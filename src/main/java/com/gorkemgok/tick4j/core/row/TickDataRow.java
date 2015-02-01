package com.gorkemgok.tick4j.core.row;

import com.gorkemgok.tick4j.core.type.DateData;
import com.gorkemgok.tick4j.core.type.DoubleData;

import java.util.Date;

public class TickDataRow extends DynamicDataRow{
	public final static int DATE = 0;
	public final static int OPEN = 1;
	public final static int HIGH = 2;
	public final static int LOW = 3;
	public final static int CLOSE = 4;
	public final static int VOLUME = 5;
    
	public TickDataRow(DateData date,DoubleData open, DoubleData high, DoubleData low, DoubleData close, DoubleData volume) {
		super();
		addData(date);
		addData(open);
		addData(high);
		addData(low);
		addData(close);
		addData(volume);
	}

	public Date getDate(){
		return (Date)getData(DATE).get();
	}

	public double getOpen(){
		return (Double)getData(OPEN).get();
	}
	
	public double getHigh(){
		return (Double)getData(HIGH).get();
	}
	
	public double getLow(){
		return (Double)getData(LOW).get();
	}
	
	public double getClose(){
		return (Double)getData(CLOSE).get();
	}
	
	public double getVolume(){
		return (Double)getData(VOLUME).get();
	}

}
