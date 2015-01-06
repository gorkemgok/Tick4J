package com.gorkemgok.data4n.talib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.tictactec.ta.lib.meta.annotation.FuncInfo;
import com.tictactec.ta.lib.meta.annotation.InputParameterInfo;
import com.tictactec.ta.lib.meta.annotation.OptInputParameterInfo;
import com.tictactec.ta.lib.meta.annotation.OutputParameterInfo;

public class TALibFunctions {
	public static void main(String[] args) throws SecurityException, ClassNotFoundException {
		Function[] functions = TALibFunctions.getFunctions();
		for (Function f : functions){
			System.out.println(f);
		}
	}
	public static Function[] getFunctions() throws SecurityException, ClassNotFoundException{
		Method[] methods = TALibFunctions.class
                .getClassLoader()
                .loadClass(("com.tictactec.ta.lib.CoreAnnotated"))
                .getMethods();
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
	            			function.addInput(new Function.Param(optInputParameterInfo.paramName(),optInputParameterInfo.type().name()));
	            		}
	            	}
	            }
			}
		}
		return functions.toArray(new Function[functions.size()]);
	}
}
