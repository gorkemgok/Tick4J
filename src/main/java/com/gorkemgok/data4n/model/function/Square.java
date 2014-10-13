package com.gorkemgok.data4n.model.function;

public class Square implements IFunction {

	@Override
	public String getSymbol() {
		return "sqr";
	}

	@Override
	public double calculate(double value, String param, double... dparams) {
		return value * value;
	}

	@Override
	public IFunction reverseFunction() {
		return new SquareRoot();
	}

}
