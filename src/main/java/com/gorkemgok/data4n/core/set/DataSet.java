package com.gorkemgok.data4n.core.set;

import java.util.ArrayList;
import java.util.List;

import com.gorkemgok.data4n.core.row.IDataRow;
import com.gorkemgok.data4n.core.type.Data;

public class DataSet implements IDataSet, IIterableDataSet{
	private ArrayList<IDataRow> rows = new ArrayList<IDataRow>();
	private ArrayList<DataSet> sets = new ArrayList<DataSet>();
	protected int currentIndex = -1;
	
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

	public void addSet(DataSet set) {
		sets.add(set);		
	}

	public DataSet getSet(int index) {
		return sets.get(index);
	}
	
	protected List<DataSet> getSets(){
		return sets;
	}

	public boolean next() {
		for (DataSet set : sets){
			if (!set.next()) return false;
		}
		currentIndex++;
		if (currentIndex<getRowCount()) {
			return true;
		}
		return false;
	}

	public IDataRow getRow() {
		return getRow(currentIndex);
	}

	public void reset() {
		currentIndex = -1;
		for (DataSet set : sets){
			set.reset();
		}
		
	}

	public void addSet(IDataSet set) {
		// TODO Auto-generated method stub
		
	}
}
