package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.MyConst;
import eis.company.households.dto.ChartHouseApartDto;

@Component
public class QueryChartHouseApart {
	
	private List<ChartHouseApartDto> chartHouseApartDto = new ArrayList<ChartHouseApartDto>();
	@PersistenceContext(name="housingEntityManager") private EntityManager em;
	
	public List<ChartHouseApartDto> getQueryChartHouseApart(String timeStamp, String timeStampPrev,int houseID){
	chartHouseApartDto.clear();
    
	@SuppressWarnings("unchecked")
	List<Tuple> list = em.createNativeQuery("select * from(\r\n"
			+ "select curr.time_stamp as timeStamp, curr.room as room, curr.num_acnt as numAcnt, \r\n"
			+ "curr.name_count as nameCount, (curr.count_w/curr.num_rat) as countW, (prev.count_w/prev.num_rat)  as prevCountW,\r\n"
			+ "IF(period_diff(date_format(curr.date_expire, \"%Y%m\"), date_format(curr.time_stamp, \"%Y%m\")) > 0, (curr.count_w - prev.count_w)/prev.num_rat, '0') as diffCountW, \r\n"
			+ "curr.serial_num as serialNum, curr.date_expire as dateExpire, \r\n"
			+ "curr.factory_number_uspd as factoryNumberUspd, curr.type_count as typeCount \r\n"
			+ " from \r\n"
			+ "(SELECT max(time_stamp) as time_stamp, room, num_acnt, name_count, max(count_w) as count_w, serial_num, date_expire, factory_number_uspd, type_count, num_rat  \r\n"
			+ "FROM housing.report_all \r\n"
			+ "where  type_count = :typeCount1 and time_stamp like :timeStamp and id_house = (SELECT id_object FROM housing.link_object_uk where id_link_object = :houseID) \r\n"
			+ "group by factory_number_uspd\r\n"
			+ "order by factory_number_uspd) curr left join \r\n"
			+ "(SELECT max(time_stamp) as time_stamp, room, num_acnt, name_count, max(count_w) as count_w, serial_num, date_expire, factory_number_uspd, type_count, num_rat \r\n"
			+ "FROM housing.report_all  \r\n"
			+ "where  type_count = :typeCount1 and time_stamp like :timeStampPrev and id_house = (SELECT id_object FROM housing.link_object_uk where id_link_object = :houseID) \r\n"
			+ "group by factory_number_uspd\r\n"
			+ "order by factory_number_uspd) prev \r\n"
			+ "on curr.factory_number_uspd = prev.factory_number_uspd\r\n"
			+ "union all\r\n"
			+ "select curr.time_stamp as timeStamp, curr.room as room, curr.num_acnt as numAcnt, \r\n"
			+ "curr.name_count as nameCount, (curr.count_w/curr.num_rat) as countW, (prev.count_w/prev.num_rat)  as prevCountW,\r\n"
			+ "IF(period_diff(date_format(curr.date_expire, \"%Y%m\"), date_format(curr.time_stamp, \"%Y%m\")) > 0, (curr.count_w - prev.count_w)/prev.num_rat, '0') as diffCountW, \r\n"
			+ "curr.serial_num as serialNum, curr.date_expire as dateExpire, \r\n"
			+ "curr.factory_number_uspd as factoryNumberUspd, curr.type_count as typeCount \r\n"
			+ " from \r\n"
			+ "(SELECT max(time_stamp) as time_stamp, room, num_acnt, name_count, max(count_w) as count_w, serial_num, date_expire, factory_number_uspd, type_count, num_rat  \r\n"
			+ "FROM housing.report_all \r\n"
			+ "where  type_count = :typeCount2 and time_stamp like :timeStamp and id_house = (SELECT id_object FROM housing.link_object_uk where id_link_object = :houseID) \r\n"
			+ "group by factory_number_uspd\r\n"
			+ "order by factory_number_uspd) curr left join \r\n"
			+ "(SELECT max(time_stamp) as time_stamp, room, num_acnt, name_count, max(count_w) as count_w, serial_num, date_expire, factory_number_uspd, type_count, num_rat \r\n"
			+ "FROM housing.report_all  \r\n"
			+ "where  type_count = :typeCount2 and time_stamp like :timeStampPrev and id_house = (SELECT id_object FROM housing.link_object_uk where id_link_object = :houseID) \r\n"
			+ "group by factory_number_uspd\r\n"
			+ "order by factory_number_uspd) prev \r\n"
			+ "on curr.factory_number_uspd = prev.factory_number_uspd)a\r\n"
			+ "order by factoryNumberUspd, typeCount\r\n"
			+ "",	
			 Tuple.class)
	        .setParameter("timeStamp", timeStamp)
            .setParameter("timeStampPrev", timeStampPrev)
            .setParameter("typeCount1", MyConst.STR_COOL_WATER_COUNT)
            .setParameter("typeCount2", MyConst.STR_HOT_WATER_COUNT)
            .setParameter("houseID", houseID)
            .getResultList();
	
	if(list.isEmpty()) {return null;}
	
	for(Tuple t : list) {
		ChartHouseApartDto cw = new ChartHouseApartDto(
				((java.sql.Timestamp) t.get("timeStamp")).toLocalDateTime(),
				t.get("room")!=null?(String)t.get("room"):"нет данных",
				t.get("numAcnt")!=null?(String)t.get("numAcnt"):"нет данных",
				t.get("nameCount")!=null?(String)t.get("nameCount"):"нет данных",
				Double.valueOf(t.get("diffCountW") != null ? t.get("diffCountW").toString():"0.0"),
				(int)t.get("typeCount")
				);
		
		chartHouseApartDto.add(cw);	
		}
			
	
	return chartHouseApartDto;
	}

}
