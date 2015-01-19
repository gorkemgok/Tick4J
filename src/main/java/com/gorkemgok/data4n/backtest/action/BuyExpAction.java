package com.gorkemgok.data4n.backtest.action;

import com.gorkemgok.data4n.backtest.Position;
import net.objecthunter.exp4j.Expression;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class BuyExpAction extends OpenPositionExpAction implements IAction {

    public BuyExpAction(Expression expression) {
        super(expression,Position.BUY);
    }

}
