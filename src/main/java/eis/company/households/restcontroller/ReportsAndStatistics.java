package eis.company.households.restcontroller;

import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.dto.ChartHouseApartDto;
import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.dto.ElEnFlowDTO;
import eis.company.households.dto.HotCountFlowDTO;
import eis.company.households.service.ReportsService;
import eis.company.households.utility.ReportUtils;



@RestController
public class ReportsAndStatistics {
	
	private ReportsService reportService;
	private ReportUtils reportUtils;
	
	public ReportsAndStatistics(ReportsService reportService, ReportUtils reportUtils) {
		super();
		this.reportService = reportService;
		this.reportUtils = reportUtils;
	}


	/**
	 * REST Счетчики для связи с л/сч.
	 * call JS function openPersonAcnt(idLink)
	 * @return modelandview
	 */
	@GetMapping(value = "/user/count_water/{id}")
	public ResponseEntity<List<AcntCountsDTO>> getTableAcntCounts(@PathVariable("id") Integer id) {
		
		return ResponseEntity.status(OK).body(reportService.getAcntCounts(id));
    }
	
	
	/**
	 * REST Фильтрация данных по расходу тепловой энергии 
	 * 
	 */
	@GetMapping(value="/user/flow_hotreport/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<List<HotCountFlowDTO>> reportFiltrHot(@PathVariable("numUspd") String numUspd, 
			                                           @PathVariable("dateFrom") LocalDate dateFrom){
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return ResponseEntity.status(OK).body(reportService.getHotCountDto(num, dateFrom));
	}
	
	/**
	 * Print of PDF reports
	 * The page of the report of cold water
	 */
	@GetMapping(value = "/user/flow_water_report_pdf/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<String> getColWaterForPDF(@PathVariable("numUspd") String numUspd,
			                                             @PathVariable("dateFrom") LocalDate dateFrom) {
		String numuspd = "%25";
		String url = null;
       	if(!numUspd.equals("0")) {
       	   	numuspd = numUspd + "%25";
       	}
       	String dateCurr = dateFrom.toString().substring(0, 7) + "%25";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%25";
		url = reportUtils.prepUrl("/reports/Housing/user/FlowColdWater&output=", numuspd, dateCurr, datePrev);
			
		return ResponseEntity.status(OK).body(url);
	}
	
	
	/**
	 * Print of PDF reports
	 * The page of the report of hot water 
	 */
	@GetMapping(value = "/user/flow_hot_water_report_pdf/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<String> getHotWaterForPDF(@PathVariable("numUspd") String numUspd,
			                                             @PathVariable("dateFrom") LocalDate dateFrom) {
		String numuspd = "%25";
		String url = null;
       	if(!numUspd.equals("0")) {
       	   	numuspd = numUspd + "%25";
       	}
       	String dateCurr = dateFrom.toString().substring(0, 7) + "%25";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%25";
		url = reportUtils.prepUrl("/reports/Housing/user/FlowHotWater&output=", numuspd, dateCurr, datePrev);
		
		return ResponseEntity.status(OK).body(url);
	}
	
	
	/**
	 * Print of PDF reports
	 * The page of the report of El Energy 
	 */
	@GetMapping(value = "/user/flow_ElEnergy_report_pdf/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<String> getElEnergyForPDF(@PathVariable("numUspd") String numUspd,
			                                             @PathVariable("dateFrom") LocalDate dateFrom) {
		String numuspd = "%25";
		String url = null;
       	if(!numUspd.equals("0")) {
       	   	numuspd = numUspd + "%25";
       	}
       	String dateCurr = dateFrom.toString().substring(0, 7) + "%25";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%25";
		url = reportUtils.prepUrl("/reports/Housing/user/FlowElEnergy&output=", numuspd, dateCurr, datePrev);
		
		return ResponseEntity.status(OK).body(url);
	}
	
	
	/**
	 * Print of PDF reports
	 * The page of the report of Hot Energy 
	 */
	@GetMapping(value = "/user/flow_HotEnergy_report_pdf/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<String> getHotEnergyForPDF(@PathVariable("numUspd") String numUspd,
			                                             @PathVariable("dateFrom") LocalDate dateFrom) {
		String numuspd = "%25";
		String url = null;
       	if(!numUspd.equals("0")) {
       	   	numuspd = numUspd + "%25";
       	}
       	String dateCurr = dateFrom.toString().substring(0, 7) + "%25";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%25";
		url = reportUtils.prepUrl("/reports/Housing/user/FlowHotEnergy&output=", numuspd, dateCurr, datePrev);
		
		return ResponseEntity.status(OK).body(url);
	}
	
	
	/** **********************************************************************************************************************
	 * Запрос на получение данных по C&H воде
	 * для построения диаграммы по дому поквартирно
	 */
	@GetMapping(value="/user/ChartHouseApartdWater/dateFrom/{dateFrom}/dateTo/{dateTo}/idLinkObj/{idLinkObj}")
	public  ResponseEntity<List<ChartHouseApartDto>> makeChartHouseApartColdWater(@PathVariable("dateFrom") LocalDate dateFrom,
			                                                                      @PathVariable("dateTo") LocalDate dateTo,
			                                                                      @PathVariable("idLinkObj") int idLinkObj){
		return ResponseEntity.status(OK).body(reportService.getChartHouseApartDto(dateFrom, dateTo, idLinkObj));
	}
	/**************************************************************************************************************************/
	
