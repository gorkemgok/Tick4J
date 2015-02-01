package com.gorkemgok.tick4j.backtest.strategy;

/**
 * Created by gorkemgok on 19/01/15.
 */
public interface IStrategy {
    public void apply(TickDataSet set,Positions positions);
}
