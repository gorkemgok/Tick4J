package com.gorkemgok.tick4j.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Model {
	public static final int TIMEUNIT_MINUTE = 0;
	public static final int TIMEUNIT_HOUR = 1;
	public  static final int TIMEUNIT_DAY = 2;
	public static final int NORMALIZE_NONE = 0;
	public static final int NORMALIZE_AFTERCALCULATION = 1;
	public static final int NORMALIZE_BEFORECALCULATION = 2;
	private int normalize = NORMALIZE_NONE;
	private int dataRatio = -1;
	private int dataStep = 1;
	private int[][] dataIn;
	private String[] dataInFn; 
	private int[][] dataOut;
	private String[] dataOutFn;
	private String trainFrom = "";
	private String trainTo = "";
	private String testFrom = "";
	private String testTo = "";
	private int maxDataRowNumber = 0;
	private String name = "";
	private String[] set = {};
	private int timeUnit = TIMEUNIT_DAY;
	
	public int getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSet() {
		return set;
	}
	public void setSet(String[] set) {
		this.set = set;
	}
	public int getDataRatio() {
		return dataRatio;
	}
	public void setDataRatio(int dataRatio) {
		this.dataRatio = dataRatio;
	}
	public int getDataStep() {
		return dataStep;
	}
	public void setDataStep(int dataStep) {
		this.dataStep = dataStep;
	}
	public Date getTrainFrom() throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy.HH-mm").parse(trainFrom);
	}
	public void setTrainFrom(String trainFrom) {
		this.trainFrom = trainFrom;
	}
	public Date getTrainTo() throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy.HH-mm").parse(trainTo);
	}
	public void setTrainTo(String trainTo) {
		this.trainTo = trainTo;
	}
	public Date getTestFrom() throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy.HH-mm").parse(testFrom);
	}
	public void setTestFrom(String testFrom) {
		this.testFrom = testFrom;
	}
	public Date getTestTo() throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy.HH-mm").parse(testTo);
	}
	public void setTestTo(String testTo) {
		this.testTo = testTo;
	}
	
	public void setMaxDataRowNumber(int maxDataRowNumber) {
		this.maxDataRowNumber = maxDataRowNumber;
	}
	public int getMaxDataRowNumber() {
		return maxDataRowNumber;
	}
	public int getNormalize() {
		return normalize;
	}
	public void setNormalize(int normalize) {
		this.normalize = normalize;
	}
	public int[][] getDataIn() {
		return dataIn;
	}
	public void setDataIn(int[][] dataIn) {
		this.dataIn = dataIn;
	}
	public String[] getDataInFn() {
		return dataInFn;
	}
	public void setDataInFn(String[] dataInFn) {
		this.dataInFn = dataInFn;
	}
	public int[][] getDataOut() {
		return dataOut;
	}
	public void setDataOut(int[][] dataOut) {
		this.dataOut = dataOut;
	}
	public String[] getDataOutFn() {
		return dataOutFn;
	}
	public void setDataOutFn(String[] dataOutFn) {
		this.dataOutFn = dataOutFn;
	}
	
	
}
