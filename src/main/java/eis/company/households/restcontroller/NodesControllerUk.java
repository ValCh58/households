package eis.company.households.restcontroller;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.LinkObjectDTO;
import eis.company.households.dto.UspdFlatDto;
import eis.company.households.dto.selectIdFromLinkObj;
import eis.company.households.model.House;
import eis.company.households.model.ManagCompany;
import eis.company.households.model.PersonAcnt;
import eis.company.households.model.Room;
import eis.company.households.model.Street;
import eis.company.households.queres.QueryLinkObjectRepoUkImpl;
import eis.company.households.queres.QueryUspdFlat;
import eis.company.households.service.ObjectUserService;

@RestController
public class NodesControllerUk {

	List<LinkObjectDTO> queryList;
	List<LinkObjectDTO> retList = new ArrayList<LinkObjectDTO>();

	private QueryLinkObjectRepoUkImpl queryLinkObj;
	private ObjectUserService objUserSrv;
	private QueryUspdFlat queryUspdFlat;
	
	@Autowired
	public NodesControllerUk(QueryLinkObjectRepoUkImpl queryLinkObj, ObjectUserService objUserSrv,
			QueryUspdFlat queryUspdFlat) {
		super();
		this.queryLinkObj = queryLinkObj;
		this.objUserSrv = objUserSrv;
		this.queryUspdFlat = queryUspdFlat;
	}

	/**
	 * Получение списка данных для построения tree table объектов УК
	 * 
	 * @return список объектов для дерева
	 */
	@GetMapping(value = "nodes_tree_uk")
	public ResponseEntity<List<LinkObjectDTO>> nodes() {
		
		return ResponseEntity.status(OK).body(makeTree());
	}

	/******************************************************************************************/
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

	/******************************************************************************************/
	/**********************
	 * Editing and saving of UK objects
	 ************************************/
	/******************************************************************************************/

	/**
	 * Редактирование параметров УК
	 * 
	 * @return ManagCompany
	 */
	@GetMapping(value = "nodeEditUk/{id}")
	public ResponseEntity<ManagCompany> getManageCompany(@PathVariable("id") Integer id) {
		return ResponseEntity.status(OK).body(objUserSrv.getDataManagCompany(id));
	}

	/**
	 * Update параметров УК
	 */
	@PostMapping(value = "updateEditUk")
	public ResponseEntity<ManagCompany> updateUk(ManagCompany mc) {
		ManagCompany managCompany =  objUserSrv.updateDataManagCompany(mc);
		
		return ResponseEntity.status(OK).body(managCompany);
	}

	// ******************************************************************************************/
	/**
	 * Редактирование параметров улицы
	 * 
	 * @return Street
	 */
	@GetMapping(value = "nodeEditStreet/{id}")
	public ResponseEntity<Street> getStreet(@PathVariable("id") Integer id) {
		return ResponseEntity.status(OK).body(objUserSrv.getStreet(id));
	}

	/**
	 * Update or Insert параметров улицы
	 */
	@PostMapping(value = "/updateEditStreet")
	public ResponseEntity<Street> updateStreet(Street st) {
		Street street = null;
		
		if (st.getIdStreet() > 0) {
			street = objUserSrv.updateStreet(st);
		} else {
			street = objUserSrv.insertStreet(st);
		}
		
		return ResponseEntity.status(OK).body(street);
	}

	// ******************************************************************************************/
	/**
	 * Edit of House
	 * 
	 * @return House
	 */
	@GetMapping(value = "nodeEditHouse/{id}")
	public ResponseEntity<House> getHouse(@PathVariable("id") Integer id) {
		return ResponseEntity.status(OK).body(objUserSrv.getHouse(id));
	}

	/**
	 * Update or Insert House
	 */
	@PostMapping(value = "/updateEditHome")
	public ResponseEntity<House> updateHome(House hs) {
		House house = null;
		
		if (hs.getIdHouse() > 0) {
			house = objUserSrv.updateHouse(hs);
		} else {
			house = objUserSrv.insertHouse(hs);
		}
		
		return ResponseEntity.status(OK).body(house);
	}

	// ******************************************************************************************/
	/**
	 * Редактирование параметров кавартиры
	 * 
	 * @return Room
	 */
	@GetMapping(value = "nodeEditFlat/{id}")
	public ResponseEntity<Room> getFlat(@PathVariable("id") Integer id) {
		Room room = objUserSrv.getRoom(id);
		room.setId_uspd(room.getUspdDev().getIdUspdDev());
		return ResponseEntity.status(OK).body(room);
	}

	/**
	 * Update параметров квартиры
	 */
	@PostMapping(value = "/updateEditFlat")
	public ResponseEntity<Room> updateFlat(Room rm) {
		Room room = null;
		
		if (rm.getIdRoom() > 0) {
			room = objUserSrv.updateRoom(rm);
		} else {
			room = objUserSrv.insertRoom(rm);
		}
		
        return ResponseEntity.status(OK).body(room); 
	}

	// ******************************************************************************************/
	/**
	 * Редактирование параметров л/с
	 * 
	 * @return PersonAcnt
	 */
	@GetMapping(value = "nodeEditAcc/{id}")
	public ResponseEntity<PersonAcnt> getPersonAcc(@PathVariable("id") Integer id) {
		return  ResponseEntity.status(OK).body(objUserSrv.getPersonAcnt(id));
	}

	/**
	 * Update or insert л.сч
	 */
	@PostMapping(value = "/updateEditAcc")
	public ResponseEntity<PersonAcnt> updateAct(final PersonAcnt pAcnt) {
		PersonAcnt personAcnt = null;
		
			if (pAcnt.getIdPersonAcnt() > 0) {
				personAcnt = objUserSrv.updateAccount(pAcnt);
			} else {
				personAcnt = objUserSrv.insertPersonAcnt(pAcnt);
			}
             
		    return ResponseEntity.status(OK).body(personAcnt);
	}

	//******************************************************************************************/
	//* The end editing and saving of UK objects
	//******************************************************************************************/

	/**
	 * Удаление объеkтов из дерева УК
	 * 
	 * @param selectidfromlinkobj
	 */

	@PostMapping(value = "/delObjectTreeUk")
	public ResponseEntity<String> delObjectTree(final selectIdFromLinkObj selectidfromlinkobj) {
		Integer selectTypeObj = selectidfromlinkobj.getId_type_object();
		Integer idLinkObjTree = selectidfromlinkobj.getId_link_object();

		switch (selectTypeObj) {
		case 9:// Street del
			if(objUserSrv.delStreet(idLinkObjTree))
			   return ResponseEntity.status(HttpStatus.OK).body("Delete Street");
			break;
		case 8:// House del
			if(objUserSrv.delHouse(idLinkObjTree))
			   return ResponseEntity.status(HttpStatus.OK).body("Delete House");
			break;
		case 10:// Room del
			if(objUserSrv.delRoom(idLinkObjTree))
			   return ResponseEntity.status(HttpStatus.OK).body("Delete Room");
			break;
		case 11:// Account del
			if(objUserSrv.delPersonAcnt(idLinkObjTree))
			   return ResponseEntity.status(HttpStatus.OK).body("Delete Account");
			break;
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deletion error");
	}

	/*****************************************************************************************/
	/*******************************
	 * Заполнение формы modalFormEditFlat
	 ************************/
	/*****************************************************************************************/
	@GetMapping(value = "/flatUspdEdit")
	public ResponseEntity<List<UspdFlatDto>> getFlatUspdEdit() {
		return  ResponseEntity.status(OK).body(queryUspdFlat.retUspdFlatDto());
	}
	/*****************************************************************************************/
}
