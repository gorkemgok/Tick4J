package com.gorkemgok.data4n.util.csv;

import java.text.ParseException;

/**
 * Created by gorkemgok on 26/12/14.
 */
public class CSVFormat {
    public static final String DATETIME = "DATETIME";
    public static final String DATE = "DATE";
    public static final String HOUR = "HOUR";
    public static final String OPEN = "OPEN";
    public static final String HIGH = "HIGH";
    public static final String LOW = "LOW";
    public static final String CLOSE = "CLOSE";
    public static final String VOLUME = "VOLUME";

    private String format;
    private String parameterSeperator = ">";
    private LineFormat[] lineFormats;

    public CSVFormat(String format) {
        this.format = format;
    }

    public CSVFormat(String format, String parameterSeperator) {
        this.format = format;
        this.parameterSeperator = parameterSeperator;
    }

    public void format() throws ParseException {
        String[] formatElems = format.split(",");
        lineFormats = new LineFormat[formatElems.length];
        int i = 0;
        for(String formatElem : formatElems){
            String[] elemParts = formatElem.split(parameterSeperator);
            String symbol = elemParts[0];
            String parameter = null;
            if (elemParts.length>1) parameter = elemParts[1];
            lineFormats[i] = new LineFormat(symbol,parameter);
            i++;
        }
    }

    public String[] getSymbols(){
        String[] symbols = new String[lineFormats.length];
        for (int i = 0; i < lineFormats.length; i++) {
            symbols[i] = lineFormats[i].getSymbol();
        }
        return symbols;
    }

    public String[] getParameters(){
        String[] parameters = new String[lineFormats.length];
        for (int i = 0; i < lineFormats.length; i++) {
            parameters[i] = lineFormats[i].getParameter();
        }
        return parameters;
    }

    public String getParameter(String header){
        for (int i = 0; i < lineFormats.length; i++) {
            if (lineFormats[i].getSymbol().equals(header)){
                return lineFormats[i].getParameter();
            }
        }
        return null;
    }

    public class LineFormat{
        private String symbol;
        private String parameter;

        public LineFormat(String symbol, String parameter) {
            this.symbol = symbol;
            this.parameter = parameter;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }
    }

}
