package com.gorkemgok.data4n.model.function;

public class SquareRoot implements IFunction{

	@Override
	public String getSymbol() {
		return "sqrt";
	}

	@Override
	public double calculate(double value, String param, double... dparams) {
		return Math.sqrt(value);
	}

	@Override
	public IFunction reverseFunction() {
		return new Square();
	}
	
	

}
