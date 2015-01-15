package com.gorkemgok.data4n.talib;

import com.gorkemgok.data4n.core.row.IDataRow;
import com.gorkemgok.data4n.core.set.CalculatedDataSet;
import com.gorkemgok.data4n.core.set.DataSet;

import net.objecthunter.exp4j.function.Function;

public class Exp4jFunction extends Function {
	private DataSet set;
	public Exp4jFunction(DataSet set,String name, int argumentCount) {
		super(name, argumentCount);
		this.set = set; 
	}

	@Override
	public double apply(double... params) {
		int setIndex = set.hasDataSet(new CalculatedDataSet(name, params));
		if (setIndex==-1){
			FunctionCalculator calculator = new FunctionCalculator(TALibFunctions.getFunction(super.name),set);
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
