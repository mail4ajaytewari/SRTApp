package com.srt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.srt.util.SessionManagementUtil;

/**
 * <p>This class act as controller for home page</p>
 * @author Ajay
 *
 */
@Controller
public class HomeController {
	private static final Class clazz = HomeController.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	/**
	 * <p>URI /home is mapped to this method to render the home page</p>
	 * @return ModelAndView
	 */
	@RequestMapping(value = {"/home**"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		logger.info("Start: Home Page");
		ModelAndView model = new ModelAndView();
		String rollNo = (String) SessionManagementUtil.getSession().getAttribute("rollNo");;
		model.addObject("rollNo", rollNo);
		model.setViewName("home");
			
		logger.info("End: Home Page");
		return model;
	}
}
