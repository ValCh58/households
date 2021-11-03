package eis.company.households.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired private QueryLinkObjectRepoUkImpl queryLinkObj;
	@Autowired private ObjectUserService objUserSrv;
	@Autowired private QueryUspdFlat queryUspdFlat;
	 
	
	/**
	 * Получение списка данных для построения tree table объектов УК
	 * 
	 * @return список объектов для дерева
	 */
	@GetMapping(value = "nodes_tree_uk")
	public List<LinkObjectDTO> nodes() {
		return makeTree();
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
		/**********************Editing and saving of UK objects************************************/
		/******************************************************************************************/
		
		/**
		 * Редактирование параметров УК
		 * @return ManagCompany
		 */
		@GetMapping(value = "nodeEditUk/{id}")
		public ManagCompany getManageCompany(@PathVariable("id") Integer id) {
			return objUserSrv.getDataManagCompany(id);
		}
		
		/**
		 * Update параметров УК
		 */
		@PostMapping(value="updateEditUk")
		public void updateUk(ManagCompany mc) {
			objUserSrv.updateDataManagCompany(mc);
		}
		
		//******************************************************************************************/	
		/**
		 * Редактирование параметров улицы
		 * @return Street
		 */
		@GetMapping(value = "nodeEditStreet/{id}")
		public Street getStreet(@PathVariable("id") Integer id) {
			return objUserSrv.getStreet(id);
		}
		
		/**
		 * Update or Insert параметров улицы
		 */
		@PostMapping(value="/updateEditStreet")
		public void updateStreet(Street st) {
			if(st.getIdStreet() > 0) {
			   objUserSrv.updateStreet(st);
			}else {
			   objUserSrv.insertStreet(st);
			}
		}
		
		//******************************************************************************************/
		/**
		 * Edit of House
		 * @return House
		 */
		@GetMapping(value = "nodeEditHouse/{id}")
		public House getHouse(@PathVariable("id") Integer id) {
			return objUserSrv.getHouse(id); 
		}
		
		/**
		 * Update or Insert House 
		 */
		@PostMapping(value="/updateEditHome")
		public void updateHome(House hs) {
			if(hs.getIdHouse() > 0) {
			   objUserSrv.updateHouse(hs);
			}else {
			   objUserSrv.insertHouse(hs);	
			}
		}
		
		//******************************************************************************************/
		/**
		 * Редактирование параметров кавартиры
		 * @return Room
		 */
		@GetMapping(value = "nodeEditFlat/{id}")
		public Room getFlat(@PathVariable("id") Integer id) {
			Room room = objUserSrv.getRoom(id);
			room.setId_uspd(room.getUspdDev().getIdUspdDev());
			return  room;
		}
		
		/**
		 * Update параметров квартиры
		 */
		@PostMapping(value="/updateEditFlat")
		public void updateFlat(Room rm) {
			
			if(rm.getIdRoom() > 0) {
			    objUserSrv.updateRoom(rm);
			}else {
				objUserSrv.insertRoom(rm);
			}
			
		}
		
		//******************************************************************************************/
		/**
		 * Редактирование параметров л/с
		 * @return PersonAcnt
		 */
		@GetMapping(value = "nodeEditAcc/{id}")
		public PersonAcnt getPersonAcc(@PathVariable("id") Integer id) {
			return objUserSrv.getPersonAcnt(id); 
		}
		
		/**
		 * Update or insert л.сч
		 */
		@PostMapping(value="/updateEditAcc")
		public void updateAct(PersonAcnt pAcnt) {
			if(pAcnt.getIdPersonAcnt() > 0) {
			objUserSrv.updateAccount(pAcnt);
			}else {
			objUserSrv.insertPersonAcnt(pAcnt);	
			}
		}

		/******************************************************************************************/
		/**********************The end editing and saving of UK objects****************************/
		/******************************************************************************************/
		
		/**
		 * Удаление объеkтов из дерева УК
		 * @param selectidfromlinkobj
		 */
		
		@PostMapping(value = "/delObjectTreeUk")
		public void delObjectTree(final selectIdFromLinkObj selectidfromlinkobj) {
			Integer selectTypeObj = selectidfromlinkobj.getId_type_object();
			Integer idLinkObjTree = selectidfromlinkobj.getId_link_object();

			switch (selectTypeObj) {
			case 9://Street del
				objUserSrv.delStreet(idLinkObjTree);
				break;

			case 8://House del
				objUserSrv.delHouse(idLinkObjTree);
				break;

			case 10://Room del
				objUserSrv.delRoom(idLinkObjTree);
				break;
				
			case 11://Account del
				objUserSrv.delPersonAcnt(idLinkObjTree);
				break;	
			}
		}
		
		/*****************************************************************************************/
		/*******************************Заполнение формы modalFormEditFlat************************/
		/*****************************************************************************************/
		@GetMapping(value="/flatUspdEdit")
		public List<UspdFlatDto> getFlatUspdEdit() {
			return queryUspdFlat.retUspdFlatDto();
		}
		/*****************************************************************************************/
}
