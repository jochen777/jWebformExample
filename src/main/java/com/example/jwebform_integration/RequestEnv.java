package com.example.jwebform_integration;

import javax.servlet.http.HttpServletRequest;

import jwebform.env.Env;

public class RequestEnv extends Env{
	public RequestEnv(HttpServletRequest request) {
		super(request::getParameter, // Request
		        request.getSession()::getAttribute,  // SessionGet
		        request.getSession()::setAttribute // SessionSet
		        ); 
	}
}
