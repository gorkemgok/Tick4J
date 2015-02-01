package com.gorkemgok.tick4j.core.row;

import com.gorkemgok.tick4j.core.type.Data;

@SuppressWarnings("rawtypes")
public interface IDataRow {
	public Data getData(int index);
	public void setData(int index,Data d);
	public Data[] getData();
	public int getColCount();
}
