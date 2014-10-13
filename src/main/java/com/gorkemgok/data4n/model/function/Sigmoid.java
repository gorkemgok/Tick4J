package com.gorkemgok.data4n.model.function;

public class Sigmoid implements IFunction {

	@Override
	public String getSymbol() {
		return "sig";
	}

	@Override
	public double calculate(double value, String param, double... dparams) {
		if (value<0) return -1; 
		else if (value>0) return 1;
		return 0;
	}

	@Override
	public IFunction reverseFunction() {
		return new Unit();
	}

}
