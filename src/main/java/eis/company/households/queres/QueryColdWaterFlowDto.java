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
	
	public List<ColdWaterFlowDTO> getQueryResult(String factoryNumberUspd, String timeStamp){
		
		coldWaterFlowDTO.clear();
		
		@SuppressWarnings("unchecked")
		List<Tuple> list = em.createNativeQuery(
				  "SELECT max(time_stamp) as timeStamp, address_loc as addressLoc, "
				+ "num_acnt as numAcnt, name_count as nameCount, max(count_w) as countW, \r\n"
				+ "serial_num as serialNum, date_expire as dateExpire, factory_number_uspd as factoryNumberUspd, num_ch as numCh  \r\n"
				+ " FROM housing.report_all\r\n"
				+ " where  factory_number_uspd like :factoryNumberUspd and num_ch = 1 and time_stamp like :timeStamp \r\n"
				+ " group by factory_number_uspd\r\n"
				+ " order by factory_number_uspd", 
				Tuple.class).setParameter("factoryNumberUspd", factoryNumberUspd)
				            .setParameter("timeStamp", timeStamp)
				            .getResultList();
				
		if(list.isEmpty())return null;
		
		for(Tuple t : list) {
			ColdWaterFlowDTO cw = new ColdWaterFlowDTO(
					((java.sql.Timestamp) t.get("timeStamp")).toLocalDateTime(),
					t.get("addressLoc")!=null?(String)t.get("addressLoc"):"нет данных",
					t.get("numAcnt")!=null?(String)t.get("numAcnt"):"нет данных",
					t.get("nameCount")!=null?(String)t.get("nameCount"):"нет данных",
					Double.valueOf(t.get("countW").toString()),
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
