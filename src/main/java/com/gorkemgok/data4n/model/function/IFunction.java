package com.gorkemgok.data4n.model.function;

public interface IFunction {
	public String getSymbol();
	public double calculate(double value, String param, double... dparams);
	public IFunction reverseFunction();
}
