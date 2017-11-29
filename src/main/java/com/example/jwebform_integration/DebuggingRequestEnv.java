package com.example.jwebform_integration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jwebform.env.Env;

public class DebuggingRequestEnv extends Env{
	
	private static final Logger log = LoggerFactory.getLogger(DebuggingRequestEnv.class);

	public DebuggingRequestEnv(HttpServletRequest request) {
		super(paramname -> {
			String val = request.getParameter(paramname);
			log.debug(String.format("getParameter %s -> %s", paramname, val));
			return val;
		}, // Request
				paramname -> {
					String val = (String)request.getSession().getAttribute(paramname);
					log.debug(String.format("getSession %s -> %s", paramname, val));
					return val;
				},  // SessionGet
				(k, v) -> {
					request.getSession().setAttribute(k, v);
					log.debug(String.format("setSession %s -> %s", k, v));
				}
				
				// SessionSet
		        ); 
	}
}
