package com.gorkemgok.tick4j.backtest.action;

import com.gorkemgok.tick4j.backtest.Position;
import com.gorkemgok.tick4j.backtest.Positions;
import com.gorkemgok.tick4j.backtest.action.result.ActionResult;
import com.gorkemgok.tick4j.backtest.action.result.ClosePositionActionResult;
import com.gorkemgok.tick4j.backtest.action.result.NoActionResult;
import com.gorkemgok.tick4j.core.set.TickDataSet;

import net.objecthunter.exp4j.Expression;

/**
 * Created by gorkemgok on 20/01/15.
 */
public class ClosePositionExpAction extends ExpressionAction implements IAction{
    private int positionType;
    private Positions positions;
    public ClosePositionExpAction(Expression expression, int positionType, Positions positions) {
        super(expression);
        this.positionType = positionType;
        this.positions = positions;
    }

    public ActionResult run(TickDataSet set) {
        super.setVariables(set);
        ClosePositionActionResult ar = new ClosePositionActionResult();
        boolean hasClosedPosition = false;
        for (Position position : positions.getPositions()){
        	if (!position.isClosed()){
	            expression.setVariable("P",position.getOpenPrice());
	            double expressionResult = expression.evaluate();
	            if (expressionResult==1d){
	                position.close(set.getRow().getClose());//TODO: hangi fiyattan alim satim yapilacagi secilebilsin
	                ar.getPositions().addPosition(position);
	                hasClosedPosition = true;
	            }
            }
        }
        if (hasClosedPosition) return ar;
        return new NoActionResult();
    }
}
