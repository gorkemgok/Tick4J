package com.gorkemgok.data4n.core.row;

import com.gorkemgok.data4n.core.type.DoubleData;

public class CalculatedDataRow extends DynamicDataRow{
	public CalculatedDataRow(DoubleData data){
		addData(data);
	}
	
	public DoubleData getData(int index){
		return (DoubleData) super.getData(index);
	}
	
	public void setData(int index,DoubleData data){
		super.setData(index, data);
	}
}
