package com.gorkemgok.tick4j.exp4j;

import net.objecthunter.exp4j.function.Function;

/**
 * Created by gorkemgok on 18/02/15.
 */
public class BuyFunction extends Function {
    public BuyFunction() {
        super("BUY",1);
    }

    @Override
    public double apply(double... params) {
        if (params[0]==1){
            return 1;
        }else return 0;
    }
}
