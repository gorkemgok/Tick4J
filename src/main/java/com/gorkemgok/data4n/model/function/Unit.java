package com.gorkemgok.data4n.model.function;

public class Unit implements IFunction {

	@Override
	public String getSymbol() {
		return "I";
	}

	@Override
	public double calculate(double value, String param, double... dparams) {
		return value;
	}

	@Override
	public IFunction reverseFunction() {
		return new Unit();
	}

}
