package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.AcntCountsDTO;

@Component
public class QueryAcntCountsDto {
	
	private List<AcntCountsDTO> acntCntList = new ArrayList<>();
	
	@PersistenceContext(name="housingEntityManager")
	private EntityManager em;
	
	public List<AcntCountsDTO> getAcntCountsDTO(Integer id){
		acntCntList.clear();
		@SuppressWarnings("unchecked")
		List<Tuple> list = em.createNativeQuery("SELECT co.id_counts as idCounts, co.id_person_acnt as idPersonAcnt,"
				                              + " co.name_count as nameCount, co.serial_num as serialNum,"
				                              + " co.date_expire as dateExpire, co.address as address \r\n"
				                              + "FROM housing.room ro \r\n"
				                              + "left join housing.uspd_dev ud on ro.id_uspd_dev = ud.id_uspd_dev\r\n"
				                              + "left join housing.counts co on ud.id_uspd_dev = co.id_uspd_dev\r\n"
				                              + "where ro.id_room ="
				                              + "(select hll.id_object from housing.link_object_uk hll where hll.id_link_object = :id)"
				                              , Tuple.class).setParameter("id", id).getResultList();
		if(list.isEmpty())
			return null;
		
		for(Tuple t : list) {
			
			AcntCountsDTO ac = new AcntCountsDTO(
					           (int)t.get("idCounts"),
					           t.get("idPersonAcnt") == null ? 0 : (int)t.get("idPersonAcnt"),
					           (String)t.get("nameCount"),
					           (String)t.get("serialNum"),
					           ((java.sql.Date) t.get("dateExpire")).toLocalDate(),
					           (String)t.get("address"));
			
			acntCntList.add(ac);
		}
		
		return acntCntList;
	}

}
