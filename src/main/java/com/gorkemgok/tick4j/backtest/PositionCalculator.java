package com.gorkemgok.tick4j.backtest;

public class PositionCalculator {
	private Positions positions;
	private double lastClose;
	private double profit = 0;

	public PositionCalculator(Positions positions, double lastClose) {
		super();
		this.positions = positions;
		this.lastClose = lastClose;
	}
	
	public void calculate(){    
        for (Position position : positions.getPositions()){
        	if (!position.isClosed()) position.close(lastClose);
            profit += position.calculateProfit();
            System.out.println(position);
        }
	}

	public double getProfit() {
		return profit;
	}
	
	
}
