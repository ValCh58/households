package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import eis.company.households.dto.ColdWaterFlowDTO;

public class QueryColdWaterFlowDto {
	
	private List<ColdWaterFlowDTO> coldWaterFlowDTO = new ArrayList<>();
	@PersistenceContext(name="housingEntityManager") private EntityManager em;
	
	@SuppressWarnings("unused")
	private List<ColdWaterFlowDTO> getQueryResult(String factoryNumberUspd, String timeStamp){
		
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
					((java.sql.Date) t.get("timeStamp")).toLocalDate(),
					(String)t.get("addressLoc"),
					(String)t.get("numAcnt"),
					(String)t.get("nameCount"),
					(double)t.get("countW"),
					(String)t.get("serialNum"),
					((java.sql.Date) t.get("dateExpire")).toLocalDate(),
					(String)t.get("factoryNumberUspd"),
					(int)t.get("numCh")
					);
			
			coldWaterFlowDTO.add(cw);	
			}
		
		
		return coldWaterFlowDTO;
	}

}
