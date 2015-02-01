package com.gorkemgok.tick4j.backtest.action;

import com.gorkemgok.tick4j.backtest.Position;
import com.gorkemgok.tick4j.backtest.action.result.ActionResult;
import com.gorkemgok.tick4j.backtest.action.result.NoActionResult;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import net.objecthunter.exp4j.Expression;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class OpenPositionExpAction extends ExpressionAction implements IAction {
    private int positionType;
    public OpenPositionExpAction(Expression expression, int positionType) {
        super(expression);
        this.positionType = positionType;
    }

    public ActionResult run(TickDataSet set) {
        super.setVariables(set);
        if (expression.evaluate()==1){
            Position position = new Position(positionType,set.getRow().getDate(),set.getRow().getClose());
            return new ActionResult(position);
        }
        return new NoActionResult();
    }
}
