package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mitchellbosecke.pebble.spring4.PebbleViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

  @Autowired
  PebbleViewResolver resolver;


  @Bean(name = "viewResolver")
  public ViewResolver getViewResolver() {
    return resolver;
  }
}
