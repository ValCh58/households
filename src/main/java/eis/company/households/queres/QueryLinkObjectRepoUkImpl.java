package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.LinkObjectDTO;
import eis.company.households.repository.QueryLinkObjectRepository;

@Component
public class QueryLinkObjectRepoUkImpl implements QueryLinkObjectRepository {

	List<LinkObjectDTO> list = new ArrayList<>();
	@PersistenceContext(unitName = "housingEntityManager")
	private EntityManager em;
			
		private String sqlStr = "select f.id_link_object, f.id_parent, f.name_object, f.prop_object, f.id_object, f.id_type_object \r\n"
				+ " from(\r\n"
				+ "       select u.id_link_object, u.id_parent, u.name_object, u.prop_object, u.id_object, u.id_type_object from\r\n"
				+ "             (select l.id_link_object, l.id_parent,\r\n"
				+ "				 (select name_company from manag_company where id_manag_company = l.id_object)  as name_object,\r\n"
				+ "				 (select address_1 from manag_company where id_manag_company = l.id_object)  as prop_object,\r\n"
				+ "				 (select id_manag_company from manag_company where id_manag_company = l.id_object)  as id_object,\r\n"
				+ "				 (select id_type_object from manag_company where id_manag_company = l.id_object) as id_type_object\r\n"
				+ "				 from link_object_uk l where l.id_type_object = 1) u\r\n"
				+ "       union all\r\n"
				+ "       select s.id_link_object, s.id_parent, s.name_object, s.prop_object, s.id_object, s.id_type_object from\r\n"
				+ "           (select l.id_link_object, l.id_parent,\r\n"
				+ "				 (select name_street from street where id_street = l.id_object)  as name_object,\r\n"
				+ "				 (select district from street where id_street = l.id_object)  as prop_object,\r\n"
				+ "				 (select id_street from street where id_street = l.id_object)  as id_object,\r\n"
				+ "				 (select id_type_object from street where id_street = l.id_object) as id_type_object\r\n"
				+ "				 from link_object_uk l where l.id_type_object = 9) s\r\n"
				+ "      union all\r\n"
				+ "       select h.id_link_object, h.id_parent, h.name_object, h.prop_object, h.id_object, h.id_type_object from\r\n"
				+ "           (select l.id_link_object, l.id_parent,\r\n"
				+ "				 (select name_house from house where id_house = l.id_object)  as name_object,\r\n"
				+ "				 (select address from house where id_house = l.id_object)  as prop_object,\r\n"
				+ "				 (select id_house from house where id_house = l.id_object)  as id_object,\r\n"
				+ "				 (select id_type_object from house where id_house = l.id_object) as id_type_object\r\n"
				+ "				 from link_object_uk l where l.id_type_object = 8) h \r\n"
				+ "      union all\r\n"
				+ "       select r.id_link_object, r.id_parent, r.name_object, r.prop_object, r.id_object, r.id_type_object from\r\n"
				+ "           (select l.id_link_object, l.id_parent,\r\n"
				+ "				 (select name_room from room where id_room = l.id_object)  as name_object,\r\n"
				+ "				 (select number_room from room where id_room = l.id_object)  as prop_object,\r\n"
				+ "				 (select id_room from room where id_room = l.id_object)  as id_object,\r\n"
				+ "				 (select id_type_object from room where id_room = l.id_object) as id_type_object\r\n"
				+ "				 from link_object_uk l where l.id_type_object = 10) r  \r\n"
				+ "      union all\r\n"
				+ "       select r.id_link_object, r.id_parent, r.name_object, r.prop_object, r.id_object, r.id_type_object from\r\n"
				+ "           (select l.id_link_object, l.id_parent,\r\n"
				+ "				 (select 'Л/сч № ' from person_acnt where id_person_acnt = l.id_object)  as name_object,\r\n"
				+ "				 (select num_acnt from person_acnt where id_person_acnt = l.id_object)  as prop_object,\r\n"
				+ "				 (select id_person_acnt from person_acnt where id_person_acnt = l.id_object)  as id_object,\r\n"
				+ "				 (select id_type_object from person_acnt where id_person_acnt = l.id_object) as id_type_object\r\n"
				+ "				 from link_object_uk l where l.id_type_object = 11) r             \r\n"
				+ ")f order by f.id_parent, id_link_object";
	

	

		@Override
		public List<LinkObjectDTO> queryLinkObjectReository() {	
			
			list.clear();
			Query q = em.createNativeQuery(sqlStr, Tuple.class);
			
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
	

}
