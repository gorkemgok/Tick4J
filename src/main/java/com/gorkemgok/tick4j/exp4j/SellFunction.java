package com.gorkemgok.tick4j.exp4j;

import net.objecthunter.exp4j.function.Function;

/**
 * Created by gorkemgok on 18/02/15.
 */
public class SellFunction extends Function {
    public SellFunction() {
        super("SELL",1);
    }

    @Override
    public double apply(double... params) {
        if (params[0]==1){
            return 2;
        }else return 0;
    }
}
