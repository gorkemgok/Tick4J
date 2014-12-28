package com.gorkemgok.data4n.util.csv;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class CSVFormatTest {
    CSVFormat format;
    @Before
    public void initialize() throws ParseException {
        format = new CSVFormat("DATE>dd/MM/yy kk:MM:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        format.format();
    }

    @Test
    public void testGetSymbols() throws Exception {
        assertEquals(format.getSymbols()[0],"DATE");
        assertEquals(format.getSymbols()[1],"HOUR");
        assertEquals(format.getSymbols()[2],"OPEN");
        assertEquals(format.getSymbols()[3],"HIGH");
        assertEquals(format.getSymbols()[4],"LOW");
        assertEquals(format.getSymbols()[5],"CLOSE");
        assertEquals(format.getSymbols()[6],"VOLUME");
    }

    @Test
    public void testGetParameters() throws Exception {
        assertEquals(format.getParameters()[0],"dd/MM/yy kk:MM:SSS");
    }

    @Test
    public void testGetParameter() throws Exception {
        assertEquals(format.getParameter("DATE"),"dd/MM/yy kk:MM:SSS");
    }
}