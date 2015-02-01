package com.gorkemgok.tick4j.backtest.action.result;

import com.gorkemgok.tick4j.backtest.Position;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class ActionResult {
    private Position position;

    public ActionResult(Position position) {
        this.position = position;
    }

    public boolean hasNewPosition(){
        if (position!=null) return true;
        return false;
    }

    public Position getPosition() {
        return position;
    }
}
