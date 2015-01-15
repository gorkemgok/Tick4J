package com.gorkemgok.data4n.core.set;

import com.gorkemgok.data4n.core.row.IDataRow;

public interface IIterableDataSet {
	public boolean next();
	public IDataRow getRow();
	public void setCurrentIndex(int currentIndex);
	public void begin();
	public void reset();
}
