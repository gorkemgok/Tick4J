package com.gorkemgok.data4n.talib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.tictactec.ta.lib.meta.annotation.FuncInfo;
import com.tictactec.ta.lib.meta.annotation.InputParameterInfo;
import com.tictactec.ta.lib.meta.annotation.OptInputParameterInfo;
import com.tictactec.ta.lib.meta.annotation.OutputParameterInfo;

public class TALibFunctions {
	private static Function[] functions;
	{
		functions = TALibFunctions.loadFunctions();
	}
	public static Function getFunction(String name){
		for (Function f : functions){
			if (f.getName().equals(name)) return f;
		}
		return null;
	}
	public static Function[] getFunctions(){
		return functions;
	}
	private static Function[] loadFunctions(){
		Method[] methods = null;
		try {
			methods = TALibFunctions.class
			        .getClassLoader()
			        .loadClass(("com.tictactec.ta.lib.CoreAnnotated"))
			        .getMethods();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Function> functions = new ArrayList<Function>();
		for (Method m : methods) {
			FuncInfo funcInfo = m.getAnnotation(FuncInfo.class);
			if (funcInfo!=null){
				Function function = new Function(m,funcInfo.name(),funcInfo.nbInput(),funcInfo.nbOptInput(),funcInfo.nbOutput());
				functions.add(function);
				Annotation[][] prameterAnnotations = m.getParameterAnnotations();
	            for (Annotation[] p : prameterAnnotations){
	            	for (Annotation annotation : p){
	            		if (annotation instanceof OutputParameterInfo){
	            			OutputParameterInfo outputParameterInfo = (OutputParameterInfo)annotation;
	            			function.addOutput(new Function.Param(outputParameterInfo.paramName(),outputParameterInfo.type().name()));
	            		}else if (annotation instanceof InputParameterInfo){
	            			InputParameterInfo inputParameterInfo = (InputParameterInfo)annotation;
	            			function.addInput(new Function.Param(inputParameterInfo.paramName(),inputParameterInfo.type().name()));
	            		}else if (annotation instanceof OptInputParameterInfo){
	            			OptInputParameterInfo optInputParameterInfo = (OptInputParameterInfo)annotation;
	            			function.addOptInput(new Function.Param(optInputParameterInfo.paramName(),optInputParameterInfo.type().name()));
	            		}
	            	}
	            }
			}
		}
		return functions.toArray(new Function[functions.size()]);
	}
}
