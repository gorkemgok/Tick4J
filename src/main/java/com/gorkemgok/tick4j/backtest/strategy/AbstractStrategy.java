package com.gorkemgok.tick4j.backtest.strategy;

import com.gorkemgok.tick4j.backtest.Positions;
import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 19/02/15.
 */
public abstract class AbstractStrategy implements IStrategy{
    protected int maxOpenPositionCount = 0;

    @Override
    public void setMaxOpenPositionCount(int maxOpenPositionCount) {
        this.maxOpenPositionCount = maxOpenPositionCount;
    }
}
