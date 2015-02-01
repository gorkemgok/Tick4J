package com.gorkemgok.tick4j.talib;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import com.gorkemgok.tick4j.listener.CSVTickListener;
import com.gorkemgok.tick4j.util.csv.CSVLoader;

public class FunctionCalculatorTest {
	private TickDataSet set;
	@Before
	public void initialize() throws IOException, ParseException{
		set = new TickDataSet("VOBO30","5DK");
		CSVLoader loader = new CSVLoader("resources/vob30_5dk.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();
	}
	@Test
	public void test() {
		int period = 2;
		Function[] functions = TALibFunctions.getFunctions();
		for (Function function : functions){
			if (function.getName().equals("SMA")){
				FunctionCalculator calculator = new FunctionCalculator(function, set);
				calculator.calculate(TickDataRow.CLOSE,period);
				
				double total = 0;
				for (int j = set.getRowCount()-1; j > set.getRowCount()-period-1; j--) {
					total += (Double)set.getRow(j).getData(TickDataRow.CLOSE).get();
				}
				assertEquals("Wrong calculation",total/period, set.getSet(0).getRow(set.getRowCount()-1).getData(0).get());
			}
		}
	}
}
