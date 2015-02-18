package com.gorkemgok.tick4j.backtest.action;

import com.gorkemgok.tick4j.backtest.Position;
import com.gorkemgok.tick4j.backtest.action.result.ActionResult;
import com.gorkemgok.tick4j.backtest.action.result.NoActionResult;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import net.objecthunter.exp4j.Expression;

/**
 * Created by gorkemgok on 18/02/15.
 */
public class OpenClosePositionExpAction extends ExpressionAction implements IAction {
    public OpenClosePositionExpAction(Expression expression) {
        super(expression);
    }

    @Override
    public ActionResult run(TickDataSet set) {
        super.setVariables(set);
        if (expression.evaluate()==1){
            Position position = new Position(Position.BUY,set.getRow().getDate(),set.getRow().getClose());
            return new ActionResult(position);
        }else if (expression.evaluate()==2){
            Position position = new Position(Position.SELL,set.getRow().getDate(),set.getRow().getClose());
            return new ActionResult(position);
        }
        return new NoActionResult();
    }
}
