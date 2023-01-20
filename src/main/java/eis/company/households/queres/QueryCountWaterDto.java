package eis.company.households.queres;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.CountWaterDTO;

@Component
public class QueryCountWaterDto {
	
	List<CountWaterDTO> countList = new ArrayList<CountWaterDTO>();
	@PersistenceContext(unitName = "eisystemsEntityManager")
	private EntityManager em;
	
	public List<CountWaterDTO> retCountWaterDTO(){
		
	countList.clear();	
		
	Query q = em.createNativeQuery("select b.id_count_water as idCountWater, b.factory_number_uspd as factoryNumberUspd, b.id_measuring as id_Measuring, \r\n"
			+ " b.count_w as countW, b.time_stamp as timeStamp, b.type_count as typeCount, b.num_ch as numCh, b.numRat  from(\r\n"
			+ " SELECT cw.id_count_water, cw.id_measuring, cw.factory_number_uspd, cw.count_w, cw.time_stamp, cw.num_ch,\r\n"
			+ " a.id_uspd_dev, a.num_uspd_dev, a.type_count, a.num_ch as numCh, a.num_rat as numRat \r\n"
			+ " FROM eisystems.count_water cw \r\n"
			+ " left join \r\n"
			+ " (SELECT ud.id_uspd_dev, ud.num_uspd_dev, cnt.type_count, cnt.num_ch, cnt.num_rat \r\n"
			+ " FROM housing.uspd_dev ud, housing.counts cnt\r\n"
			+ " where type_count in(1,2) and ud.id_uspd_dev = cnt.id_uspd_dev)a \r\n"
			+ " on a.num_uspd_dev = cw.factory_number_uspd and a.num_ch = cw.num_ch)b "
			+ " where b.type_count <> 0 and b.count_w <> 0 order by b.time_stamp desc limit 1000;", Tuple.class);
	
	@SuppressWarnings("unchecked")
	List<Tuple> list = q.getResultList();
	if (list.isEmpty())
		return null;
	
	for (Tuple t : list) {
		CountWaterDTO ad = new CountWaterDTO(
				                   (int) t.get("idCountWater"), 
				                   (String) t.get("factoryNumberUspd"),
				                   (int) t.get("id_Measuring"), 
				                   (BigDecimal) t.get("countW"),
				                   ((Timestamp) t.get("timeStamp")).toLocalDateTime(), 
				                   (t.get("typeCount") == null ? -1 : (int)t.get("typeCount")),
				                   (int) t.get("numCh"),
				                   (int) t.get("numRat")
				                   );
		countList.add(ad);
	}
		
	return countList;
		
	}

}
