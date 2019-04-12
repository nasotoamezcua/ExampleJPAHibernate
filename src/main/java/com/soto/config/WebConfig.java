package com.soto.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import com.soto.utils.SimpleCORSFilter;

//Esta configuracion solo es para utilizar las vistas jsp en @Controller
public class WebConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		FilterRegistration corsFilterReg = servletContext.addFilter("simpleCORSFilter", SimpleCORSFilter.class);
		corsFilterReg.addMappingForServletNames(null, false, "/*");
	}

}
