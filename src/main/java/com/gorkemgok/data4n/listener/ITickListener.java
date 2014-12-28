package com.gorkemgok.data4n.listener;

import com.gorkemgok.data4n.core.row.TickDataRow;

/**
 * Created by gorkemgok on 26/12/14.
 */
public interface ITickListener {
    public void onNewTick(TickDataRow tickDataRow);
}
