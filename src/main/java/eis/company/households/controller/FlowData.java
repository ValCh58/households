package eis.company.households.controller;

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
	
	@GetMapping(value="/user/flow_water_report")
	public ModelAndView getReportFlowWater() {
		ModelAndView modelAndView = new ModelAndView();
		List<ColdWaterFlowDTO> listWaterFlowReport = reportsService.getWaterFlowDto();
		modelAndView.addObject("listWaterFlowReport", listWaterFlowReport);
		modelAndView.setViewName("user/flow_water_report");
		return modelAndView;
		
	}
}
