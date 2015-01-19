package com.gorkemgok.data4n.backtest;

import com.gorkemgok.data4n.backtest.action.BuyExpAction;
import com.gorkemgok.data4n.backtest.action.ClosePositionExpAction;
import com.gorkemgok.data4n.backtest.action.SellExpAction;
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
        CSVLoader loader = new CSVLoader("resources/vob30_5dk_100.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();
        set.reset();

        Positions positions = new Positions();

        BasicStrategy strategy = new BasicStrategyBuilder()
                .addAction(new BuyExpAction(new TALibExpressionBuilder(set,"C>SMA(c,20)").build()))
                .addAction(new SellExpAction(new TALibExpressionBuilder(set,"C<SMA(c,20)").build()))
                .addAction(new ClosePositionExpAction(new TALibExpressionBuilder(set,"C<P").build(),0,positions))
                .build();
        set.begin();
        while (set.next()){
            strategy.apply(set,positions);
        }
        set.reset();
        double profit = 0;
        for (Position position : positions.getPositions()){
            System.out.println(position);
            profit += position.calculateProfit();
        }

        System.out.println("Total:"+profit);
    }
}
