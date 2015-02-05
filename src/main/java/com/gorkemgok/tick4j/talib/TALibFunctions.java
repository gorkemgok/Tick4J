package com.gorkemgok.tick4j.talib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.tictactec.ta.lib.meta.annotation.FuncInfo;
import com.tictactec.ta.lib.meta.annotation.InputParameterInfo;
import com.tictactec.ta.lib.meta.annotation.OptInputParameterInfo;
import com.tictactec.ta.lib.meta.annotation.OutputParameterInfo;

public class TALibFunctions {
	private static TALibFunction[] talibFunctions;
	static{
		talibFunctions = TALibFunctions.loadFunctions();
	}
	public static TALibFunction getFunction(String name){
		for (TALibFunction f : talibFunctions){
			if (f.getName().equals(name)) return f;
		}
		throw new RuntimeException("No such function "+name);
	}
	public static TALibFunction[] getTALibFunctions(){
		return talibFunctions;
	}
	private static TALibFunction[] loadFunctions(){
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
		ArrayList<TALibFunction> TALibFunctions = new ArrayList<TALibFunction>();
		for (Method m : methods) {
			FuncInfo funcInfo = m.getAnnotation(FuncInfo.class);
			if (funcInfo!=null){
				TALibFunction TALibFunction = new TALibFunction(m,funcInfo.name(),funcInfo.nbInput(),funcInfo.nbOptInput(),funcInfo.nbOutput());
				TALibFunctions.add(TALibFunction);
				Annotation[][] prameterAnnotations = m.getParameterAnnotations();
	            for (Annotation[] p : prameterAnnotations){
	            	for (Annotation annotation : p){
	            		if (annotation instanceof OutputParameterInfo){
	            			OutputParameterInfo outputParameterInfo = (OutputParameterInfo)annotation;
	            			TALibFunction.addOutput(new TALibFunction.Param(outputParameterInfo.paramName(),outputParameterInfo.type().name()));
	            		}else if (annotation instanceof InputParameterInfo){
	            			InputParameterInfo inputParameterInfo = (InputParameterInfo)annotation;
	            			TALibFunction.addInput(new TALibFunction.Param(inputParameterInfo.paramName(),inputParameterInfo.type().name()));
	            		}else if (annotation instanceof OptInputParameterInfo){
	            			OptInputParameterInfo optInputParameterInfo = (OptInputParameterInfo)annotation;
	            			TALibFunction.addOptInput(new TALibFunction.Param(optInputParameterInfo.paramName(),optInputParameterInfo.type().name()));
	            		}
	            	}
	            }
			}
		}
		return TALibFunctions.toArray(new TALibFunction[TALibFunctions.size()]);
	}
}
