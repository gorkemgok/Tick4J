package com.gorkemgok.data4n.core;

public abstract class AbstractDataRow implements IDataRow{
	private int rowNumber;

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	
}