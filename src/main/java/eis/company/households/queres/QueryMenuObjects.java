package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.LinkObjectDTO;

/**
 * Запрос для построения меню выбора объектов УК
 * @author Valeriy
 *
 */
@Component
public class QueryMenuObjects {
	
	List<LinkObjectDTO> list = new ArrayList<>();
	List<LinkObjectDTO> retList = new ArrayList<LinkObjectDTO>();
	@PersistenceContext(unitName = "housingEntityManager")
	private EntityManager em;
	
	private String sqlString = "select f.id_link_object, f.id_parent, CONCAT_WS('; ', f.name_object,f.prop_object) as name_object, f.prop_object, f.id_object, f.id_type_object \r\n"
			+ " from(select u.id_link_object, u.id_parent, u.name_object, u.prop_object, u.id_object, u.id_type_object from\r\n"
			+ " (select l.id_link_object, l.id_parent,\r\n"
			+ " (select name_company from manag_company where id_manag_company = l.id_object)  as name_object,\r\n"
			+ " (select address_1 from manag_company where id_manag_company = l.id_object)  as prop_object,\r\n"
			+ " (select id_manag_company from manag_company where id_manag_company = l.id_object)  as id_object,\r\n"
			+ " (select id_type_object from manag_company where id_manag_company = l.id_object) as id_type_object\r\n"
			+ " from housing.link_object_uk l where l.id_type_object = 1) u\r\n"
			+ " union all\r\n"
			+ " select s.id_link_object, s.id_parent, s.name_object, s.prop_object, s.id_object, s.id_type_object from\r\n"
			+ " (select l.id_link_object, l.id_parent,\r\n"
			+ " (select name_street from street where id_street = l.id_object)  as name_object,\r\n"
			+ " (select district from street where id_street = l.id_object)  as prop_object,\r\n"
			+ " (select id_street from street where id_street = l.id_object)  as id_object,\r\n"
			+ " (select id_type_object from street where id_street = l.id_object) as id_type_object\r\n"
			+ " from housing.link_object_uk l where l.id_type_object = 9) s\r\n"
			+ " union all\r\n"
			+ " select h.id_link_object, h.id_parent, h.name_object, h.prop_object, h.id_object, h.id_type_object from\r\n"
			+ " (select l.id_link_object, l.id_parent,\r\n"
			+ " (select name_house from house where id_house = l.id_object)  as name_object,\r\n"
			+ " (select address from house where id_house = l.id_object)  as prop_object,\r\n"
			+ " (select id_house from house where id_house = l.id_object)  as id_object,\r\n"
			+ " (select id_type_object from house where id_house = l.id_object) as id_type_object\r\n"
			+ " from housing.link_object_uk l where l.id_type_object = 8) h \r\n"
			+ " )f order by f.id_parent, id_link_object"; 
	
	
	private List<LinkObjectDTO> queryMenuObjects() {	
		
		list.clear();
		Query q = em.createNativeQuery(sqlString, Tuple.class);
		
		@SuppressWarnings("unchecked")
		List<Tuple> listTp = q.getResultList();
		if (listTp.isEmpty()) {return null;}
		
		for (Tuple t : listTp) {
			LinkObjectDTO ad = new LinkObjectDTO(
					                             ((Number)t.get("id_link_object")).intValue(),
					                             ((Number)t.get("id_parent")).intValue(),
					                             (String)t.get("name_object"),
					                             (String)t.get("prop_object"),
					                             ((Number)t.get("id_object")).intValue(),
					                             ((Number)t.get("id_type_object")).intValue()
					                             );
			list.add(ad);
		}
	
	    return list;
    }
	
	/**
	 * Упорядочим список с объектами в правильном порядке иерархии
	 * 
	 * @return retList список с объектами LinkObjectDTO
	 */
	public List<LinkObjectDTO> makeTree() {
		queryMenuObjects();
		retList.clear();
		int id = list.get(0).getId_link_object();
		retList.add(list.get(0));
		recurs(id);
		return retList;//Список упорядоченных объектов для построения дерева//
	}

	/**
	 * Найдем все Nodы и листья
	 * 
	 * @param id минимальный номер child_ID
	 */
	private void recurs(int id) {
		for (LinkObjectDTO n : list) {
			if (n.getId_parent().intValue() == id) {
				retList.add(n);
				recurs(n.getId_link_object());
			}
		}
	}
}
