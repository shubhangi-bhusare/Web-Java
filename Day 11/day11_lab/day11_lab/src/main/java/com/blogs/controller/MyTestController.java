package com.blogs.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//base pattern of the incoming url-pattern ,
//can be supplied at the class level --optional BUT reco.
@RequestMapping("/test") // => can intercept ANY request (GET|POST|PUT|DELETE|PATCH)
public class MyTestController {
	public MyTestController() {
		System.out.println("in ctor of " + getClass());
	}

	// add req handling method to display server side time stamp
	// (i.e a dynamic resp)
	@GetMapping("/test2") // =@RequestMapping(method=GET)
	public ModelAndView renderDynResult() {
		System.out.println("in render dyn res");
		/*
		 * o.s.w.s.ModelAndView : class that holds LVN + results Results are represented
		 * by model attributes (name , value pair) Constructor - ModelAndView(String LVN
		 * , String modelAttrName,Object modelAttrValue)
		 * 
		 */
		return new ModelAndView("/test/display", 
				"server_ts", LocalDateTime.now());
		/*
		 * Handler rets ModelAndView  to D.S
		 * D.S sends LVN --> V.R --> 
		 * AVN (/WEB-INF/views/test/display.jsp --> D.S
		 * --> adds model attribute/s under request scope 
		 * D.S forwards the clnt to JSP
		 * 
		 */

	}

}
