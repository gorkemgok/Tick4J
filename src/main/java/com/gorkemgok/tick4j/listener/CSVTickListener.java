package com.gorkemgok.tick4j.listener;

import com.csvreader.CsvReader;
import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.TickDataSet;
import com.gorkemgok.tick4j.core.type.DateData;
import com.gorkemgok.tick4j.core.type.DoubleData;
import com.gorkemgok.tick4j.util.csv.CSVFormat;
import com.gorkemgok.tick4j.util.csv.CSVLoader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by gorkemgok on 27/12/14.
 */
public class CSVTickListener extends AbstractTickListener implements ICSVTickListener {
    public CSVTickListener(TickDataSet set) {
        super(set);
    }

    public void onNewTick(CSVLoader csvLoader) {
    	CsvReader csv = csvLoader.getCsv();
        try {
            set.addRow(new TickDataRow(
                new DateData(new SimpleDateFormat(csvLoader.getCsvFormat().getParameter(CSVFormat.DATE)).parse(csv.get(CSVFormat.DATE)+" "+csv.get(CSVFormat.HOUR))),
                new DoubleData(Double.parseDouble(csv.get(CSVFormat.OPEN))),
                new DoubleData(Double.parseDouble(csv.get(CSVFormat.HIGH))),
                new DoubleData(Double.parseDouble(csv.get(CSVFormat.LOW))),
                new DoubleData(Double.parseDouble(csv.get(CSVFormat.CLOSE))),
                new DoubleData(Double.parseDouble(csv.get(CSVFormat.VOLUME)))
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
