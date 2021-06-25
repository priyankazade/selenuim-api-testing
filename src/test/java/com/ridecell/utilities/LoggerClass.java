package com.ridecell.utilities;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.testng.log4testng.Logger;

public class LoggerClass {

	static Logger log = Logger.getLogger(LoggerClass.class);

	public static void main(String[] args) 
    {
		BasicConfigurator.configure();
        //PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("log4j.properties");
 
		//Log in console in and log file
        log.debug("Log4j appender configuration is successful !!");
    }
}
