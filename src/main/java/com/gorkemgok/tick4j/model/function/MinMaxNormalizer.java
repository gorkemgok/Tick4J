package com.gorkemgok.tick4j.model.function;

public class MinMaxNormalizer implements IFunction {

	public String getSymbol() {
		return "mmnorm";
	}

	public double calculate(double value, String param, double... dparams) {
		return (value-dparams[1])/(dparams[0]-dparams[1]);
	}

	public IFunction reverseFunction() {
		return new ReverseMinMaxNormalizer();
	}

}
