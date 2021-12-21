package eis.company.households.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.LinkObjectDTO;
import eis.company.households.queres.QueryLinkObjectRepoUkImpl;

@RestController
public class NodesControllerUk {

	List<LinkObjectDTO> queryList;
	List<LinkObjectDTO> retList = new ArrayList<LinkObjectDTO>();
	
	@Autowired private QueryLinkObjectRepoUkImpl queryLinkObj;
	
	/**
	 * Получение списка данных для построения tree table объектов УК
	 * 
	 * @return список объектов для дерева
	 */
	@GetMapping(value = "nodes_uk")
	public List<LinkObjectDTO> nodes() {
		return makeTree();
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

}
