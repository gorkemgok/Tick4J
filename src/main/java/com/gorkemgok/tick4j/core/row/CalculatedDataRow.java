package com.gorkemgok.tick4j.core.row;

import com.gorkemgok.tick4j.core.type.DoubleData;

public class CalculatedDataRow extends DynamicDataRow{
	public CalculatedDataRow(DoubleData data){
		addData(data);
	}
	
	public DoubleData getData(int index){
		return (DoubleData) super.getData(index);
	}
}
