package com.gorkemgok.data4n.core.set;

import com.gorkemgok.data4n.core.row.IDataRow;
import com.gorkemgok.data4n.core.type.Data;

public interface IDataSet {
	public void addRow(IDataRow dataRow);
	public IDataRow getRow(int index);
	public void setRow(int index, IDataRow dataRow);
	
	public void addSet(IDataSet set);
	public IDataSet getSet(int index);
	@SuppressWarnings("rawtypes")
	public Data getData(int colIndex, int rowIndex);
	public int getRowCount();
	public int getColumnCount();
}
