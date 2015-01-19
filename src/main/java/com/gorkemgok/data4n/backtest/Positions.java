package com.gorkemgok.data4n.backtest;

import java.util.ArrayList;

/**
 * Created by gorkemgok on 20/01/15.
 */
public class Positions {
    private ArrayList<Position> positions = new ArrayList<Position>();

    public void addPosition(Position position){
        positions.add(position);
    }

    public ArrayList<Position> getPositions(){
        return positions;
    }
}
