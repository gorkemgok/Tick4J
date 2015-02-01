package com.gorkemgok.tick4j.listener;

import com.gorkemgok.tick4j.util.csv.CSVLoader;

/**
 * Created by gorkemgok on 27/12/14.
 */
public interface ICSVTickListener {
    public void onNewTick(CSVLoader csv);
}
