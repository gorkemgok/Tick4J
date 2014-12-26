package com.gorkemgok.data4n.core.row;

import com.gorkemgok.data4n.core.type.DoubleData;

public class TickDataRow extends DynamicDataRow{
	public final static int OPEN = 0;
	public final static int HIGH = 1;
	public final static int LOW = 2;
	public final static int CLOSE = 3;
	public final static int VOLUME = 4;
    
	public TickDataRow(DoubleData open, DoubleData high, DoubleData low, DoubleData close, DoubleData volume) {
		super();
		addData(open);
		addData(high);
		addData(low);
		addData(close);
		addData(volume);
	}

	public DoubleData getData(int index) {
		return (DoubleData)super.getData(index);
	}

	public void setData(int index, DoubleData d) {
		super.setData(index,d);
	}
	
	public double getOpen(){
		return getData(OPEN).get();
	}
	
	public double getHigh(){
		return getData(HIGH).get();
	}
	
	public double getLow(){
		return getData(LOW).get();
	}
	
	public double getClose(){
		return getData(CLOSE).get();
	}
	
	public double getVolume(){
		return getData(VOLUME).get();
	}

}
