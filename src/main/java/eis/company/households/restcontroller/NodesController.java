package eis.company.households.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.dto.CountsDTO;
import eis.company.households.dto.EditServerDTO;
import eis.company.households.dto.EditUspdDTO;
import eis.company.households.dto.LinkObjectDTO;
import eis.company.households.dto.selectIdFromLinkObj;
import eis.company.households.model.ComServer;
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

	private QueryCountsDto queryCountsDto;
	private UspdDevRepository uspdDevRep;
	private TypeUspdRepository typeUspdRepository;
	private QueryLinkObjectRepoImpl queryLinkObj;
	private QueryEditSrvRepositoryImpl queryEditSrv;
	private UpdateEditSrv updEditSrv;
	
	@Autowired
	public NodesController(QueryCountsDto queryCountsDto, UspdDevRepository uspdDevRep,
			TypeUspdRepository typeUspdRepository, QueryLinkObjectRepoImpl queryLinkObj,
			QueryEditSrvRepositoryImpl queryEditSrv, UpdateEditSrv updEditSrv) {
		super();
		this.queryCountsDto = queryCountsDto;
		this.uspdDevRep = uspdDevRep;
		this.typeUspdRepository = typeUspdRepository;
		this.queryLinkObj = queryLinkObj;
		this.queryEditSrv = queryEditSrv;
		this.updEditSrv = updEditSrv;
	}

	/**
	 * Получение списка данных для построения tree table
	 * 
	 * @return список объектов для дерева
	 */
	@GetMapping(value = "nodes")
	public ResponseEntity<List<LinkObjectDTO>> nodes() {
		return ResponseEntity.status(HttpStatus.OK).body(makeTree());
	}

//******************************************************************************************
	/**
	 * Упорядочим список с объектами в правильном порядке иерархии
	 * 
	 * @return retList список с объектами LinkObjectDTO
	 */
	private List<LinkObjectDTO> makeTree() {
		queryList = Optional.ofNullable(queryLinkObj.queryLinkObjectReository())
				            .orElseThrow(()->new ResourceNotFoundException("Object list LinkObjectDTO Not found"));
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

		return ResponseEntity.status(HttpStatus.OK).body(editUspdDto);
	}

	/**
	 * Создание нового счетчика
	 * 
	 * @return countsDt
	 */
	@GetMapping(value = "nodeNewCount")
	public ResponseEntity<CountsDTO> createCount() {
		CountsDTO countsDto = new CountsDTO(0, 0, "", LocalDate.now(), LocalDate.now(), "", "", 0, 1, 0);

		return ResponseEntity.status(HttpStatus.OK).body(countsDto);
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
		List<EditServerDTO> retList = Optional.ofNullable(queryEditSrv.queryEditModalFormRepository(id))
				                     .orElseThrow(()->new ResourceNotFoundException("Object EditServerDTO Not found"));
		return ResponseEntity.status(HttpStatus.OK).body(retList.get(0));
	}

	/**
	 * Запрос данных для формы редактирование счетчика
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "nodeCounts/{id}")
	public ResponseEntity<CountsDTO> getCountsDto(@PathVariable("id") Integer id) {
		CountsDTO countDto = Optional.ofNullable(queryCountsDto.retCountsDto(id))
				            .orElseThrow(()->new ResourceNotFoundException("Object CountsDTO Not found"));
		return ResponseEntity.status(HttpStatus.OK).body(countDto);
	}

	/**
	 * Update Counts
	 * 
	 * @param countsDto
	 */
	@PostMapping(value = "updateCounts")
	public ResponseEntity<CountsDTO> saveCount(CountsDTO countsDto) {
	    updEditSrv.updateCounts(countsDto);
		return ResponseEntity.status(HttpStatus.OK).body(countsDto);
	}

	/**
	 * Update UspdDev
	 * 
	 * @param editUspdDto
	 */
	@PostMapping(value = "updateUspdDev")
	public ResponseEntity<UspdDev> updateUspdDev(EditUspdDTO editUspdDto) {
		UspdDev uspdDev = updEditSrv.saveUspdDev(editUspdDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(uspdDev);
	}

	/**
	 * Запись изменений при редактировании
	 * 
	 * @param editServerDto объект данных от модального окна. Поменять класс
	 *                      EditServerDTO на класс ComServer!!!
	 */
	@PostMapping(value = "updateEditSrv")
	public ResponseEntity<ComServer> updateEditSrvFromModal(EditServerDTO editServerDto) {
		ComServer comServer = updEditSrv.saveComServer(editServerDto);
		return ResponseEntity.status(HttpStatus.OK).body(comServer);
	}

	@PostMapping(value = "newSrv")
	public ResponseEntity<ComServer> addNewServer(EditServerDTO editServerDto) {
		ComServer comServer = updEditSrv.saveNewComServer(editServerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(comServer);
	}

	@PostMapping(value = "delObjectTree")
	public BodyBuilder delObjectTree(selectIdFromLinkObj selectidfromlinkobj) {
		Integer selectTypeObj = selectidfromlinkobj.getId_type_object();
		Integer idLinkObjTree = selectidfromlinkobj.getId_link_object();

		switch (selectTypeObj) {
		case 2:
			if(updEditSrv.delServerObj(idLinkObjTree))
				return ResponseEntity.status(HttpStatus.OK);
			break;

		case 3:
			if(updEditSrv.delUspdObj(idLinkObjTree))
				return ResponseEntity.status(HttpStatus.OK);
			break;

		case 4:
            if(updEditSrv.delCounts(idLinkObjTree))
            	return ResponseEntity.status(HttpStatus.OK);
			break;
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
