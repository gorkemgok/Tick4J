package com.gorkemgok.tick4j.function;

import com.gorkemgok.tick4j.core.set.CalculatedDataSet;

/**
 * Created by gorkemgok on 05/02/15.
 */
public interface IFunction {
    String getName();
    int getArgumentCount();
    CalculatedDataSet[] calculate(double... params);
}
