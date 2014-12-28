package com.gorkemgok.data4n.listener;

import com.gorkemgok.data4n.core.row.TickDataRow;
import com.gorkemgok.data4n.core.set.TickDataSet;

/**
 * Created by gorkemgok on 26/12/14.
 */
public class TickListener extends AbstractTickListener implements ITickListener{
    public TickListener(TickDataSet set) {
        super(set);
    }

    @Override
    public void onNewTick(TickDataRow tickDataRow) {
        set.addRow(tickDataRow);
    }
}
