package eis.company.households.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.service.ReportsService;

@RestController
public class ReportsAndStatistics {
	
	@Autowired ReportsService reportService;
	
	/**
	 * Счетчики для связи с л/сч.
	 * call JS function openPersonAcnt(idLink)
	 * @return modelandview
	 */
	@GetMapping(value = "/user/count_water/{id}")
	public List<AcntCountsDTO> reportCoolWater(@PathVariable("id") Integer id) {
		
		List<AcntCountsDTO> listAcnt = reportService.getAcntCounts(id);
		
		return listAcnt.isEmpty() ? null : listAcnt;
	}

}
