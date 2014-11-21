package com.gorkemgok.data4n;

import java.text.ParseException;
import java.util.Date;

import com.gorkemgok.data4n.model.Model;

public class NetworkDataSet extends DataSet{
	private Model model;
	private int inputCount;
	private int outputCount;
	public NetworkDataSet(Model model){
		this.model = model;
		this.inputCount = model.getDataIn().length;
		this.outputCount = model.getDataOut().length;
	}
	public Model getModel() {
		return model;
	}
	public int getInputCount() {
		return inputCount;
	}
	public int getOutputCount() {
		return outputCount;
	}
	public double[][] getInputData(Date from, Date to){
		return this.getDoubleArray(from, to, 1, this.getInputCount());
	}
	public double[][] getOutputData(Date from, Date to){
		return this.getDoubleArray(from, to, 1+this.getInputCount(),this.getOutputCount());
	}
	public Date[] getTrainOutDateArray(){
		try {
			return this.getDateArray(model.getTrainFrom(),model.getTrainTo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;	
	}
	public Date[] getTestOutDateArray(){
		try {
			//Date d = model.getTestFrom()+1;
			return this.getDateArray(model.getTestFrom(),model.getTestTo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;	
	}
	public Date[] getDateArray(Date from, Date to){
		int rowCount = 0;
		for (DataRow r : this.getRowList()){
			Date rowDate = (Date)r.getData(0).get();
			if (!rowDate.before(from) && !rowDate.after(to) && !rowDate.equals(to)){
				rowCount++;
			}
		}
		Date[] result = new Date[rowCount];
		int ri = 0;
		for (DataRow r : this.getRowList()){
			Date rowDate = (Date)r.getData(0).get();
			if (!rowDate.before(from) && !rowDate.after(to) && !rowDate.equals(to)){
				result[ri] = (Date)r.getData(0).get();
				ri++;
			}
		}	
		return result;
	}
	public double[][] getDoubleArray(Date from, Date to,int startCol,int colCount){
		int rowCount = 0;
		for (DataRow r : this.getRowList()){
			Date rowDate = (Date)r.getData(0).get();
			if (!rowDate.before(from) && !rowDate.after(to) && !rowDate.equals(to)){
				rowCount++;
			}
		}
		double[][] result = new double[rowCount][colCount];
		int ri = 0;
		for (DataRow r : this.getRowList()){
			Date rowDate = (Date)r.getData(0).get();
			if (!rowDate.before(from) && !rowDate.after(to) && !rowDate.equals(to)){
				int ci = 0;
				for (int i=startCol;i<startCol+colCount;i++){
					result[ri][ci] = (Double)r.getData(i).get();
					ci++;
				}
				ri++;
			}
		}	
		return result;
	}
	public double[][] getTrainInputData(){
		try {
			return this.getInputData(model.getTrainFrom(),model.getTrainTo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public double[][] getTrainOutputData(){
		try {
			return this.getOutputData(model.getTrainFrom(),model.getTrainTo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public double[][] getTestInputData(){
		try {
			return this.getInputData(model.getTestFrom(),model.getTestTo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public double[][] getTestOutputData(){
		try {
			return this.getOutputData(model.getTestFrom(),model.getTestTo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
