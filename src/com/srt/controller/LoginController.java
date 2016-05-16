package com.srt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>This class provide controller logic to render login page.</p>
 * @author Ajay
 *
 */
@Controller
public class LoginController {
	private static final Class clazz = LoginController.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	/**
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = {"/", "/login**",}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		logger.info("Start: Login Controller - Logout : {} , Error : {}", logout, error);
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		model.setViewName("login");
		logger.info("End: Login Controller");
		return model;

	}
}
