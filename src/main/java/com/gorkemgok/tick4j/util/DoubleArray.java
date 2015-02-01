package com.gorkemgok.tick4j.util;

import java.util.ArrayList;

import com.gorkemgok.tick4j.core.set.DataSet;
import com.gorkemgok.tick4j.core.type.DoubleData;

public class DoubleArray {
	private ArrayList<double[]> arrays = new ArrayList<double[]>();
	private Integer[] columns;
	
	public DoubleArray(DataSet set,Integer... columns) {
		this.columns = columns;
		int rowCount = set.getRowCount();
		for (int i = 0; i < columns.length; i++) {
			arrays.add(new double[rowCount]);
		}
		set.begin();
		int i = 0;
		while (set.next()){
			int j = 0;
			for (int c : columns){
				if (c<set.getColumnCount()){
					arrays.get(j)[i] = ((DoubleData)set.getRow().getData(c)).get();
				}else{
					c -= set.getColumnCount();
					arrays.get(j)[i] = ((DoubleData)set.getSet(c).getRow().getData(0)).get();
				}
				j++;
			}
			i++;
		}
		set.reset();
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
	
	public double[] getByIndex(int index){
		return arrays.get(index);
	}
}
