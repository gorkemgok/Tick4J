package com.gorkemgok.tick4j.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	@SuppressWarnings("rawtypes")
	public static Logger get(Class clas){
		return LogManager.getLogger(clas);
	}
	
	public static Logger get(Object object){
		return LogManager.getLogger(object.getClass());
	}
	
	public static Logger get(){
		return LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	}
}
