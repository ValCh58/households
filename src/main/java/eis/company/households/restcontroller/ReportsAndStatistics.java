package eis.company.households.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.dto.ElEnFlowDTO;
import eis.company.households.dto.HotCountFlowDTO;
import eis.company.households.service.ReportsService;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class ReportsAndStatistics {
	
	private final ReportsService reportService;
	

	/**
	 * REST Счетчики для связи с л/сч.
	 * call JS function openPersonAcnt(idLink)
	 * @return modelandview
	 */
	@GetMapping(value = "/user/count_water/{id}")
	public List<AcntCountsDTO> getTableAcntCounts(@PathVariable("id") Integer id) {
		
		List<AcntCountsDTO> listAcnt = reportService.getAcntCounts(id);
		
		return listAcnt.isEmpty() ? null : listAcnt;
	}
	
	
	/**
	 * REST Фильтрация данных по расходу тепловой энергии 
	 * 
	 */
	@GetMapping(value="/user/flow_hotreport/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public List<HotCountFlowDTO> reportFiltrHot(@PathVariable("numUspd") String numUspd, 
			                                           @PathVariable("dateFrom") LocalDate dateFrom){
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return reportService.getHotCountDto(num, dateFrom);
	}
	
	
	/**
	 * REST Фильтрация данных по расходу холодной воды 
	 * 
	 */
	@GetMapping(value="/user/flow_water_report/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public List<ColdWaterFlowDTO> reportFiltrCoolWater(@PathVariable("numUspd") String numUspd, 
			                                           @PathVariable("dateFrom") LocalDate dateFrom){
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return reportService.getWaterFlowDto(num, dateFrom, "1000", "1");
	}
	
	/**
	 * REST Фильтрация данных по расходу горячей воды 
	 * 
	 */
	@GetMapping(value="/user/flow_water_hot_report/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public List<ColdWaterFlowDTO> reportFiltrHotWater(@PathVariable("numUspd") String numUspd, 
			                                           @PathVariable("dateFrom") LocalDate dateFrom){
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return reportService.getWaterFlowDto(num, dateFrom, "1000", "2");
	}
	
	/**
	 * REST Фильтрация данных по расходу эл энергии 
	 * 
	 */
	@GetMapping(value="/user/flow_el_en_report/numUspd/{numUspd}/dateFrom/{dateFrom}")
	public List<ElEnFlowDTO> reportFiltrElEn(@PathVariable("numUspd") String numUspd, 
			                                 @PathVariable("dateFrom") LocalDate dateFrom){
		
		String num = numUspd.indexOf("0")==0 ? "%" : numUspd + "%";
		return reportService.getElEnFlowDto(num, dateFrom);
	}
	

}
