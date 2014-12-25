package com.gorkemgok.data4n.core;

import java.util.ArrayList;

import com.gorkemgok.data4n.core.row.IDataRow;
import com.gorkemgok.data4n.core.type.Data;

public class DataSet implements IDataSet{
	private ArrayList<IDataRow> row = new ArrayList<IDataRow>();
	
	public void addRow(IDataRow r){
		row.add(r);
	}
	
	public IDataRow getRow(int index){
		return row.get(index);
	}
	
	public void setRow(int index, IDataRow r){
		row.set(index, r);
	}
		
	public Data getData(int colIndex, int rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getRowCount(){
		return row.size();
	}
	public int getColumnCount(){
		if (row.size()>0){
			return row.get(0).getColCount();
		}
		return 0;
	}
}
