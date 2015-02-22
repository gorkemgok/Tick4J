package com.gorkemgok.tick4j.backtest.strategy;

import com.gorkemgok.tick4j.backtest.Positions;
import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 19/01/15.
 */
public interface IStrategy {
    public void apply(TickDataSet set,Positions positions);
    public void setMaxOpenPositionCount(int maxOpenPositionCount);
}
