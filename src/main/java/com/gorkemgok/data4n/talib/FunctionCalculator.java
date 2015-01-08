package com.gorkemgok.data4n.talib;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.gorkemgok.data4n.core.row.CalculatedDataRow;
import com.gorkemgok.data4n.core.row.TickDataRow;
import com.gorkemgok.data4n.core.set.CalculatedDataSet;
import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.core.type.DoubleData;
import com.gorkemgok.data4n.talib.Function.Param;
import com.gorkemgok.data4n.util.DoubleArray;
import com.tictactec.ta.lib.CoreAnnotated;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.meta.annotation.InputParameterType;
import com.tictactec.ta.lib.meta.annotation.OptInputParameterType;

public class FunctionCalculator {
	private Function function;
	private TickDataSet set;
	
	public FunctionCalculator(Function function, TickDataSet set) {
		super();
		this.function = function;
		this.set = set;
	}
	
	public void calculate(double... params){
		double inputs[][] = new double[function.getInputs().length][];
		Object[] optParams = new Object[function.getOptInputs().length];
		double outputs[][] = new double[function.getOutputs().length][];
		ArrayList<Integer> priceColumns = new ArrayList<Integer>(); 
		int paramIndex = 0;
		for (Param p : function.getInputs()){
			if (p.getType().equals(InputParameterType.TA_Input_Price.name())){
				String prices = p.getName().substring(0,7);
				for (char c : prices.toCharArray()){
					switch (c){
						case 'O':priceColumns.add(TickDataRow.OPEN);
						case 'H':priceColumns.add(TickDataRow.HIGH);
						case 'L':priceColumns.add(TickDataRow.LOW);
						case 'C':priceColumns.add(TickDataRow.CLOSE);
						case 'V':priceColumns.add(TickDataRow.VOLUME);
					}
				}
			}else if(p.getType().equals(InputParameterType.TA_Input_Real.name())){
				priceColumns.add((int)params[paramIndex++]);
			}
		}
		int optParamIndex = 0;
		for (Param p : function.getOptInputs()){
			if (p.getType().equals(OptInputParameterType.TA_OptInput_IntegerRange.name()) || p.getType().equals(OptInputParameterType.TA_OptInput_IntegerList.name())){
				optParams[optParamIndex++] = (int)params[paramIndex++];
			}else if (p.getType().equals(OptInputParameterType.TA_OptInput_RealRange.name()) || p.getType().equals(OptInputParameterType.TA_OptInput_RealList.name())){
				optParams[optParamIndex++] = params[paramIndex++];
			}
		}
		Integer[] priceColumnsArray = priceColumns.toArray(new Integer[priceColumns.size()]);
		DoubleArray doubleArray = new DoubleArray(set,priceColumnsArray);
		int startIdx = 0;
		int endIdx = doubleArray.get(priceColumnsArray[0]).length-1;

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = doubleArray.get(priceColumnsArray[i]);
		}
		for (int i = 0; i < outputs.length; i++) {
			outputs[i] = new double[endIdx+1];
		}
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		
		Object[] allParams = new Object[inputs.length+outputs.length+optParams.length+4];
		int j = 0;
		allParams[j++] = startIdx;
		allParams[j++] = endIdx;
		for (double[] in : inputs){
			allParams[j++] = in;
		}
		for (Object optParam : optParams){
			allParams[j++] = optParam;
		}
		allParams[j++] = outBegIdx;
		allParams[j++] = outNBElement;
		for (double[] out : outputs){
			allParams[j++] = out;
		}
		try {
			function.getTalibMethod().invoke(new CoreAnnotated(), allParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//Core core = new Core();
		//core.sma(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, outReal);
		
		for (double[] out:outputs){
			CalculatedDataSet samSet = new CalculatedDataSet("SAM","5dk");
			set.addSet(samSet);
			for (int i = 0; i < outBegIdx.value; i++) {
				samSet.addRow(new CalculatedDataRow(new DoubleData(0d)));
			}
			for (double o : out) {
				samSet.addRow(new CalculatedDataRow(new DoubleData(o)));
			}
		}
	}
}
