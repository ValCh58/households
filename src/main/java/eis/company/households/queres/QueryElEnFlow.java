package eis.company.households.queres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.ElEnFlowDTO;

@Component
public class QueryElEnFlow {
	
	private List<ElEnFlowDTO> listElEnFlowDTO = new ArrayList<>();
	@PersistenceContext(name="housingEntityManager") private EntityManager em;
	
	public List<ElEnFlowDTO> getQueryResult(String factoryNumberUspd, String timeStamp, String timeStampPrev){
	
		listElEnFlowDTO.clear();
		
		@SuppressWarnings("unchecked")
		List<Tuple> list = em.createNativeQuery(
			  "select curr.time_stamp as timeStamp, curr.address_loc as addressLoc, curr.num_acnt as numAcnt, curr.name_count as nameCount, "
			  + "curr.TARIFF_EN as tarifEn, prev.TARIFF_EN  as prevTarifEn, \r\n"
			  + " IF(period_diff(date_format(curr.date_expire, \"%Y%m\"), date_format(curr.time_stamp, \"%Y%m\")) > 0, (curr.TARIFF_EN - prev.TARIFF_EN), '0') as diffTarifEn, \r\n"
			  + " curr.serial_num as serialNum, curr.date_expire as dateExpire, curr.factory_number_uspd as factoryNumberUspd, if(curr.type_tarif = 0, 'T1', 'T2') as typeTarif from \r\n"
			  + " (SELECT max(time_stamp) as time_stamp, address_loc, num_acnt, name_count, max(TARIFF_EN) as TARIFF_EN, "
			  + " serial_num, date_expire, factory_number_uspd, TYPE_TARIF  \r\n"
			  + " FROM housing.report_el  \r\n"
			  + " where  factory_number_uspd like :factoryNumberUspd and count_type = 3 and time_stamp like :timeStamp \r\n"
			  + " group by factory_number_uspd,TYPE_TARIF order by factory_number_uspd)  curr left join\r\n"
			  + "  (SELECT max(TARIFF_EN) as TARIFF_EN, factory_number_uspd, TYPE_TARIF FROM housing.report_el  \r\n"
			  + " where  factory_number_uspd like :factoryNumberUspd and count_type = 3 and time_stamp like :timeStampPrev \r\n"
			  + " group by factory_number_uspd,TYPE_TARIF order by factory_number_uspd) prev \r\n"
			  + " on (curr.factory_number_uspd = prev.factory_number_uspd and curr.type_tarif = prev.type_tarif)", 
		      Tuple.class).setParameter("factoryNumberUspd", factoryNumberUspd)
				          .setParameter("timeStamp", timeStamp)
				          .setParameter("timeStampPrev", timeStampPrev)
				          .getResultList();
				
		if(list.isEmpty()) {return null;}
		
		for(Tuple t : list) {
			ElEnFlowDTO cEl = new ElEnFlowDTO(
					((java.sql.Timestamp) t.get("timeStamp")).toLocalDateTime(),
					t.get("addressLoc")!=null?(String)t.get("addressLoc"):"нет данных",
					t.get("numAcnt")!=null?(String)t.get("numAcnt"):"нет данных",
					t.get("nameCount")!=null?(String)t.get("nameCount"):"нет данных",
					Double.valueOf(t.get("tarifEn") != null ? t.get("tarifEn").toString():"0.0"),
					Double.valueOf(t.get("prevTarifEn") != null ? t.get("prevTarifEn").toString():"0.0"),
					Double.valueOf(t.get("diffTarifEn") != null ? t.get("diffTarifEn").toString():"0.0"),
					t.get("serialNum")!=null?(String)t.get("serialNum"):"нет данных",
					t.get("dateExpire")!=null?((java.sql.Date) t.get("dateExpire")).toLocalDate():LocalDate.now(),
					(String)t.get("factoryNumberUspd"),
					(String)t.get("typeTarif")
					);
			
			listElEnFlowDTO.add(cEl);	
			}
		
		return listElEnFlowDTO;
	}
	
	
	

}
