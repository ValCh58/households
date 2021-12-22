package eis.company.households.queres;

import java.sql.Timestamp;
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
	private EntityManager me;
	
	@SuppressWarnings("unused")
	public List<AcntCountsDTO> getAcntCountsDTO(Integer id){
		@SuppressWarnings("unchecked")
		List<Tuple> list = me.createNativeQuery("SELECT co.id_counts as idCounts, co.id_person_acnt as idPersonAcnt,"
				                              + " co.name_count as nameCount, co.serial_num as serialNum,"
				                              + " co.date_expire as dateExpire, co.address as address \r\n"
				                              + "FROM housing.room ro \r\n"
				                              + "left join housing.uspd_dev ud on ro.id_uspd_dev = ud.id_uspd_dev\r\n"
				                              + "left join housing.counts co on ud.id_uspd_dev = co.id_uspd_dev\r\n"
				                              + "where ro.id_room =:id", Tuple.class).setParameter("id", id).getResultList();
		if(list.isEmpty())
			return null;
		
		for(Tuple t : list) {
			
			AcntCountsDTO ac = new AcntCountsDTO(
					           (int)t.get("idCounts"),
					           (int)t.get("idPersonAcnt"),
					           (String)t.get("nameCount"),
					           (String)t.get("serialNum"),
					           ((Timestamp) t.get("dateExpire")).toLocalDateTime(),
					           (String)t.get("address"));
			
			acntCntList.add(ac);
		}
		
		return acntCntList;
	}

}
