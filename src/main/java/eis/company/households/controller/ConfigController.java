package eis.company.households.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ConfigController {

	/**
	 * Вызов страницы /admin/spr_object конфигурация железа
	 * @author chvaleriy
	 *
	 */
	@GetMapping(value = "/admin/spr_object")
	public ModelAndView sprObject() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/spr_object");
		return modelAndView;
	}
	
	
	/**
	 * Вызов страницы /user/nodes_uk конфигурация УК
	 * @author chvaleriy
	 *
	 */
	@GetMapping(value = "/user/nodes_uk")
	public ModelAndView sprObjectUk() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/nodes_uk");
		return modelAndView;
	}
}
