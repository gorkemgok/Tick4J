package com.gorkemgok.tick4j.backtest;

import com.gorkemgok.tick4j.backtest.action.BuyExpAction;
import com.gorkemgok.tick4j.backtest.action.ClosePositionExpAction;

import com.gorkemgok.tick4j.backtest.strategy.BasicStrategy;
import com.gorkemgok.tick4j.backtest.strategy.BasicStrategyBuilder;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import com.gorkemgok.tick4j.listener.CSVTickListener;
import com.gorkemgok.tick4j.util.TALibExpressionBuilder;
import com.gorkemgok.tick4j.util.csv.CSVLoader;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by gorkemgok on 19/01/15.
 */
public class BackTester {
    public static void main(String[] args) throws IOException, ParseException {
        TickDataSet set = new TickDataSet("VOBO30","5DK");
        CSVLoader loader = new CSVLoader("resources/vob30_5dk.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();

        Positions positions = new Positions();

        BasicStrategy strategy = new BasicStrategyBuilder()
                .addAction(new BuyExpAction(new TALibExpressionBuilder(set,"(TRIX((max((min((max((tema(l,29)),29)),29)),29)),24))<0").build()))
                //.addAction(new SellExpAction(new TALibExpressionBuilder(set, "((WMA((max((min((max(c,18)),16)),18)),5))<L)&(AVGPRICE()>(MAX(o,16)))").build()))
                .addAction(new ClosePositionExpAction(new TALibExpressionBuilder(set, "(RSI((dema(o,27)),28))<(MFI(27))").build(), 0, positions))
                .addAction(new ClosePositionExpAction(new TALibExpressionBuilder(set, "(RSI(h,8))>89").build(), 0, positions))
                .build();
        
        strategy.setMaxOpenPositionCount(3);
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
