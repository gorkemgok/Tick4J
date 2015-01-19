package com.gorkemgok.data4n.backtest.action;

import com.gorkemgok.data4n.backtest.Position;
import net.objecthunter.exp4j.Expression;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class SellExpAction extends OpenPositionExpAction implements IAction {

    public SellExpAction(Expression expression) {
        super(expression, Position.SELL);
    }

}
