package com.gorkemgok.data4n.core.set;

import java.util.Arrays;
import java.util.List;

import com.gorkemgok.data4n.core.row.TickDataRow;

public class TickDataSet extends DataSet {
	private String symbol;
	private String period;

	public TickDataSet(String symbol, String period) {
		this.symbol = symbol;
		this.period = period;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public String getPeriod() {
		return period;
	}

	public TickDataRow getRow(int index){
		return (TickDataRow)super.getRow(index);
	}
	
	public TickDataRow getRow(){
		return getRow(currentIndex);
	}

	public void addRow(TickDataRow r){
		super.addRow(r);
	}
	
	public void setRow(int index, TickDataRow r){
		super.setRow(index, r);
	}
	
	public boolean hasCalculatedDataSet(String name, double[] parameters){
		List<DataSet> sets = getSets();
		for (DataSet set : sets){
			if (set instanceof CalculatedDataSet){
				CalculatedDataSet calculatedDataSet = (CalculatedDataSet)set;
				if (calculatedDataSet.getName().equals(name) && Arrays.equals(calculatedDataSet.getParameters(), parameters)){
					return true;
				}
			}
		}
		return false;
	}
	
}
