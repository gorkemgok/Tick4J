package com.gorkemgok.tick4j.model.function;

public class Calculator {
	private static IFunction[] functions = 
		{new Divide(),new Multiply(),new Square(),new SquareRoot(),new Sigmoid(),new SigmoidPlus(),new Unit(),new MinMaxNormalizer(),new ReverseMinMaxNormalizer()};
	private static IFunction getFunction(String symbol){
		for (IFunction fn : functions){
			if (fn.getSymbol().equals(symbol)){
				return fn;
			}
		}
		return null;
	}
	public static double calculate(double value,String functionStr,double... dparams){
		double result = value;
		String[] fns = functionStr.split(">");
		for (String fn : fns){
			String[] f = fn.split("_");
			IFunction function = Calculator.getFunction(f[0]);
			if (function!=null){
				String param = f.length>1?f[1]:"";
				result = function.calculate(result,param,dparams);
			}
		}
		return result;
	}
	
	public static double calculateReverse(double value,String functionStr,double... dparams){
		double result = value;
		String[] fns = functionStr.split(">");
		for (int i=fns.length-1;i>-1 && !fns[i].isEmpty();i--){
			String[] f = fns[i].split("_");
			IFunction function = Calculator.getFunction(f[0]).reverseFunction();
			if (function!=null){
				String param = f.length>1?f[1]:"";
				result = function.calculate(result,param,dparams);
			}
		}
		return result;
	}
}
