package eis.company.households.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.service.ReportsService;

@Controller
public class FlowData {

	@Autowired private ReportsService reportsService;
	
	/**
	 * Отчет по расходу холодной воды
	 * 
	 * @return ModelAndView
	 */
	@GetMapping(value="/user/flow_water_report")
	public ModelAndView getReportFlowWater() {
		LocalDate dateFrom = LocalDate.now();
						
		ModelAndView modelAndView = new ModelAndView();
		List<ColdWaterFlowDTO> listWaterFlowReport = reportsService.getWaterFlowDto("%", dateFrom, "1000", "1");
		modelAndView.addObject("listWaterFlowReport", listWaterFlowReport);
		modelAndView.setViewName("user/flow_water_report");
		return modelAndView;
		
	}
	
	/**
	 * Отчет по расходу горячей воды
	 * 
	 * @return ModelAndView
	 */
	@GetMapping(value="/user/flow_water_hot_report")
	public ModelAndView getReportFlowWaterHot() {
		LocalDate dateFrom = LocalDate.now();
						
		ModelAndView modelAndView = new ModelAndView();
		List<ColdWaterFlowDTO> listWaterFlowReport = reportsService.getWaterFlowDto("%", dateFrom, "1000", "2");
		modelAndView.addObject("listWaterFlowReport", listWaterFlowReport);
		modelAndView.setViewName("user/flow_water_hot_report");
		return modelAndView;
		
	}
}
