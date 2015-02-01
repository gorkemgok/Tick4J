package com.gorkemgok.tick4j.listener;

import com.gorkemgok.tick4j.core.row.TickDataRow;

/**
 * Created by gorkemgok on 26/12/14.
 */
public interface ITickListener {
    public void onNewTick(TickDataRow tickDataRow);
}
