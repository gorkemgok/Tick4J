package com.gorkemgok.tick4j.listener;

import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 26/12/14.
 */
public abstract class AbstractTickListener{
    protected TickDataSet set;

    public AbstractTickListener(TickDataSet set) {
        this.set = set;
    }
}
