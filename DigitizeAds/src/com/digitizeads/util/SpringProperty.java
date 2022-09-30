package com.digitizeads.util;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(name="ApplicationResources", 
value="classpath:ApplicationResources.properties")
public class SpringProperty {

	public String getPropertyValue(String propertyName){
		String propertyValue = null;
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		if(propertyName!=null && !"".equals(propertyName) && !"null".equals(propertyName)){
			try {
				ctx.register(SpringProperty.class);
				ctx.refresh();
				Environment env = ctx.getEnvironment();
				propertyValue = env.getProperty(propertyName);
			} finally {
				ctx.close();
			}
		}
		
		return propertyValue;
		
	}
	
	
	public static String getPropValue(String propertyName){
		String propertyValue = null;
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		if(propertyName!=null && !"".equals(propertyName) && !"null".equals(propertyName)){
			try {
				ctx.register(SpringProperty.class);
				ctx.refresh();
				Environment env = ctx.getEnvironment();
				propertyValue = env.getProperty(propertyName);
			} finally {
				ctx.close();
			}
		}
		
		return propertyValue;
		
	}
	public static void main(String[] args) {/*
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(SpringProperty.class);
			ctx.refresh();
			Environment env = ctx.getEnvironment();
			System.out.println("homeLink: " + env.getProperty("homeLink"));
		} finally {
			ctx.close();
		}
	*/}
}