	/** ***********************************************************************************************************************
	 * REST Фильтрация для построения диаграммы по холодной воде
	 * 
	 */
	@GetMapping(value="/user/ChartColdWater1/dateFrom/{dateFrom}")
	public  ResponseEntity<List<ColdWaterFlowDTO>> makeChartColdWater(@PathVariable("dateFrom") LocalDate dateFrom){
		return ResponseEntity.status(OK).body(reportService.getWaterFlowDto("%", dateFrom, "1000", "1"));
	}
	
	/**
	 * REST Фильтрация для построения диаграммы по горячей воде
	 * 
	 */
	@GetMapping(value="/user/ChartHotWater/dateFrom/{dateFrom}")
	public  ResponseEntity<List<ColdWaterFlowDTO>> makeChartHotWater(@PathVariable("dateFrom") LocalDate dateFrom){
		return ResponseEntity.status(OK).body(reportService.getWaterFlowDto("%", dateFrom, "1000", "2"));
	}
	
	/**
	 * REST Фильтрация для построения диаграммы по расходу тепловой энергии 
	 * 
	 */
	@GetMapping(value="/user/ChartHotEn/dateFrom/{dateFrom}")
	public ResponseEntity<List<HotCountFlowDTO>> makeChartHotEl(@PathVariable("dateFrom") LocalDate dateFrom){
		return ResponseEntity.status(OK).body(reportService.getHotCountDto("%", dateFrom));
	}
	
	/**
	 * REST Фильтрация данных по расходу эл энергии 
	 * 
	 */
	@GetMapping(value="/user/ChartElEn/dateFrom/{dateFrom}")
	public ResponseEntity<List<ElEnFlowDTO>> makeChartElEn(@PathVariable("dateFrom") LocalDate dateFrom){
		String num = "%";
		return ResponseEntity.status(OK).body(reportService.getElEnFlowDto(num, dateFrom));
	}
	/**************************************************************************************************************************/
	
	
	
	/** ***********************************************************************************************************************
	 * REST Фильтрация данных по расходу холодной воды 
	 * 
	 */
	@GetMapping(value="/user/flow_water_report/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<List<ColdWaterFlowDTO>> reportFiltrCoolWater(@PathVariable("numUspd") String numUspd, 
			                                           @PathVariable("dateFrom") LocalDate dateFrom){
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return ResponseEntity.status(OK).body(reportService.getWaterFlowDto(num, dateFrom, "1000", "1"));
	}
	
	/** 
	 * REST Фильтрация данных по расходу горячей воды 
	 * 
	 */
	@GetMapping(value="/user/flow_water_hot_report/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<List<ColdWaterFlowDTO>> reportFiltrHotWater(@PathVariable("numUspd") String numUspd, 
			                                           @PathVariable("dateFrom") LocalDate dateFrom){
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return ResponseEntity.status(OK).body(reportService.getWaterFlowDto(num, dateFrom, "1000", "2"));
	}
	
	/**
	 * REST Фильтрация данных по расходу эл энергии 
	 * 
	 */
	@GetMapping(value="/user/flow_el_en_report/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public ResponseEntity<List<ElEnFlowDTO>> reportFiltrElEn(@PathVariable("numUspd") String numUspd, 
			                                 @PathVariable("dateFrom") LocalDate dateFrom){
		
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return ResponseEntity.status(OK).body(reportService.getElEnFlowDto(num, dateFrom));
	}
	
    /**************************************************************************************************************************/
}
