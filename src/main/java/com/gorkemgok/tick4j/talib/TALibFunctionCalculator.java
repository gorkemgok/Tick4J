package com.gorkemgok.tick4j.talib;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.gorkemgok.tick4j.core.row.CalculatedDataRow;
import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.CalculatedDataSet;
import com.gorkemgok.tick4j.core.set.DataSet;
import com.gorkemgok.tick4j.core.type.DoubleData;
import com.gorkemgok.tick4j.talib.TALibFunction.Param;
import com.gorkemgok.tick4j.util.DoubleArray;
import com.tictactec.ta.lib.CoreAnnotated;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.meta.annotation.InputParameterType;
import com.tictactec.ta.lib.meta.annotation.OptInputParameterType;
import com.tictactec.ta.lib.meta.annotation.OutputParameterType;

public class TALibFunctionCalculator {
	private TALibFunction TALibFunction;
	private DataSet set;
	
	public TALibFunctionCalculator(TALibFunction TALibFunction, DataSet set) {
		super();
		this.TALibFunction = TALibFunction;
		this.set = set;
	}
	
	public void calculate(double... params){
		ArrayList<Integer> priceColumns = new ArrayList<Integer>(); 
		int paramIndex = 0;
		for (Param p : TALibFunction.getInputs()){
			if (p.getType().equals(InputParameterType.TA_Input_Price.name())){
				String prices = p.getName().substring(7,p.getName().length());
				for (char c : prices.toCharArray()){
					switch (c){
						case 'O':priceColumns.add(TickDataRow.OPEN);break;
						case 'H':priceColumns.add(TickDataRow.HIGH);break;
						case 'L':priceColumns.add(TickDataRow.LOW);break;
						case 'C':priceColumns.add(TickDataRow.CLOSE);break;
						case 'V':priceColumns.add(TickDataRow.VOLUME);break;
					}
				}
			}else if(p.getType().equals(InputParameterType.TA_Input_Real.name())){
				priceColumns.add((int)params[paramIndex++]);
			}
		}
		
		double inputs[][] = new double[priceColumns.size()][];
		Object[] optParams = new Object[TALibFunction.getOptInputs().length];
		Object[] outputs = new Object[TALibFunction.getOutputs().length];
		
		int optParamIndex = 0;
		for (Param p : TALibFunction.getOptInputs()){
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
			if (TALibFunction.getOutputs()[i].getType().equals(OutputParameterType.TA_Output_Integer.toString())){
				outputs[i] = new int[endIdx+1];
			}else{
				outputs[i] = new double[endIdx+1];
			}
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
		int optIndex = 0;
		for (Object optParam : optParams){
			Param optInput = TALibFunction.getOptInputs()[optIndex];
			if (optInput.getType().equals(OptInputParameterType.TA_OptInput_IntegerList.toString()) ||
					optInput.getType().equals(OptInputParameterType.TA_OptInput_IntegerRange.toString())){
				int optIntParam = (Integer)optParam;
				if (optInput.getName().equals("optInMAType")){
					switch(optIntParam){
						case 0 : allParams[j++] = MAType.Sma;break;
						case 1 : allParams[j++] = MAType.Ema;break;
						case 2 : allParams[j++] = MAType.Wma;break;
						case 3 : allParams[j++] = MAType.Dema;break;
						case 4 : allParams[j++] = MAType.Tema;break;
						case 5 : allParams[j++] = MAType.Trima;break;
						case 6 : allParams[j++] = MAType.Kama;break;
						case 7 : allParams[j++] = MAType.Mama;break;
						case 8 : allParams[j++] = MAType.T3;break;
					}
				}else{
					allParams[j++] = optIntParam;
				}
			}else{
				allParams[j++] = optParam;
			}
			optIndex++;
		}
		allParams[j++] = outBegIdx;
		allParams[j++] = outNBElement;
		int i = 0;
		for (Object out : outputs){
			allParams[j++] = out;		
		}
		try {
			TALibFunction.getTalibMethod().invoke(new CoreAnnotated(), allParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//Core core = new Core();
		//core.sma(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, outReal);
		
		int ni = 0;
		for (Object out:outputs){
			double[] calculatedDataSetParams = params.clone();
			if (outputs.length>1){
				calculatedDataSetParams[calculatedDataSetParams.length-1] = ni;
			}
			String calculatedDataSetName = TALibFunction.getName();
			/*if (TALibFunction.getOutputs().length>1){
				calculatedDataSetName += "_"+TALibFunction.getOutputs()[ni].getName();
			}*/
			CalculatedDataSet calculatedDataSet = new CalculatedDataSet(calculatedDataSetName,calculatedDataSetParams);
			set.addSet(calculatedDataSet);
			for (i = 0; i < outBegIdx.value; i++) {
				calculatedDataSet.addRow(new CalculatedDataRow(new DoubleData(0d)));
			}
			if (TALibFunction.getOutputs()[ni].getType().equals(OutputParameterType.TA_Output_Integer.toString())){
				for (int o : (int[])out) {
					calculatedDataSet.addRow(new CalculatedDataRow(new DoubleData((double) o)));
				}
			}else{
				for (double o : (double[])out) {
					calculatedDataSet.addRow(new CalculatedDataRow(new DoubleData(o)));
				}
			}
			ni++;
		}
	}
}
