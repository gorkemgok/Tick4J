package com.gorkemgok.data4n;

import java.util.ArrayList;

import com.gorkemgok.data4n.core.IDataRow;
import com.gorkemgok.data4n.core.type.Data;
import com.gorkemgok.data4n.core.type.DoubleData;

public class _TickDataRow implements IDataRow{
	public final static int OPEN = 0;
	public final static int HIGH = 1;
	public final static int LOW = 2;
	public final static int CLOSE = 3;
	public final static int VOLUME = 4;
	private DoubleData open;
	private DoubleData high;
	private DoubleData low;
	private DoubleData close;
	private DoubleData volume;
    
	public _TickDataRow(DoubleData open, DoubleData high, DoubleData low, DoubleData close, DoubleData volume) {
		super();
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public double getOpen(){
		return (Double)getData(0).get();
	}

	public DoubleData getData(int index) {
		switch (index){
			case 0 : return open;
			case 1 : return high;
			case 2 : return low;
			case 3 : return close;
			case 4 : return volume;
			default : return null;
		}
	}

	public void setData(int index, DoubleData d) {
		switch (index){
			case 0 : open = d;
			case 1 : high = d;
			case 2 : low = d;
			case 3 : close = d;
			case 4 : volume = d;
		}
		
	}

	public int getColCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setData(int index, Data d) {
		// TODO Auto-generated method stub
		
	}

	public Data[] getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
