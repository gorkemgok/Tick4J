package com.gorkemgok.tick4j.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import com.gorkemgok.tick4j.listener.CSVTickListener;
import com.gorkemgok.tick4j.util.csv.CSVLoader;

public class DoubleArrayTest {
	private DoubleArray doubleArray;
	private TickDataSet set;
	@Before
	public void initialize() throws IOException, ParseException{
		set = new TickDataSet("VOBO30","5DK");
		CSVLoader loader = new CSVLoader("resources/vob30_5dk.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();
		doubleArray = new DoubleArray(set,TickDataRow.HIGH,TickDataRow.VOLUME);
	}
	@Test
	public void testGet() {
		assertNotEquals("Wrong row size", 0, set.getRowCount());
		for (int i = 0; i < set.getRowCount(); i++) {
			assertEquals("Wrong data",doubleArray.get(TickDataRow.HIGH)[i],set.getData(TickDataRow.HIGH, i).get());
			assertNotEquals("Wrong data",doubleArray.get(TickDataRow.VOLUME)[i],set.getData(TickDataRow.HIGH, i).get());
		}
		
	}

}
