package eis.company.households.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Вызов страницы /admin/spr_object
 * @author chvaleriy
 *
 */
@Controller
public class ConfigController {

	@GetMapping(value = "/admin/spr_object")
	public ModelAndView sprObject() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/spr_object");
		return modelAndView;
	}
	
	@GetMapping(value = "/user/nodes_uk")
	public ModelAndView sprObjectUk() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/nodes_uk");
		return modelAndView;
	}
}
