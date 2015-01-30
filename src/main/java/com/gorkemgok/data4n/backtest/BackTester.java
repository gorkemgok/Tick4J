package com.gorkemgok.data4n.backtest;

import com.gorkemgok.data4n.backtest.action.BuyExpAction;
import com.gorkemgok.data4n.backtest.action.ClosePositionExpAction;

import com.gorkemgok.data4n.backtest.strategy.BasicStrategy;
import com.gorkemgok.data4n.backtest.strategy.BasicStrategyBuilder;
import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.listener.CSVTickListener;
import com.gorkemgok.data4n.util.TALibExpressionBuilder;
import com.gorkemgok.data4n.util.csv.CSVLoader;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class BackTester {
    public static void main(String[] args) throws IOException, ParseException {
        TickDataSet set = new TickDataSet("VOBO30","5DK");
        CSVLoader loader = new CSVLoader("resources/vob30_5dk_15-19Agust.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();

        Positions positions = new Positions();

        BasicStrategy strategy = new BasicStrategyBuilder()
                .addAction(new BuyExpAction(new TALibExpressionBuilder(set,"((TRIX(h,29))<(TRIX(o,29)))&(((WMA(l,12))>H)&(33<(RSI(c,25))))").build()))
                //.addAction(new SellExpAction(new TALibExpressionBuilder(set,"C>SMA(c,20)").build()))
                .addAction(new ClosePositionExpAction(new TALibExpressionBuilder(set,"C<(P-5)").build(),0,positions))
                .addAction(new ClosePositionExpAction(new TALibExpressionBuilder(set,"C>(P+5)").build(),0,positions))
                .build();
        
        strategy.setMaxOpenPositionCount(4);
        double lastClose = 0;
        set.begin();
        while (set.next()){
            strategy.apply(set,positions);
            lastClose = set.getRow().getClose();
        }
        set.reset();
        
        PositionCalculator positionCalculator = new PositionCalculator(positions, lastClose);
        positionCalculator.calculate();
        double profit = positionCalculator.getProfit();

        System.out.println("Total:"+profit);
    }
}
