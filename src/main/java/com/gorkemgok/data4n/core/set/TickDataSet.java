package com.gorkemgok.data4n.core.set;

import com.gorkemgok.data4n.core.row.TickDataRow;

public class TickDataSet extends DataSet {


	public TickDataSet(String symbol, String period) {
		super(symbol, period);
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
	
}
