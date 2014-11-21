package com.gorkemgok.data4n.model.function;

public class ReverseMinMaxNormalizer implements IFunction {

	public String getSymbol() {
		return "rmmnorm";
	}

	public double calculate(double value, String param, double... dparams) {
		return (value*(dparams[0]-dparams[1]))+dparams[1];
	}

	public IFunction reverseFunction() {
		return new MinMaxNormalizer();
	}

}
