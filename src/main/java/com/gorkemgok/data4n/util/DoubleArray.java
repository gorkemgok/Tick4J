package com.gorkemgok.data4n.util;

import java.util.ArrayList;

import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.core.type.DoubleData;

public class DoubleArray {
	private ArrayList<double[]> arrays = new ArrayList<double[]>();
	private Integer[] columns;
	
	public DoubleArray(TickDataSet set,Integer... columns) {
		this.columns = columns;
		int rowCount = set.getRowCount();
		for (int i = 0; i < columns.length; i++) {
			arrays.add(new double[rowCount]);
		}
		int i = 0;
		while (set.next()){
			int j = 0;
			for (int c : columns){
				arrays.get(j)[i] = ((DoubleData)set.getRow().getData(c)).get();
				j++;
			}
			i++;
		}
	}
	
	public double[] get(int column){
		int i = 0;
		for (int c : columns){
			if (c==column){
				return arrays.get(i);
			}
			i++;
		}
		return null;
	}
}
