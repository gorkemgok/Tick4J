package com.gorkemgok.tick4j.model.function;

public class Square implements IFunction {

	public String getSymbol() {
		return "sqr";
	}

	public double calculate(double value, String param, double... dparams) {
		return value * value;
	}

	public IFunction reverseFunction() {
		return new SquareRoot();
	}

}
