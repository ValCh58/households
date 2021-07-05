package eis.company.households.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Вызов страницы /admin/spr_object
 * @author Admin
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
}
