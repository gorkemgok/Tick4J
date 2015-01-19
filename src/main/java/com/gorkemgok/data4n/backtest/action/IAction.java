package com.gorkemgok.data4n.backtest.action;

import com.gorkemgok.data4n.backtest.action.result.ActionResult;
import com.gorkemgok.data4n.core.set.TickDataSet;

/**
 * Created by gorkemgok on 19/01/15.
 */
public interface IAction {
    public ActionResult run(TickDataSet set);
}
