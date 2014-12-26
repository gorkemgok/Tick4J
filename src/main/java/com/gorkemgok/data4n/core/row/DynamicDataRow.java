package com.gorkemgok.data4n.core.row;

import java.util.ArrayList;

import com.gorkemgok.data4n.core.AbstractDataRow;
import com.gorkemgok.data4n.core.type.Data;
@SuppressWarnings("rawtypes")
public class DynamicDataRow extends AbstractDataRow {
	private ArrayList<Data> dataList = new ArrayList<Data>();
	public void addData(Data data){
		dataList.add(data);
	}
	public Data getData(int index){
		return dataList.get(index);
	}
	public void setData(int index,Data data){
		dataList.set(index, data);
	}
	public Data[] getData(){
		return dataList.toArray(new Data[dataList.size()]);
	}
	public int getColCount(){
		return dataList.size();
	}
}
