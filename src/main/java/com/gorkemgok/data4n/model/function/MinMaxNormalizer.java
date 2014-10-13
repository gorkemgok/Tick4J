package com.gorkemgok.data4n.model.function;

public class MinMaxNormalizer implements IFunction {

	@Override
	public String getSymbol() {
		return "mmnorm";
	}

	@Override
	public double calculate(double value, String param, double... dparams) {
		return (value-dparams[1])/(dparams[0]-dparams[1]);
	}

	@Override
	public IFunction reverseFunction() {
		return new ReverseMinMaxNormalizer();
	}

}
