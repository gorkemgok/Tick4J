package com.gorkemgok.tick4j.backtest.commission;

import com.gorkemgok.tick4j.backtest.Position;

public interface ICommission {
	double calculate(Position position);
}
