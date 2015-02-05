/**
 * Created by gorkemgok on 26/12/14.
 */
package com.gorkemgok.tick4j.util.csv;

import com.csvreader.CsvReader;
import com.gorkemgok.tick4j.listener.ICSVTickListener;
import com.gorkemgok.tick4j.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class CSVLoader {
    private ArrayList<ICSVTickListener> listeners = new ArrayList<ICSVTickListener>();
    private CsvReader csv;
    CSVFormat csvFormat;

    public CSVLoader(String fileName,String format) throws ParseException {
        try {
            csv = new CsvReader(fileName);

            if (format!=null){
                csvFormat = new CSVFormat(format);
                csvFormat.format();
                csv.setHeaders(csvFormat.getSymbols());
            }else{
                csv.getHeaders();
            }
        } catch (FileNotFoundException e) {
            Log.get().error(new File(fileName).getAbsoluteFile());
            Log.get().error(e);
        } catch (IOException e) {
            Log.get().error(e);
        }
    }

    public void addListener(ICSVTickListener listener){
        listeners.add(listener);
    }

    private void trigListeners(){
        for(ICSVTickListener listener : listeners){
            listener.onNewTick(this);
        }
    }

    public void load() throws IOException {
        while(csv.readRecord()){
            trigListeners();
        }
    }

    public CsvReader getCsv() {
        return csv;
    }

    public CSVFormat getCsvFormat() {
        return csvFormat;
    }

    public void close() throws IOException{
        csv.close();
    }
}
