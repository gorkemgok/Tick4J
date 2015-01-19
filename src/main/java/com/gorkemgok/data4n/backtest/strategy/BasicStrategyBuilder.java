package com.gorkemgok.data4n.backtest.strategy;

import com.gorkemgok.data4n.backtest.action.IAction;

import java.util.ArrayList;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class BasicStrategyBuilder {
    private ArrayList<BasicStrategy> strategies = new ArrayList<BasicStrategy>();
    private ArrayList<IAction> actions = new ArrayList<IAction>();
    private BasicStrategy basicStrategy;

    public BasicStrategyBuilder() {
        basicStrategy = new BasicStrategy(strategies,actions);
    }

    public BasicStrategyBuilder addStrategy(BasicStrategy basicStrategy){
        strategies.add(basicStrategy);
        return this;
    }

    public BasicStrategyBuilder addAction(IAction action){
        actions.add(action);
        return this;
    }

    public BasicStrategy build(){
        return basicStrategy;
    }
}
