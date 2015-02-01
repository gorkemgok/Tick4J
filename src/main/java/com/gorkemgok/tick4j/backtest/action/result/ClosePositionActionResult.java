package com.gorkemgok.tick4j.backtest.action.result;

import com.gorkemgok.tick4j.backtest.Positions;

public class ClosePositionActionResult extends ActionResult{
	private Positions positions = new Positions(); 
	public ClosePositionActionResult() {
		super(null);
	}
	
	public Positions getPositions(){
		return positions;
	}
    public boolean hasNewPosition(){
        return false;
    }

}
