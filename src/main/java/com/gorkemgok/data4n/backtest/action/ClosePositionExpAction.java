package com.gorkemgok.data4n.backtest.action;

import com.gorkemgok.data4n.backtest.Position;
import com.gorkemgok.data4n.backtest.Positions;
import com.gorkemgok.data4n.backtest.action.result.ActionResult;
import com.gorkemgok.data4n.backtest.action.result.NoActionResult;
import com.gorkemgok.data4n.core.set.TickDataSet;
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

    @Override
    public ActionResult run(TickDataSet set) {
        super.setVariables(set);
        for (Position position : positions.getPositions()){
            expression.setVariable("P",position.getOpenPrice());
            if (expression.evaluate()==1){
                position.close(set.getRow().getClose());//TODO: hangi fiyattan alım satım yapılacagi secilebilsin
                return new ActionResult(position);
            }
        }
        return new NoActionResult();
    }
}
