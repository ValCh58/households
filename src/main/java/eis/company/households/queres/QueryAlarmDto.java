package eis.company.households.queres;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.AlarmDTO;

@Component
public class QueryAlarmDto {

	List<AlarmDTO> retList = new ArrayList<AlarmDTO>();
	
	@PersistenceContext(unitName = "eisystemsEntityManager")
	private EntityManager em;

	/**
	 * Запрос без параметров для построения таблицы
	 * @return List<AlarmDTO>
	 */
	public List<AlarmDTO> retAlarmDto() {
		retList.clear();

		Query q = em.createNativeQuery(
				" select al.ID_ALARM as idAlarm, al.ID_MEASURING as idMeasuring, al.FACTORY_NUMBER_USPD as factoryNumber, "
						+ "al.TIME_STAMP as timeStamp, al.MSG_TEXT as msgText, al.ACTIVE as active from(\r\n"
						+ " Select al1.ID_ALARM, al1.ID_MEASURING, al1.FACTORY_NUMBER_USPD, al1.TIME_STAMP, \r\n"
						+ "CONCAT(al1.msg_text, ' ',  al1.THRESHOLD_COUNTER_USPD) as msg_text,  al1.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.THRESHOLD_COUNTER_USPD, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where a.OUTPUT_STATUS_USPD = '01' OR (a.OUTPUT_STATUS_USPD = '11' AND a.THRESHOLD_COUNTER_USPD IS NOT NULL)\r\n"
						+ ")al1\r\n" + " union all\r\n"
						+ " Select al2.ID_ALARM, al2.ID_MEASURING, al2.FACTORY_NUMBER_USPD, al2.TIME_STAMP, al2.msg_text, al2.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.THRESHOLD_COUNTER_USPD, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where (a.OUTPUT_STATUS_USPD = '10'  AND a.THRESHOLD_COUNTER_USPD IS NULL) OR (a.OUTPUT_STATUS_USPD = '11' AND a.THRESHOLD_COUNTER_USPD IS NULL)\r\n"
						+ ")al2\r\n" + " union all\r\n"
						+ " Select al3.ID_ALARM, al3.ID_MEASURING, al3.FACTORY_NUMBER_USPD, al3.TIME_STAMP, \r\n"
						+ "CONCAT(al3.msg_text, ' ',  al3.ALARM_STATUS_ANALOG_SENSOR) as msg_text,  al3.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.ALARM_STATUS_ANALOG_SENSOR, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where a.OUTPUT_STATUS_USPD IS NULL AND a.ALARM_STATUS_ANALOG_SENSOR IS NOT NULL\r\n"
						+ ")al3\r\n" + " union all\r\n"
						+ " Select al4.ID_ALARM, al4.ID_MEASURING, al4.FACTORY_NUMBER_USPD, al4.TIME_STAMP, \r\n"
						+ "CONCAT(al4.msg_text, ' ',  al4.POWER_BATTERY_USPD, ' ', 'в.') as msg_text,  al4.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.POWER_BATTERY_USPD, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where a.OUTPUT_STATUS_USPD IS NULL AND a.LOW_POWER_BATTERY_USPD = '1'\r\n"
						+ ")al4)al order by al.ACTIVE, al.TIME_STAMP desc limit 500",
				Tuple.class);

		@SuppressWarnings("unchecked")
		List<Tuple> list = q.getResultList();
		if (list.isEmpty())
			return null;

		for (Tuple t : list) {
			AlarmDTO ad = new AlarmDTO(
					                   (int) t.get("idAlarm"), 
					                   (int) t.get("idMeasuring"),
					                   (String) t.get("factoryNumber"), 
					                   ((Timestamp) t.get("timeStamp")).toLocalDateTime(), 
					                   (String) t.get("msgText"),
					                   (String) t.get("active")
					                   );
			retList.add(ad);
		}
		
		return retList;
	}
	
