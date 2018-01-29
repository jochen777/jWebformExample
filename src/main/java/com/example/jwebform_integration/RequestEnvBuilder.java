package com.example.jwebform_integration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jwebform.env.Env;
import jwebform.env.EnvBuilder;

public class RequestEnvBuilder {

  private static final Logger log = LoggerFactory.getLogger(RequestEnvBuilder.class);


  public Env of(HttpServletRequest request) {
    return new EnvBuilder().of(request::getParameter, // Request
        request.getSession()::getAttribute, // SessionGet
        request.getSession()::setAttribute);
  }

  public Env ofWithDebugging(HttpServletRequest request) {
    return new EnvBuilder().of(paramname -> {
      String val = request.getParameter(paramname);
      log.debug(String.format("getParameter %s -> %s", paramname, val));
      return val;
    }, // Request
        paramname -> {
          String val = (String) request.getSession().getAttribute(paramname);
          log.debug(String.format("getSession %s -> %s", paramname, val));
          return val;
        }, // SessionGet
        (k, v) -> {
          request.getSession().setAttribute(k, v);
          log.debug(String.format("setSession %s -> %s", k, v));
        });
  }


}
