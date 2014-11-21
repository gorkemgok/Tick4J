package com.gorkemgok.data4n.model.function;

public class SquareRoot implements IFunction{

	public String getSymbol() {
		return "sqrt";
	}

	public double calculate(double value, String param, double... dparams) {
		return Math.sqrt(value);
	}

	public IFunction reverseFunction() {
		return new Square();
	}
	
	

}
