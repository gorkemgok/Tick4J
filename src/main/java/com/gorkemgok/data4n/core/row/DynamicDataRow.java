package com.gorkemgok.data4n.core.row;

import java.util.ArrayList;

import com.gorkemgok.data4n.core.type.Data;
@SuppressWarnings("rawtypes")
public class DynamicDataRow extends AbstractDataRow {
	private ArrayList<Data> data = new ArrayList<Data>();
	public void addData(Data d){
		data.add(d);
	}
	public Data getData(int index){
		return data.get(index);
	}
	public void setData(int index,Data d){
		data.set(index, d);
	}
	public Data[] getData(){
		return data.toArray(new Data[data.size()]);
	}
	public int getColCount(){
		return data.size();
	}
}
