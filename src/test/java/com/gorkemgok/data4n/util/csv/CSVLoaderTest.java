package com.gorkemgok.data4n.util.csv;

import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.core.type.DateData;
import com.gorkemgok.data4n.listener.CSVTickListener;
import com.gorkemgok.data4n.util.csv.CSVLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class CSVLoaderTest {
    private TickDataSet set;
    CSVLoader loader;
    @Before
    public void initialize() throws IOException, ParseException {
        set = new TickDataSet("VOBO30","5DK");
        loader = new CSVLoader("resources/vob30_5dk.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
    }
    @Test
    public void testLoad() throws IOException, ParseException {
        loader.load();

        assertEquals("Wrong load", new DateData(new SimpleDateFormat("MM/dd/yy kk:mm:SSS").parse("08/13/14 16:30:000")), set.getData(0, 0));
        assertEquals("Wrong load", new DateData(new SimpleDateFormat("MM/dd/yy k:mm:SSS").parse("08/14/14 9:10:000")), set.getData(0, 15));
    }
}