package com.gorkemgok.data4n.core.set;

import com.gorkemgok.data4n.core.row.TickDataRow;

public class TickDataSet extends DataSet {
	
	public void addRow(TickDataRow r){
		super.addRow(r);
	}
	
	public TickDataRow getRow(int index){
		return (TickDataRow)super.getRow(index);
	}
	
	public void setRow(int index, TickDataRow r){
		super.setRow(index, r);
	}
	
}
