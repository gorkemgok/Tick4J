package com.gorkemgok.tick4j.talib;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import com.gorkemgok.tick4j.listener.CSVTickListener;
import com.gorkemgok.tick4j.util.DoubleArray;
import com.gorkemgok.tick4j.util.csv.CSVLoader;

public class FunctionsTest {
	private DoubleArray doubleArray;
	private TickDataSet set;
	@Before
	public void initialize() throws IOException, ParseException{
		set = new TickDataSet("VOBO30","5DK");
		CSVLoader loader = new CSVLoader("resources/vob30_5dk_100.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();
		doubleArray = new DoubleArray(set,TickDataRow.HIGH,TickDataRow.VOLUME);
		set.reset();
	}
	@Test
	public void test() throws SecurityException, ClassNotFoundException {
		//new Functions().getMethods();
		
	}

}
