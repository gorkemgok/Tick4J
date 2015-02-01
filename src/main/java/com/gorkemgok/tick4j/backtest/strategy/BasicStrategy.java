package com.gorkemgok.tick4j.backtest.strategy;

import com.gorkemgok.tick4j.backtest.Positions;
import com.gorkemgok.tick4j.backtest.action.IAction;
import com.gorkemgok.tick4j.backtest.action.result.ActionResult;
import com.gorkemgok.tick4j.core.set.TickDataSet;

import java.util.ArrayList;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class BasicStrategy implements IStrategy{
    private ArrayList<BasicStrategy> strategies = new ArrayList<BasicStrategy>();
    private ArrayList<IAction> actions = new ArrayList<IAction>();
    private int maxOpenPositionCount = 0;

    protected BasicStrategy(ArrayList<BasicStrategy> strategies, ArrayList<IAction> actions) {
        this.strategies = strategies;
        this.actions = actions;
    }

    public void apply(TickDataSet set,Positions positions) {
        for (IAction action : actions){
            ActionResult actionResult = action.run(set);
            if (actionResult.hasNewPosition() && maxOpenPositionCount > 0 && maxOpenPositionCount > positions.getOpenPositionCount()) positions.addPosition(actionResult.getPosition());
        }
        for (IStrategy strategy : strategies){
            strategy.apply(set,positions);
        }
    }

	public void setMaxOpenPositionCount(int maxOpenPositionCount) {
		this.maxOpenPositionCount = maxOpenPositionCount;
	}
    
    

}
