package com.gorkemgok.data4n.talib;

import com.gorkemgok.data4n.core.set.CalculatedDataSet;
import com.gorkemgok.data4n.core.set.DataSet;

import net.objecthunter.exp4j.function.Function;

public class Exp4jPosFunction extends Function {
	private final static String POS_FUNCTION_PREFIX = "P_";
	private DataSet set;
	public Exp4jPosFunction(DataSet set,String name, int argumentCount) {
		super(name, argumentCount);
		this.set = set; 
	}

	@Override
	public double apply(double... params) {
		//TODO:control if parameter count
		//String orgName = super.name.replaceAll(POS_FUNCTION_PREFIX, "");
		String orgName = name.toUpperCase();
		int setIndex = set.hasDataSet(new CalculatedDataSet(orgName, params));	
		if (setIndex==-1){
			FunctionCalculator calculator = new FunctionCalculator(TALibFunctions.getFunction(orgName),set);
			calculator.calculate(params);
		}
		setIndex = set.hasDataSet(new CalculatedDataSet(orgName, params));
		if(setIndex>-1) {
			return setIndex+set.getColumnCount();
		}
		throw new RuntimeException("Can't calculate ot find function");
	}

}
