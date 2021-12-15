package eis.company.households.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportsAndStatistics {
	
	
	/**
	 * Получение отчета по расходу холодной воды
	 * 
	 * @return modelandview
	 */
	@GetMapping(value = "/user/cool_water")
	public ModelAndView reportCoolWater() {
		ModelAndView modelandview = new ModelAndView();
		
		modelandview.setViewName("user/cool_water");
		return modelandview;
	}

}
