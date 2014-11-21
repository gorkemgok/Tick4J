package com.gorkemgok.data4n.model.function;

public class Sigmoid implements IFunction {

	public String getSymbol() {
		return "sig";
	}

	public double calculate(double value, String param, double... dparams) {
		if (value<0) return -1; 
		else if (value>0) return 1;
		return 0;
	}

	public IFunction reverseFunction() {
		return new Unit();
	}

}
