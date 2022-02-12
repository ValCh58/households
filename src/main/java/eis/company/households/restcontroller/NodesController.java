package eis.company.households.restcontroller;

import static org.springframework.http.HttpStatus.OK;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.CountsDTO;
import eis.company.households.dto.EditServerDTO;
import eis.company.households.dto.EditUspdDTO;
import eis.company.households.dto.LinkObjectDTO;
import eis.company.households.dto.selectIdFromLinkObj;
import eis.company.households.model.UspdDev;
import eis.company.households.queres.QueryCountsDto;
import eis.company.households.queres.QueryEditSrvRepositoryImpl;
import eis.company.households.queres.QueryLinkObjectRepoImpl;
import eis.company.households.repository.TypeUspdRepository;
import eis.company.households.repository.UspdDevRepository;
import eis.company.households.service.UpdateEditSrv;

@RestController
public class NodesController {

	List<LinkObjectDTO> queryList;
	List<LinkObjectDTO> retList = new ArrayList<LinkObjectDTO>();

	@Autowired private QueryCountsDto queryCountsDto;
	@Autowired private UspdDevRepository uspdDevRep;
	@Autowired private TypeUspdRepository typeUspdRepository;
	@Autowired private QueryLinkObjectRepoImpl queryLinkObj;
	@Autowired private QueryEditSrvRepositoryImpl queryEditSrv;
	@Autowired private UpdateEditSrv updEditSrv;

	public NodesController() {}

	/**
	 * Получение списка данных для построения tree table
	 * 
	 * @return список объектов для дерева
	 */
	@GetMapping(value = "nodes")
	public ResponseEntity<List<LinkObjectDTO>> nodes() {
		return ResponseEntity.status(OK).body(makeTree());
	}

//******************************************************************************************
	/**
	 * Упорядочим список с объектами в правильном порядке иерархии
	 * 
	 * @return retList список с объектами LinkObjectDTO
	 */
	private List<LinkObjectDTO> makeTree() {
		queryList = queryLinkObj.queryLinkObjectReository();
		retList.clear();
		int id = queryList.get(0).getId_link_object();
		retList.add(queryList.get(0));
		recurs(id);
		return retList;
	}

	/**
	 * Найдем все Nodы и листья
	 * 
	 * @param id минимальный номер child_ID
	 */
	void recurs(int id) {
		for (LinkObjectDTO n : queryList) {
			if (n.getId_parent().intValue() == id) {
				retList.add(n);
				recurs(n.getId_link_object());
			}
		}
	}
//******************************************************************************************/

	//@GetMapping(value = "nodeEditCount/{id}")
	//public void getCountObj(@PathVariable("id") Integer id) {
	//}

	/**
	 * Заполнение полей формы "Редактирование УСПД"
	 * 
	 * @param id записи в таблице UspdDev
	 * @return EditUspdDTO
	 */
	@GetMapping(value = "nodeEditUspd/{id}")
	public ResponseEntity<EditUspdDTO> getUspdDev(@PathVariable("id") Integer id) {
		Optional<UspdDev> opUspdDev = uspdDevRep.getByIdUspdDev(id);
		EditUspdDTO editUspdDto = null;

		if (opUspdDev.isPresent()) {
			editUspdDto = new EditUspdDTO(opUspdDev.get().getIdUspdDev(), opUspdDev.get().getNameUspdDev(),
					opUspdDev.get().getNumUspdDev(), opUspdDev.get().getAddressLoc(), opUspdDev.get().getIdCounts(),
					opUspdDev.get().getIdConfigUspd(), opUspdDev.get().getTypeUspd().getIdTypeUspd(), 0, 0,
					typeUspdRepository.findAll());

		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(editUspdDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(editUspdDto);
	}

	/**
	 * Создание УСПД объекта
	 * 
	 * @return editUspdDto - заполнит список типами УСПД в модальном окне
	 */
	@GetMapping(value = "nodeNewUspd")
	public ResponseEntity<EditUspdDTO> createNewUspd() {
		EditUspdDTO editUspdDto = new EditUspdDTO(0, "", "", "", 0, 0, 0, 0, 0, typeUspdRepository.findAll());

		return ResponseEntity.status(OK).body(editUspdDto);
	}

	/**
	 * Создание нового счетчика
	 * 
	 * @return countsDt
	 */
	@GetMapping(value = "nodeNewCount")
	public ResponseEntity<CountsDTO> createCount() {
		CountsDTO countsDto = new CountsDTO(0, 0, "", LocalDate.now(), LocalDate.now(), "", "", 0, 0);

		return ResponseEntity.status(OK).body(countsDto);
	}

	/**
	 * Отправка данных запроса для модального окна редактирования сервера
	 * 
	 * @param id_object поле из таблицы LinkObject для передачи параметра в запрос
	 * @return EditServerDTO объект - результат запроса для модального окна
	 */
	@GetMapping(value = "nodeEditSrv/{id}")
	public ResponseEntity<EditServerDTO> getNode(@PathVariable("id") Integer id) {
		// LinkObject linkObject = updEditSrv.getDataFromTree(id);
		List<EditServerDTO> retList = queryEditSrv.queryEditModalFormRepository(id);
		return ResponseEntity.status(OK).body(retList.get(0));
	}

	/**
	 * Запрос данных для формы редактирование счетчика
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "nodeCounts/{id}")
	public ResponseEntity<CountsDTO> getCountsDto(@PathVariable("id") Integer id) {
		return ResponseEntity.status(OK).body(queryCountsDto.retCountsDto(id));
	}

	/**
	 * Update Counts
	 * 
	 * @param countsDto
	 */
	@PostMapping(value = "updateCounts")
	public void saveCount(CountsDTO countsDto) {
		updEditSrv.updateCounts(countsDto);
	}

	/**
	 * Update UspdDev
	 * 
	 * @param editUspdDto
	 */
	@PostMapping(value = "updateUspdDev")
	public void updateUspdDev(EditUspdDTO editUspdDto) {
		updEditSrv.saveUspdDev(editUspdDto);
	}

	/**
	 * Запись изменений при редактировании
	 * 
	 * @param editServerDto объект данных от модального окна. Поменять класс
	 *                      EditServerDTO на класс ComServer!!!
	 */
	@PostMapping(value = "updateEditSrv")
	public void updateEditSrvFromModal(EditServerDTO editServerDto) {
		updEditSrv.saveComServer(editServerDto);
	}

	@PostMapping(value = "newSrv")
	public void addNewServer(EditServerDTO editServerDto) {
		updEditSrv.saveNewComServer(editServerDto);
	}

	@PostMapping(value = "delObjectTree")
	public void delObjectTree(final selectIdFromLinkObj selectidfromlinkobj) {
		Integer selectTypeObj = selectidfromlinkobj.getId_type_object();
		Integer idLinkObjTree = selectidfromlinkobj.getId_link_object();

		switch (selectTypeObj) {
		case 2:// MyConst.TYPE_OBJECT_SERVER
			updEditSrv.delServerObj(idLinkObjTree);
			break;

		case 3:// MyConst.TYPE_OBJECT_USPD
			updEditSrv.delUspdObj(idLinkObjTree);
			break;

		case 4:// MyConst.TYPE_OBJECT_COUNT
            updEditSrv.delCounts(idLinkObjTree);
			break;
		}
	}

}
