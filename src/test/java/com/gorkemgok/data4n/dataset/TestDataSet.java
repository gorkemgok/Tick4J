package com.gorkemgok.data4n.dataset;

import static org.junit.Assert.*;

import com.gorkemgok.data4n.core.type.DateData;
import org.junit.Test;

import com.gorkemgok.data4n.core.row.CalculatedDataRow;
import com.gorkemgok.data4n.core.row.TickDataRow;
import com.gorkemgok.data4n.core.set.CalculatedDataSet;
import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.core.type.DoubleData;

import java.util.Date;

public class TestDataSet {

	@Test
	public void testGetData() {
		TickDataSet set = new TickDataSet("TEST","TEST");
		set.addRow(new TickDataRow(new DateData(new Date()),new DoubleData(10d),new DoubleData(11d),new DoubleData(12d),new DoubleData(13d),new DoubleData(14d)));
		
		CalculatedDataSet calculatedSet = new CalculatedDataSet("TEST1",null); 
		set.addSet(calculatedSet);
		calculatedSet.addRow(new CalculatedDataRow(new DoubleData(15d)));
		
		CalculatedDataSet calculatedSet2 = new CalculatedDataSet("TEST2",null); 
		set.addSet(calculatedSet2);
		calculatedSet2.addRow(new CalculatedDataRow(new DoubleData(16d)));

		assertEquals("Wrong data returned",10d, set.getData(1, 0).get());
		assertEquals("Wrong data returned",11d, set.getData(2, 0).get());
		assertEquals("Wrong data returned",12d, set.getData(3, 0).get());
		assertEquals("Wrong data returned",13d, set.getData(4, 0).get());
		assertEquals("Wrong data returned",14d, set.getData(5, 0).get());
		assertEquals("Wrong data returned",15d, set.getData(6, 0).get());
		assertEquals("Wrong data returned",16d, set.getData(7, 0).get());
	}

}
