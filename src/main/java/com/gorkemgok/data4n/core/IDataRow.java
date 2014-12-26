package com.gorkemgok.data4n.core;

import com.gorkemgok.data4n.core.type.Data;

@SuppressWarnings("rawtypes")
public interface IDataRow {
	public Data getData(int index);
	public void setData(int index,Data d);
	public Data[] getData();
	public int getColCount();
}
