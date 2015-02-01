package com.gorkemgok.tick4j.model.function;

public class SigmoidPlus implements IFunction {

	public String getSymbol() {
		return "sigp";
	}

	public double calculate(double value, String param, double... dparams) {
		if (value<0) return 0; 
		else if (value>0) return 1;
		return 0.5;
	}

	public IFunction reverseFunction() {
		return new Unit();
	}

}
