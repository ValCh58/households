package eis.company.households.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eis.company.households.dto.AlarmDTO;
import eis.company.households.dto.CountWaterDTO;
import eis.company.households.dto.ServerObjDTO;
import eis.company.households.modeleis.CountElEn;
import eis.company.households.modeleis.CountHeat;
import eis.company.households.modeleis.Measuring;
import eis.company.households.modeleis.RawData;
import eis.company.households.queres.QueryComSrvDto;
import eis.company.households.service.ObjectAdminService;

@Controller
public class ObjectsAdmin {

	private QueryComSrvDto queryComSrv;
	private ObjectAdminService objAdmSrv;
	
	@Autowired	
	public ObjectsAdmin(QueryComSrvDto queryComSrv, ObjectAdminService objAdmSrv) {
		super();
		this.queryComSrv = queryComSrv;
		this.objAdmSrv = objAdmSrv;
	}


	/**
	 * Получение данных о связи с серверами УСПД
	 * @return modelView
	 */
	@GetMapping(value = "/admin/object_server")
	public ModelAndView viewServers() {
		ModelAndView modelView = new ModelAndView();
		List<ServerObjDTO> listComSrv = queryComSrv.retComSrvDto();
		modelView.addObject("listComSrv", listComSrv);
		modelView.setViewName("admin/object_server");
		return modelView;
	}
	
	
	/**
	 * Получение данных от УСПД
	 * @return modelView
	 */
	@GetMapping(value = "/admin/object_uspd")
	public ModelAndView viewUspd() {
		ModelAndView modelView = new ModelAndView();
		List<Measuring> listMeasuring = objAdmSrv.retUspdObj();
		modelView.addObject("listMeasuring", listMeasuring);
		modelView.setViewName("admin/object_uspd");
		return modelView;
	}
	
	/**
	 * Получение данных от водянных счетчиков.
	 * @return modelView
	 */
	@GetMapping(value="/admin/object_count")
	public ModelAndView viewCounts() {
		ModelAndView modelView = new ModelAndView();
		//List<CountWater> listCountWater = objAdmSrv.retCountWater();
		List<CountWaterDTO> listCountWater = objAdmSrv.retCountWater();
		modelView.addObject("listCountWater", listCountWater);
		modelView.setViewName("admin/object_count");
		return modelView;
	}
	
	/**
	 * Получение данных от эл. счетчиков.
	 * @return modelView
	 */
	@GetMapping(value="/admin/object_count_el")
	public ModelAndView viewCountsEl() {
		ModelAndView modelView = new ModelAndView();
		List<CountElEn> listCountElEn = objAdmSrv.retCountElEn();
		modelView.addObject("listCountElEn", listCountElEn);
		modelView.setViewName("admin/object_count_el");
		return modelView;
	}
	
	/**
	 * Получение данных от тепловых счетчиков.
	 * @return modelView
	 */
	@GetMapping(value="/admin/object_count_he")
	public ModelAndView viewCountHe() {
		ModelAndView modelView = new ModelAndView();
		List<CountHeat> listCountHe = objAdmSrv.retCountHeater();
		modelView.addObject("listCountHe", listCountHe);
		modelView.setViewName("admin/object_count_he");
		return modelView;
	}
	
	/**
	 * Получение сырых данных от серверов связи
	 * @return modelView
	 */
	@GetMapping(value="/admin/monit_data")
	public ModelAndView viewDataSrevers() {
		ModelAndView modelAndView = new ModelAndView();
		List<RawData> listRawData = objAdmSrv.retRawData();
		modelAndView.addObject("listRawData", listRawData);
		modelAndView.setViewName("admin/monit_data");
		return modelAndView;
	}
	
	/**
	 * Получение аварийных сообщений от УСПД
	 * @return modelView
	 */
	@GetMapping(value="/admin/message_obj")
	public ModelAndView viewMessageObj() {
		ModelAndView modelAndView = new ModelAndView();
		List<AlarmDTO> listAlarmDto = objAdmSrv.retAlarmMsg();
		modelAndView.addObject("listAlarmDto", listAlarmDto);
		modelAndView.setViewName("admin/message_obj");
		return modelAndView;
	}
	
}
