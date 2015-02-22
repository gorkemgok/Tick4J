package com.gorkemgok.tick4j.backtest.strategy;

import com.gorkemgok.tick4j.backtest.Position;
import com.gorkemgok.tick4j.backtest.Positions;
import com.gorkemgok.tick4j.backtest.action.OpenClosePositionExpAction;
import com.gorkemgok.tick4j.backtest.action.result.ActionResult;
import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 18/02/15.
 */
public class UpDownStrategy extends AbstractStrategy implements IStrategy{
    private OpenClosePositionExpAction action;
    private TickDataSet set;

    public UpDownStrategy(OpenClosePositionExpAction action,TickDataSet set) {
        this.action = action;
        this.set = set;
    }

    @Override
    public void apply(TickDataSet set, Positions positions) {
        TickDataRow row = set.getRow();
        ActionResult actionResult = action.run(set);
        if (actionResult.hasNewPosition()){
            if (maxOpenPositionCount == 0 || maxOpenPositionCount > positions.getOpenPositionCount()){
                positions.addPosition(actionResult.getPosition());
            }
            for (Position position : positions.getPositions()){
                if ((actionResult.getPosition().getType()==Position.BUY && position.getType()==Position.SELL) ||
                        (actionResult.getPosition().getType()==Position.SELL && position.getType()==Position.BUY)){
                    position.close(row.getClose(),row.getDate());
                }
            }
        }
    }
}
