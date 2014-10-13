package com.gorkemgok.data4n;

import java.util.ArrayList;

public class DataSet {
	private ArrayList<DataRow> row = new ArrayList<DataRow>();
	
	public void add(DataRow r){
		row.add(r);
	}
	
	public DataRow getRow(int index){
		return row.get(index);
	}
	
	public void setRow(int index, DataRow r){
		row.set(index, r);
	}
	
	public ArrayList<DataRow> getRowList(){
		return row;
	}
	
	public int getRowCount(){
		return row.size();
	}
	public int getColCount(){
		if (row.size()>0){
			return row.get(0).getColCount();
		}
		return 0;
	}
}
