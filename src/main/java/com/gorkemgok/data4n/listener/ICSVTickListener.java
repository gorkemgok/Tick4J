package com.gorkemgok.data4n.listener;

import com.gorkemgok.data4n.util.csv.CSVLoader;

/**
 * Created by gorkemgok on 27/12/14.
 */
public interface ICSVTickListener {
    public void onNewTick(CSVLoader csv);
}
