package com.gorkemgok.tick4j.function;

import com.gorkemgok.tick4j.core.set.CalculatedDataSet;
import com.gorkemgok.tick4j.core.set.TickDataSet;

/**
 * Created by gorkemgok on 05/02/15.
 */
public class Cross extends AbstractFunction implements IFunction {
    private static final String NAME = "CROSS";
    public Cross(TickDataSet set) {
        super(set);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getArgumentCount() {
        return 2;
    }

    @Override
    public CalculatedDataSet[] calculate(double... params) {
        boolean lastStatus = false;
        CalculatedDataSet calculatedDataSet = new CalculatedDataSet(NAME,params);
        set.begin();
        while (set.next()){
            //set.get
        }
        set.reset();
        return null;
    }
}
