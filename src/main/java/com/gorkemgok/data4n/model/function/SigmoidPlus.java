package com.gorkemgok.data4n.model.function;

public class SigmoidPlus implements IFunction {

	@Override
	public String getSymbol() {
		return "sigp";
	}

	@Override
	public double calculate(double value, String param, double... dparams) {
		if (value<0) return 0; 
		else if (value>0) return 1;
		return 0.5;
	}

	@Override
	public IFunction reverseFunction() {
		return new Unit();
	}

}
