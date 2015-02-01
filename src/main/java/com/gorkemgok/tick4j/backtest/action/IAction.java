package com.gorkemgok.tick4j.backtest.action;

import com.gorkemgok.tick4j.backtest.action.result.ActionResult;
import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 19/01/15.
 */
public interface IAction {
    public ActionResult run(TickDataSet set);
}
