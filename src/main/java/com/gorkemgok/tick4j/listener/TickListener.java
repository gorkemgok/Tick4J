package com.gorkemgok.tick4j.listener;

import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 26/12/14.
 */
public class TickListener extends AbstractTickListener implements ITickListener{
    public TickListener(TickDataSet set) {
        super(set);
    }

    public void onNewTick(TickDataRow tickDataRow) {
        set.addRow(tickDataRow);
    }
}
