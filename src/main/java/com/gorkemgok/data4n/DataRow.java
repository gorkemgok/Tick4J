package com.gorkemgok.data4n;

import java.util.ArrayList;
@SuppressWarnings("rawtypes")
public class DataRow {
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
	public ArrayList<Data> getData(){
		return data;
	}
	public int getColCount(){
		return data.size();
	}
}
