package com.gorkemgok.data4n.backtest;

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
    private Date date;

    public Position(int type,Date date, double openPrice) {
        this.type = type;
        this.openPrice = openPrice;
        this.isClosed = false;
        this.date = date;
    }
    public void close(double price){
        if (!isClosed){
            closePrice = price;
            isClosed = true;
        }
    }
    public boolean isClosed(){
    	return isClosed;
    }
    public int getType() {
        return type;
    }
    
    public Date getOpenDate(){
    	return date;
    }
    public double getOpenPrice() {
        return openPrice;
    }

    public double calculateProfit(){
        double profit = closePrice - openPrice;
        if (type==Position.SELL) profit *= -1;
        return isClosed?profit:0;
    }

    @Override
    public String toString() {
        return (isClosed?"-":"")+(type==Position.BUY?"Buy":"Sell")+"Position{" +
        		"date="+date+
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                '}';
    }
}
