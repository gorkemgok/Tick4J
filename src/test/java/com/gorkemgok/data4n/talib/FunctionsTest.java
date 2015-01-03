package com.gorkemgok.data4n.talib;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.gorkemgok.data4n.core.row.CalculatedDataRow;
import com.gorkemgok.data4n.core.row.TickDataRow;
import com.gorkemgok.data4n.core.set.CalculatedDataSet;
import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.core.type.DoubleData;
import com.gorkemgok.data4n.listener.CSVTickListener;
import com.gorkemgok.data4n.util.DoubleArray;
import com.gorkemgok.data4n.util.csv.CSVLoader;
import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;

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
		double[] H = doubleArray.get(TickDataRow.HIGH);
		int period = 30;
		int startIdx = 0;
		int endIdx = H.length-1;
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		double[] outReal = new double[H.length-period+1];
		Core core = new Core();
		core.sma(startIdx, endIdx, H, period, outBegIdx, outNBElement, outReal);

		CalculatedDataSet samSet = new CalculatedDataSet("SAM","5dk");
		set.addSet(samSet);
		for (int i = 0; i < outBegIdx.value; i++) {
			samSet.addRow(new CalculatedDataRow(new DoubleData(0d)));
		}
		for (double o : outReal) {
			samSet.addRow(new CalculatedDataRow(new DoubleData(o)));
		}

		double total = 0;
		int i = 0;
		for (int j = set.getRowCount()-1; j > set.getRowCount()-period-1; j--) {
			total += (Double)set.getRow(j).getData(TickDataRow.HIGH).get();
			i++;
		}
		assertEquals("Wrong period", period, i);
		assertEquals("Wrong calculation",total/period, set.getSet(0).getRow(set.getRowCount()-1).getData(0).get());
	}

}
