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
public class QueryLinkObjectRepoImpl implements QueryLinkObjectRepository{
	
	List<LinkObjectDTO> list = new ArrayList<>();
	@PersistenceContext(unitName = "housingEntityManager")
	private EntityManager em;
	
	private String sqlStr = "select f.id_link_object, f.id_parent, f.name_object, f.prop_object, f.id_object, f.id_type_object from(\r\n"
				+"select r.id_link_object, r.id_parent, r.name_object, r.prop_object, r.id_object, r.id_type_object from\r\n"
				+ " (select l.id_link_object, l.id_parent,\r\n"
				+ " (select name_company from manag_company where id_manag_company = l.id_object)  as name_object,\r\n"
				+ " (select address_1 from manag_company where id_manag_company = l.id_object)  as prop_object,\r\n"
				+ " (select id_manag_company from manag_company where id_manag_company = l.id_object)  as id_object,\r\n"
				+ " (select id_type_object from manag_company where id_manag_company = l.id_object) as id_type_object\r\n"
				+ " from link_object l where l.id_type_object = 1) r\r\n"
				+ " union all "
				+ " select a.id_link_object, a.id_parent, name_object, concat(a.ip_object, ' ', a.port_object, ' ', a.name_company) prop_object,\r\n"
				+  "a.id_object, a.id_type_object from (select l.id_link_object, l.id_parent, \r\n"
				+ " (select name_server from com_server where id_com_server = l.id_object) as name_object,\r\n"
				+ " (select ip_server   from com_server where id_com_server = l.id_object) as ip_object,\r\n"
				+ " (select port_server from com_server where id_com_server = l.id_object) as port_object,\r\n"
				+ " (select name_company from manag_company where id_manag_company = (select id_manag_company from com_server where id_com_server = id_object)) as name_company,\r\n"
				+ " (select id_com_server from com_server where id_com_server = l.id_object) as id_object,\r\n"
				+ " (select id_type_object from com_server where id_com_server = l.id_object) as id_type_object\r\n"
				+ " from link_object l where l.id_type_object = 2 ) a union all\r\n"
				+ " select b.id_link_object, b.id_parent, b.name_object, b.type_uspd as prop_object, b.id_object, b.id_type_object from \r\n"
				+ " (select (select id_link_object from link_object where id_type_object = 3 and id_object = u.id_uspd_dev) as id_link_object,\r\n"
				+ " (select id_parent from link_object where id_type_object = 3 and id_object = u.id_uspd_dev) as id_parent,\r\n"
				+ " u.name_uspd_dev as name_object, (select name_type from type_uspd where id_type_uspd = u.id_type_uspd_dev) as type_uspd,\r\n"
				+ " u.id_uspd_dev as id_object, u.id_type_object as id_type_object \r\n"
				+ " from uspd_dev u where u.id_type_object = 3) b union all\r\n"
				+ " select (select id_link_object from link_object where id_type_object = 4 and id_object = c.id_counts) as id_link_object,\r\n"
				+ " (select id_parent from link_object where id_type_object = 4 and id_object = c.id_counts) as id_parent,\r\n"
				+ " c.name_count as name_object, concat(' №:', c.serial_num, '  ', address) as prop_object,\r\n"
				+ " c.id_counts as id_object, c.id_type_object as id_type_object\r\n"
				+ " from counts c where c.id_type_object = 4) f order by f.id_parent, id_link_object";
		
	

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
