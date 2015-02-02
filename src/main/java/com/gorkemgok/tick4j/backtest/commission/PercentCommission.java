package com.gorkemgok.tick4j.backtest.commission;

import com.gorkemgok.tick4j.backtest.Position;

public class PercentCommission implements ICommission{
	private double percent;

	public PercentCommission(double percent) {
		super();
		this.percent = percent;
	}

	public double calculate(Position position) {
		return position.getClosePrice() * percent / 100;
	}

}