	/**
	 * Запрос с ID параметром для обновления строки таблицы
	 * @param idAlarm
	 * @return AlarmDTO
	 */
	public AlarmDTO retAlarmDto(Integer idAlarm){
		
		Query q = em.createNativeQuery(
				" select al.ID_ALARM as idAlarm, al.ID_MEASURING as idMeasuring, al.FACTORY_NUMBER_USPD as factoryNumber, "
						+ "al.TIME_STAMP as timeStamp, al.MSG_TEXT as msgText, al.ACTIVE as active from(\r\n"
						+ " Select al1.ID_ALARM, al1.ID_MEASURING, al1.FACTORY_NUMBER_USPD, al1.TIME_STAMP, \r\n"
						+ "CONCAT(al1.msg_text, ' ',  al1.THRESHOLD_COUNTER_USPD) as msg_text,  al1.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.THRESHOLD_COUNTER_USPD, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where a.OUTPUT_STATUS_USPD = '01' OR (a.OUTPUT_STATUS_USPD = '11' AND a.THRESHOLD_COUNTER_USPD IS NOT NULL)\r\n"
						+ ")al1\r\n" + " union all\r\n"
						+ " Select al2.ID_ALARM, al2.ID_MEASURING, al2.FACTORY_NUMBER_USPD, al2.TIME_STAMP, al2.msg_text, al2.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.THRESHOLD_COUNTER_USPD, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where (a.OUTPUT_STATUS_USPD = '10'  AND a.THRESHOLD_COUNTER_USPD IS NULL) OR (a.OUTPUT_STATUS_USPD = '11' AND a.THRESHOLD_COUNTER_USPD IS NULL)\r\n"
						+ ")al2\r\n" + " union all\r\n"
						+ " Select al3.ID_ALARM, al3.ID_MEASURING, al3.FACTORY_NUMBER_USPD, al3.TIME_STAMP, \r\n"
						+ "CONCAT(al3.msg_text, ' ',  al3.ALARM_STATUS_ANALOG_SENSOR) as msg_text,  al3.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.ALARM_STATUS_ANALOG_SENSOR, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where a.OUTPUT_STATUS_USPD IS NULL AND a.ALARM_STATUS_ANALOG_SENSOR IS NOT NULL\r\n"
						+ ")al3\r\n" + " union all\r\n"
						+ " Select al4.ID_ALARM, al4.ID_MEASURING, al4.FACTORY_NUMBER_USPD, al4.TIME_STAMP, \r\n"
						+ "CONCAT(al4.msg_text, ' ',  al4.POWER_BATTERY_USPD, ' ', 'в.') as msg_text,  al4.ACTIVE\r\n"
						+ " FROM(\r\n"
						+ " SELECT a.ID_ALARM, a.ID_MEASURING, a.FACTORY_NUMBER_USPD, a.POWER_BATTERY_USPD, a.TIME_STAMP,   \r\n"
						+ "(select b.MSG_TEXT from eisystems.type_alarm_msg b where b.ID_TYPE_ALARM_MSG = a.ID_TYPE_ALARM_MSG) as msg_text, a.ACTIVE \r\n"
						+ " FROM eisystems.alarm a where a.OUTPUT_STATUS_USPD IS NULL AND a.LOW_POWER_BATTERY_USPD = '1'\r\n"
						+ ")al4)al where al.ID_ALARM = ?1",
				Tuple.class).setParameter(1, idAlarm);
		
		Tuple tupleSingl =  (Tuple) q.getSingleResult();
		if(tupleSingl == null) return null;
		
		AlarmDTO ad = new AlarmDTO(
                (int) tupleSingl.get("idAlarm"), 
                (int) tupleSingl.get("idMeasuring"),
                (String) tupleSingl.get("factoryNumber"), 
                ((Timestamp) tupleSingl.get("timeStamp")).toLocalDateTime(), 
                (String) tupleSingl.get("msgText"),
                (String) tupleSingl.get("active")
                );
		
		
		return ad == null ? null : ad;
	}
}
