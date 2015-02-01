package com.gorkemgok.tick4j.core.set;

import com.gorkemgok.tick4j.core.row.TickDataRow;

public class TickDataSet extends DataSet {
	private String symbol;
	private String period;

	public TickDataSet(String symbol, String period) {
		this.symbol = symbol;
		this.period = period;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public String getPeriod() {
		return period;
	}

	public TickDataRow getRow(int index){
		return (TickDataRow)super.getRow(index);
	}
	
	public TickDataRow getRow(){
		return getRow(currentIndex);
	}

	public void addRow(TickDataRow r){
		super.addRow(r);
	}
	
	public void setRow(int index, TickDataRow r){
		super.setRow(index, r);
	}
	
	public CalculatedDataSet getCalculatedDataSet(int index){
		DataSet set = getSet(index);
		if (set instanceof CalculatedDataSet){
			return (CalculatedDataSet)set;
		}else{
			return null;
		}
	}
	
}
