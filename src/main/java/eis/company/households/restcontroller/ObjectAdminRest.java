package eis.company.households.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.AlarmDTO;
import eis.company.households.dto.CountWaterDTO;
import eis.company.households.modeleis.CountElEn;
import eis.company.households.modeleis.CountHeat;
import eis.company.households.modeleis.Measuring;
import eis.company.households.modeleis.RawData;
import eis.company.households.service.ObjectAdminService;

@RestController
public class ObjectAdminRest {
	
	@Autowired private ObjectAdminService objAdmSrv; 
	
	/**
	 * REST УСПД фильтрация
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_uspd/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public List<Measuring> updateViewUspdPar(@PathVariable("numUspd") String numUspd,
			                                 @PathVariable("dateFrom")  LocalDate dateFrom, 
			                                 @PathVariable("dateTo")  LocalDate dateTo)
			                                		 //throws ResourceNotFoundException{
	                                            {
		
		List<Measuring> list = objAdmSrv.retUpdateUspdObj(numUspd, dateFrom, dateTo); 
		return list.isEmpty() ? null : list;
	}
	
	/**
	 * REST Счетчики фильтрация
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_count/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public List<CountWaterDTO> updateViewCountWaterPar(@PathVariable("numUspd") String numUspd,
		                                            @PathVariable("dateFrom")  LocalDate dateFrom, 
		                                            @PathVariable("dateTo")  LocalDate dateTo) {
		
		//List<CountWater> list = objAdmSrv.retUpdateCountWaterObj(numUspd, dateFrom, dateTo);
		List<CountWaterDTO> list = objAdmSrv.retUpdateCountWaterObj(numUspd, dateFrom, dateTo);
		
		return list;//При отсутствии данных list = null//
	}
	
	/**
	 * REST Эл. счетчики фильтрация
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_count_el/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public List<CountElEn> updateViewCountElEnPar(@PathVariable("numUspd") String numUspd,
		                                            @PathVariable("dateFrom")  LocalDate dateFrom, 
		                                            @PathVariable("dateTo")  LocalDate dateTo) {
		
		List<CountElEn> list = objAdmSrv.retUpdateCountElEnObj(numUspd, dateFrom, dateTo); 
		
		if(list.isEmpty()) {list = null;}
		return list;
	}
	
	/**
	 * REST Row данные от серверов, фильтрация
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/monit_rawdata/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public List<RawData> updateViewRawData(@PathVariable("numUspd") String numUspd,
		                                            @PathVariable("dateFrom")  LocalDate dateFrom, 
		                                            @PathVariable("dateTo")  LocalDate dateTo) {
		
		List<RawData> list = objAdmSrv.retUpdateRawData(numUspd, dateFrom, dateTo); 
		if(list.isEmpty()) {list = null;}
		return list;
	}
	
	/**
	 * REST Аварийные сообщения
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value="/admin/message_obj/idAlarm/{idAlarm}")
	public AlarmDTO updateAlarm(@PathVariable("idAlarm") Integer idAlarm) {
		return objAdmSrv.retUpdAlarmDTO(idAlarm);
	}
	
	/**
	 * REST Тепл. счетчики фильтрация
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value="/admin/object_count_he/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public List<CountHeat> updateViwHeaterData(@PathVariable("numUspd") String numUspd,
                                               @PathVariable("dateFrom")  LocalDate dateFrom, 
                                               @PathVariable("dateTo")  LocalDate dateTo){
		List<CountHeat> list = objAdmSrv.retUpdCountHeater(numUspd, dateFrom, dateTo);
		if(list.isEmpty()) {list = null;}
		return list;
	}

}
