package com.gorkemgok.data4n.listener;

import com.gorkemgok.data4n.core.set.TickDataSet;

/**
 * Created by gorkemgok on 26/12/14.
 */
public abstract class AbstractTickListener{
    protected TickDataSet set;

    public AbstractTickListener(TickDataSet set) {
        this.set = set;
    }
}
