package com.gorkemgok.data4n.backtest;

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

    public Position(int type, double openPrice) {
        this.type = type;
        this.openPrice = openPrice;
        this.isClosed = false;
    }
    public void close(double price){
        if (!isClosed){
            closePrice = price;
            isClosed = true;
        }
    }

    public int getType() {
        return type;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double calculateProfit(){
        double profit = openPrice - closePrice;
        if (type==Position.SELL) profit *= -1;
        return isClosed?profit:0;
    }

    @Override
    public String toString() {
        return (isClosed?"-":"")+(type==Position.BUY?"Buy":"Sell")+"Position{" +
                "openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                '}';
    }
}
