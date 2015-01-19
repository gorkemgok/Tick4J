package com.gorkemgok.data4n.backtest.action;

import com.gorkemgok.data4n.core.set.TickDataSet;
import net.objecthunter.exp4j.Expression;

/**
 * Created by gorkemgok on 19/01/15.
 */
public abstract class ExpressionAction implements IAction {
    protected Expression expression;

    public ExpressionAction(Expression expression) {
        this.expression = expression;
    }

    protected void setVariables(TickDataSet set){
        expression.setVariable("O",set.getRow().getOpen());
        expression.setVariable("H",set.getRow().getHigh());
        expression.setVariable("L",set.getRow().getLow());
        expression.setVariable("C",set.getRow().getClose());
        expression.setVariable("V",set.getRow().getVolume());
    }

}
