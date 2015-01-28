package com.gorkemgok.data4n.backtest;

import java.util.ArrayList;

/**
 * Created by gorkemgok on 20/01/15.
 */
public class Positions {
    private ArrayList<Position> positions = new ArrayList<Position>();
    private int openPositionCount = 0;

    public void addPosition(Position position){
        positions.add(position);
        position.setParent(this);
        openPositionCount++;
    }

    public ArrayList<Position> getPositions(){
        return positions;
    }
    
    public void closeOnePosition(){
    	openPositionCount--;
    }

	public int getOpenPositionCount() {
		return openPositionCount;
	}
   
}
