package com.gorkemgok.tick4j.exp4j;

import net.objecthunter.exp4j.function.Function;

/**
 * Created by gorkemgok on 18/02/15.
 */
public class StrategyFunction extends Function {
    public StrategyFunction() {
        super("STRATEGY",2);
    }

    @Override
    public double apply(double... params) {
        return params[0] + params[1];
    }
}
