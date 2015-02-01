package com.gorkemgok.tick4j.core.set;

import com.gorkemgok.tick4j.core.row.IDataRow;

public interface IIterableDataSet {
	public boolean next();
	public IDataRow getRow();
	public void setCurrentIndex(int currentIndex);
	public void begin();
	public void reset();
}
