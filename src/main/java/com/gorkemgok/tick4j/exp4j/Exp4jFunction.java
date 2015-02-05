package com.gorkemgok.tick4j.exp4j;

import com.gorkemgok.tick4j.core.row.IDataRow;
import com.gorkemgok.tick4j.core.set.CalculatedDataSet;
import com.gorkemgok.tick4j.core.set.DataSet;

import com.gorkemgok.tick4j.talib.TALibFunctionCalculator;
import com.gorkemgok.tick4j.talib.TALibFunctions;
import net.objecthunter.exp4j.function.Function;

public class Exp4jFunction extends Function {
	private DataSet set;
	public Exp4jFunction(DataSet set,String name, int argumentCount) {
		super(name, argumentCount);
		this.set = set; 
	}

	@Override
	public double apply(double... params) {
		//TODO:control if parameter count
		int setIndex = set.hasDataSet(new CalculatedDataSet(name, params));
		if (setIndex==-1){
			TALibFunctionCalculator calculator = new TALibFunctionCalculator(TALibFunctions.getFunction(super.name),set);
			calculator.calculate(params);
		}
		setIndex = set.hasDataSet(new CalculatedDataSet(name, params));
		if(setIndex>-1) {
			DataSet subSet = set.getSet(setIndex);
			IDataRow row = subSet.getRow();
			Object data = row.getData(0).get();
			return (Double) data;
		}
		throw new RuntimeException("Can't calculate ot find function");
	}

}
