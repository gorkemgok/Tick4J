package com.gorkemgok.tick4j.function;

import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 05/02/15.
 */
public abstract class AbstractFunction implements IFunction {
    protected TickDataSet set;

    public AbstractFunction(TickDataSet set) {
        this.set = set;
    }
}
