package com.gorkemgok.tick4j.backtest.commission;

import com.gorkemgok.tick4j.backtest.Position;

public class ConstantSpreadCommission implements ICommission{
	private double spread;

	public ConstantSpreadCommission(double spread) {
		super();
		this.spread = spread;
	}

	public double calculate(Position position) {
		return spread;
	}
	
}
