package com.gorkemgok.tick4j.backtest;

import java.util.Date;

import com.gorkemgok.tick4j.backtest.commission.ICommission;

public class ProfitCalculator {
	private Positions positions;
	private double lastClose;
	private Date lastDate;
	private ICommission commission;
	private double profit = 0;
	private boolean verbose = false;

	public ProfitCalculator(Positions positions,ICommission commission, double lastClose, Date lastDate) {
		super();
		this.positions = positions;
		this.lastClose = lastClose;
		this.lastDate = lastDate;
		this.commission = commission;
	}
	
	public void calculate(){    
        for (Position position : positions.getPositions()){
        	if (!position.isClosed()) position.close(lastClose,lastDate);
            profit += position.calculateProfit() - commission.calculate(position);
            if (verbose){
            	System.out.println(position);
            }
        }
	}

	public double getProfit() {
		return profit;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
}
