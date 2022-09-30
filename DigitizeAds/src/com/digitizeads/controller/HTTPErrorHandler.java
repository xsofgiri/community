package com.digitizeads.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorHandler{
	
	@RequestMapping(value="/400")
	 public String error400(){
	  System.out.println("custom error handler : 400");
	  return "/error/400";
	 }
	
	@RequestMapping(value="/404")
	 public String error404(){
	  //System.out.println("custom error handler : 404");
	  return "/error/404";
	 }
	
	@RequestMapping(value="/500")
	 public String error500(){
	  System.out.println("custom error handler : 500");
	  return "/error/500";
	 }
	
	@RequestMapping(value="/503")
	 public String error503(){
	  System.out.println("custom error handler : 503");
	  return "/error/503";
	 }
	
}
