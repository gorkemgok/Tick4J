package com.gorkemgok.tick4j.backtest;

import java.util.Date;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class Position {
    public final static int BUY = 0;
    public final static int SELL = 1;

    private int type;
    private double openPrice;
    private boolean isClosed;
    private double closePrice;
    private Date openDate;
    private Date closeDate;
    private Positions parent = null;

    public Position(int type,Date openDate, double openPrice) {
        this.type = type;
        this.openPrice = openPrice;
        this.isClosed = false;
        this.openDate = openDate;
    }
    public void close(double price, Date date){
        if (!isClosed){
            closePrice = price;
            closeDate = date;
            isClosed = true;
            if (parent!=null){
            	parent.closeOnePosition();
            }
        }
    }
    public boolean isClosed(){
    	return isClosed;
    }
    public int getType() {
        return type;
    }
    
    public Date getOpenDate(){
    	return openDate;
    }
    public double getOpenPrice() {
        return openPrice;
    }

    public double getClosePrice() {
		return closePrice;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public double calculateProfit(){
        double profit = closePrice - openPrice;
        if (type==Position.SELL) profit *= -1;
        return isClosed?profit:0;
    }

    @Override
    public String toString() {
    	double profit = type==Position.BUY?closePrice-openPrice:openPrice-closePrice;
        return (isClosed?(profit<0?"-":"+"):"")+(type==Position.BUY?"Buy":"Sell")+"Position{" +
        		"openDate="+openDate+
                ", openPrice=" + openPrice +
                ", closeDate="+closeDate+
                ", closePrice=" + closePrice +
                ", profit=" + profit +
                '}';
    }
    
    protected void setParent(Positions parent){
    	this.parent = parent;
    }
}
