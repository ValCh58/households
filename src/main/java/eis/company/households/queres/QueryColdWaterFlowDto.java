package eis.company.households.queres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.ColdWaterFlowDTO;

@Component
public class QueryColdWaterFlowDto {
	
	private List<ColdWaterFlowDTO> coldWaterFlowDTO = new ArrayList<>();
	@PersistenceContext(name="housingEntityManager") private EntityManager em;
	
	public List<ColdWaterFlowDTO> getQueryResult(String factoryNumberUspd, String timeStamp, String timeStampPrev, String typeCount){
		
		coldWaterFlowDTO.clear();
		
		@SuppressWarnings("unchecked")
		List<Tuple> list = em.createNativeQuery(
				  "select curr.time_stamp as timeStamp, curr.address_loc as addressLoc, curr.num_acnt as numAcnt, "
				  + "curr.name_count as nameCount, (curr.count_w/curr.num_rat) as countW, (prev.count_w/prev.num_rat)  as prevCountW, \r\n"
				  + "IF(period_diff(date_format(curr.date_expire, \"%Y%m\"), date_format(curr.time_stamp, \"%Y%m\")) > 0, (curr.count_w - prev.count_w)/prev.num_rat, '0') as diffCountW, "
				  + "curr.serial_num as serialNum, curr.date_expire as dateExpire, "
				  + "curr.factory_number_uspd as factoryNumberUspd, curr.num_ch as numCh \r\n"
				  + " from \r\n"
				  + "(SELECT max(time_stamp) as time_stamp, address_loc, num_acnt, name_count, max(count_w) as count_w, serial_num, date_expire, factory_number_uspd, num_ch, num_rat  \r\n"
				  + " FROM housing.report_all  \r\n"
				  + " where  factory_number_uspd like :factoryNumberUspd and type_count = :typeCount and time_stamp like :timeStamp \r\n"
				  + " group by factory_number_uspd\r\n"
				  + " order by factory_number_uspd) curr left join \r\n"
				  + " (SELECT max(time_stamp) as time_stamp, address_loc, num_acnt, name_count, max(count_w) as count_w, serial_num, date_expire, factory_number_uspd, num_ch, num_rat  \r\n"
				  + " FROM housing.report_all  \r\n"
				  + " where  factory_number_uspd like :factoryNumberUspd and type_count = :typeCount and time_stamp like :timeStampPrev \r\n"
				  + " group by factory_number_uspd\r\n"
				  + " order by factory_number_uspd) prev \r\n"
				  + " on curr.factory_number_uspd = prev.factory_number_uspd", 
				Tuple.class).setParameter("factoryNumberUspd", factoryNumberUspd)
				            .setParameter("timeStamp", timeStamp)
				            .setParameter("timeStampPrev", timeStampPrev)
				            .setParameter("typeCount", typeCount)
				            .getResultList();
				
		if(list.isEmpty()) {return null;}
		
		for(Tuple t : list) {
			ColdWaterFlowDTO cw = new ColdWaterFlowDTO(
					((java.sql.Timestamp) t.get("timeStamp")).toLocalDateTime(),
					t.get("addressLoc")!=null?(String)t.get("addressLoc"):"нет данных",
					t.get("numAcnt")!=null?(String)t.get("numAcnt"):"нет данных",
					t.get("nameCount")!=null?(String)t.get("nameCount"):"нет данных",
					Double.valueOf(t.get("countW") != null ? t.get("countW").toString():"0.0"),
					Double.valueOf(t.get("prevCountW") != null ? t.get("prevCountW").toString():"0.0"),
					Double.valueOf(t.get("diffCountW") != null ? t.get("diffCountW").toString():"0.0"),
					t.get("serialNum")!=null?(String)t.get("serialNum"):"нет данных",
					t.get("dateExpire")!=null?((java.sql.Date) t.get("dateExpire")).toLocalDate():LocalDate.now(),
					(String)t.get("factoryNumberUspd"),
					(int)t.get("numCh")
					);
			
			coldWaterFlowDTO.add(cw);	
			}
		
		
		return coldWaterFlowDTO;
	}

}
