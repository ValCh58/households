package eis.company.households.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.dto.ElEnFlowDTO;
import eis.company.households.dto.HotCountFlowDTO;
import eis.company.households.dto.LinkObjectDTO;
import eis.company.households.queres.QueryMenuObjects;
import eis.company.households.service.ReportsService;

@Controller
public class FlowData {

	private ReportsService reportsService;
	private QueryMenuObjects queryMenuObj;
	
	@Autowired
	public FlowData(ReportsService reportsService, QueryMenuObjects queryMenuObj) {
		super();
		this.reportsService = reportsService;
		this.queryMenuObj = queryMenuObj;
	}

	/** **********************************************************************************************************
	 *  Make menu for all objects
	 */
	
	@GetMapping(value="/user/ChartForHouseApart")
	public ModelAndView makeChartHousApart() {
		
		ModelAndView modelAndView = new ModelAndView();
		List<LinkObjectDTO> menuObjects = queryMenuObj.makeTree();
		modelAndView.addObject("menuObjects", menuObjects);
		modelAndView.setViewName("user/chart_house_apart");
		
		return modelAndView;
	}
	
	/** 
	 * Chart for cold water
	 * 
	 */
	@GetMapping(value="/user/ChartColdWater1")
	public ModelAndView makeChartColdWater() {
		LocalDate dateFrom = LocalDate.now();
		
		List<ColdWaterFlowDTO> listWaterFlowReport = reportsService.getWaterFlowDto("%", dateFrom, "1000", "1");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listWaterFlowReport", listWaterFlowReport);
		modelAndView.setViewName("user/ChartColdWater1");
		return modelAndView;
		
	}
	
	/**
	 * Chart for hot water
	 * 
	 */
	@GetMapping(value="/user/ChartHotWater")
	public ModelAndView makeChartHotWater() {
		LocalDate dateFrom = LocalDate.now();
		List<ColdWaterFlowDTO> listWaterFlowReport = reportsService.getWaterFlowDto("%", dateFrom, "1000", "2");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listWaterFlowReport", listWaterFlowReport);
		modelAndView.setViewName("user/ChartHotWater");
		return modelAndView;
	}
	
	/**
	 * Chart for hot energy
	 * @return ModelAndView
	 */
	@GetMapping(value="/user/ChartHotEn")
	public ModelAndView makeChartHotEn() {
		LocalDate dateFrom = LocalDate.now();
	  	ModelAndView modelAndView = new ModelAndView();
		List<HotCountFlowDTO> listHotFlowReport = reportsService.getHotCountDto("%", dateFrom);
		modelAndView.addObject("listHotFlowReport", listHotFlowReport);
		modelAndView.setViewName("user/ChartHotEn");
		return modelAndView;
	}
	
	/**
	 * Chart for расходу Эл. энергии
	 * @return ModelAndView
	 */
	@GetMapping(value="/user/ChartElEn")
	public ModelAndView makeChartElEn() {
		LocalDate dateFrom = LocalDate.now();
		ModelAndView modelAndView = new ModelAndView();
		List<ElEnFlowDTO> listElEnReport = reportsService.getElEnFlowDto("%", dateFrom);
		modelAndView.addObject("listElEnReport", listElEnReport);
		modelAndView.setViewName("user/ChartElEn");
		return modelAndView;
	}
    //************************************************************************************************************ 

	/**
	 * Отчет по расходу тепловой энергии
	 * @return ModelAndView
	 */
	@GetMapping(value="/user/flow_hot_report")
	public ModelAndView getReportFlowHot() {
		LocalDate dateFrom = LocalDate.now();
	    
		ModelAndView modelAndView = new ModelAndView();
		List<HotCountFlowDTO> listHotFlowReport = reportsService.getHotCountDto("%", dateFrom);
		modelAndView.addObject("listHotFlowReport", listHotFlowReport);
		modelAndView.setViewName("user/flow_hot_report");
		return modelAndView;
	}
	
	
	/**
	 * Отчет по расходу холодной воды
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
	
	/**
	 * Отчет по расходу Эл. энергии
	 * @return ModelAndView
	 */
	@GetMapping(value="/user/flow_el_en_report")
	public ModelAndView getReportFlowElEn() {
		LocalDate dateFrom = LocalDate.now();
		
		ModelAndView modelAndView = new ModelAndView();
		List<ElEnFlowDTO> listElEnReport = reportsService.getElEnFlowDto("%", dateFrom);
		modelAndView.addObject("listElEnReport", listElEnReport);
		modelAndView.setViewName("user/flow_el_en_report");
		return modelAndView;
	}
	
	
}
