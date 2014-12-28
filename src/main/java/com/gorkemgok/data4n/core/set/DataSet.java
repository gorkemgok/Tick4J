package com.gorkemgok.data4n.core.set;

import java.util.ArrayList;

import com.gorkemgok.data4n.core.row.IDataRow;
import com.gorkemgok.data4n.core.type.Data;

public class DataSet implements IDataSet{
	private ArrayList<IDataRow> rows = new ArrayList<IDataRow>();
	private ArrayList<IDataSet> sets = new ArrayList<IDataSet>();
	
	public void addRow(IDataRow r){
		rows.add(r);
	}
	
	public IDataRow getRow(int index){
		return rows.get(index);
	}
	
	public void setRow(int index, IDataRow r){
		rows.set(index, r);
	}
		
	@SuppressWarnings("rawtypes")
	public Data getData(int colIndex, int rowIndex) {
		int rowsColCount = rows.get(0).getColCount();
		if (colIndex>=rowsColCount){
			int setIndex = colIndex-rowsColCount;
			for (IDataSet set : sets){
			  if (set.getColumnCount()>setIndex){
				  return set.getData(setIndex, rowIndex);
			  }else{
				  setIndex -= set.getColumnCount();
			  }
			}
			return null;
		}else{
			return getRow(rowIndex).getData(colIndex);
		}
	}
	
	public int getRowCount(){
		return rows.size();
	}
	public int getColumnCount(){
		if (rows.size()>0){
			return rows.get(0).getColCount();
		}
		return 0;
	}

	public void addSet(IDataSet set) {
		sets.add(set);		
	}

	public IDataSet getSet(int index) {
		return sets.get(index);
	}
}
