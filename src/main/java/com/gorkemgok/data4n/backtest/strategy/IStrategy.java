package com.gorkemgok.data4n.backtest.strategy;

import com.gorkemgok.data4n.backtest.Positions;
import com.gorkemgok.data4n.core.set.TickDataSet;

/**
 * Created by gorkemgok on 19/01/15.
 */
public interface IStrategy {
    public void apply(TickDataSet set,Positions positions);
}
