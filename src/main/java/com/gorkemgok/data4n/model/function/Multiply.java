package com.gorkemgok.data4n.model.function;

public class Multiply implements IFunction {

	@Override
	public String getSymbol() {
		return "*";
	}

	@Override
	public double calculate(double value,String param, double... dparams) {
		return value * Double.parseDouble(param);
	}

	@Override
	public IFunction reverseFunction() {
		return new Divide();
	}

}
