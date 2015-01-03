package com.gorkemgok.data4n.core.set;

import com.gorkemgok.data4n.core.row.CalculatedDataRow;

public class CalculatedDataSet extends DataSet {
	
	public CalculatedDataSet(String symbol, String period) {
		super(symbol, period);
	}

	public void addRow(CalculatedDataRow r){
		super.addRow(r);
	}
	
	public CalculatedDataRow getRow(int index){
		return (CalculatedDataRow)super.getRow(index);
	}
	public CalculatedDataRow getRow(){
		return getRow(currentIndex);
	}
	
	public void setRow(int index, CalculatedDataRow r){
		super.setRow(index, r);
	}
}
