package eis.company.households.queres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.HotCountFlowDTO;

@Component
public class QueryHotCountFlowDto {

	private List<HotCountFlowDTO> hotCountFlowDTO = new ArrayList<>();
	@PersistenceContext(name="housingEntityManager") private EntityManager em;
	
	public List<HotCountFlowDTO> getQueryResult(String factoryNumberUspd, String timeStamp, String timeStampPrev){
		
		hotCountFlowDTO.clear();
		
		@SuppressWarnings("unchecked")
		List<Tuple> list = em.createNativeQuery(
				  "select curr.time_stamp as timeStamp, curr.address_loc as addressLoc, curr.num_acnt as numAcnt, curr.name_count as nameCount, (curr.g_kalor) as currGKalor, (prev.g_kalor)  as prevGKalor, \r\n"
				  + "  IF(period_diff(date_format(curr.date_expire, \"%Y%m\"), date_format(curr.time_stamp, \"%Y%m\")) > 0, "
				  + "  (curr.g_kalor - prev.g_kalor), 0.0) as diffGKalor, \r\n"
				  + "  curr.serial_num as serialNum, curr.date_expire as dateExpire, curr.factory_number_uspd as factoryNumberUspd, curr.type_count as typeCount\r\n"
				  + " from \r\n"
				  + " (SELECT max(time_stamp) as time_stamp, address_loc, num_acnt, name_count, max(g_kalor) as g_kalor, serial_num, date_expire, factory_number_uspd, type_count  \r\n"
				  + " FROM housing.report_hot  \r\n"
				  + " where  factory_number_uspd like :factoryNumberUspd and type_count = 4 and time_stamp like :timeStamp \r\n"
				  + " group by factory_number_uspd\r\n"
				  + " order by factory_number_uspd) curr left join \r\n"
				  + " (SELECT max(time_stamp) as time_stamp, address_loc, num_acnt, name_count, max(g_kalor) as g_kalor, serial_num, date_expire, factory_number_uspd, type_count  \r\n"
				  + " FROM housing.report_hot  \r\n"
				  + " where  factory_number_uspd like :factoryNumberUspd and type_count = 4 and time_stamp like :timeStampPrev \r\n"
				  + " group by factory_number_uspd\r\n"
				  + " order by factory_number_uspd) prev \r\n"
				  + " on curr.factory_number_uspd = prev.factory_number_uspd", 
				Tuple.class).setParameter("factoryNumberUspd", factoryNumberUspd)
				            .setParameter("timeStamp", timeStamp)
				            .setParameter("timeStampPrev", timeStampPrev)
				            .getResultList();
		
        if(list.isEmpty()) {return null;}
		
		for(Tuple t : list) {
			HotCountFlowDTO hc = new HotCountFlowDTO(
					((java.sql.Timestamp) t.get("timeStamp")).toLocalDateTime(),
					t.get("addressLoc")!=null?(String)t.get("addressLoc"):"нет данных",
					t.get("numAcnt")!=null?(String)t.get("numAcnt"):"нет данных",
					t.get("nameCount")!=null?(String)t.get("nameCount"):"нет данных",
					Double.valueOf(t.get("currGKalor") != null ? t.get("currGKalor").toString():"0.0"),
					Double.valueOf(t.get("prevGKalor") != null ? t.get("prevGKalor").toString():"0.0"),
					Double.valueOf(t.get("diffGKalor") != null ? t.get("diffGKalor").toString():"0.0"),
					t.get("serialNum")!=null?(String)t.get("serialNum"):"нет данных",
					t.get("dateExpire")!=null?((java.sql.Date) t.get("dateExpire")).toLocalDate():LocalDate.now(),
					(String)t.get("factoryNumberUspd"),
					(int)t.get("typeCount")
					);
			
			hotCountFlowDTO.add(hc);	
			}
		return hotCountFlowDTO;
	}
	
}
