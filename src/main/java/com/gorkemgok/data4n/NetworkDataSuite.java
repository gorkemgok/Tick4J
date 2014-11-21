package com.gorkemgok.data4n;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gorkemgok.data4n.model.Model;
import com.gorkemgok.data4n.model.function.*;

import com.gorkemgok.data4n.model.function.Calculator;

public class NetworkDataSuite {
	private NetworkDataSet initialDataSet;
	private NetworkDataSet calculatedDataSet;
	private double[][] maxMin;
	
	public NetworkDataSuite(NetworkDataSet initialDataSet) {
		super();
		this.initialDataSet = initialDataSet;
		this.maxMin = NetworkDataSuite.getMaxMin(this.initialDataSet);
	}
	
	public void calculateNetworkDataSet() throws ParseException{
		NetworkDataSet rawDataSet = initialDataSet;
		if(initialDataSet.getModel().getNormalize()==Model.NORMALIZE_BEFORECALCULATION){
			rawDataSet = NetworkDataSuite.normalizeDataSet(initialDataSet);
		}
		NetworkDataSet calculatedDataSet = new NetworkDataSet(initialDataSet.getModel());
		for (DataRow r : rawDataSet.getRowList()){
			DataRow row = new DataRow();
			row.addData(r.getData(0));
			Date rDate = (Date)r.getData(0).get();
			if (!rDate.before(new SimpleDateFormat("dd/MM/yy").parse("01/01/10"))){
				System.currentTimeMillis();
			}
			for (int i=1;i<rawDataSet.getInputCount()+1;i++){
				@SuppressWarnings("rawtypes")
				Data d = r.getData(i);
				double v = (Double)d.get();
				double cv = Calculator.calculate(v, rawDataSet.getModel().getDataInFn()[i-1]);
				row.addData(new Data<Double>(cv));
			}
			for (int i=rawDataSet.getInputCount()+1;i<rawDataSet.getInputCount()+rawDataSet.getOutputCount()+1;i++){
				double v = (Double)r.getData(i).get();
				double cv = Calculator.calculate(v, rawDataSet.getModel().getDataInFn()[i-rawDataSet.getInputCount()]);
				row.addData(new Data<Double>(cv));
			}
			calculatedDataSet.add(row);
		}
		if(initialDataSet.getModel().getNormalize()==Model.NORMALIZE_AFTERCALCULATION){
			this.calculatedDataSet=NetworkDataSuite.normalizeDataSet(calculatedDataSet);
		}
		this.calculatedDataSet=calculatedDataSet;
	}
	
	public NetworkDataSet getInitialDataSet() {
		return initialDataSet;
	}

	public NetworkDataSet getCalculatedDataSet() {
		return calculatedDataSet;
	}

	public double[][] getMaxMin() {
		return maxMin;
	}
	public double normalizeValue(double value,int col){
		double min = this.maxMin[col][1];
		double max = this.maxMin[col][0];
		return new MinMaxNormalizer().calculate(value, "", max,min);
	}
	public double denormalizeOutputValue(double value){
		double min = this.maxMin[this.initialDataSet.getColCount()-2][1];
		double max = this.maxMin[this.initialDataSet.getColCount()-2][0];
		return new ReverseMinMaxNormalizer().calculate(value, "", max,min);
	}
	@SuppressWarnings("rawtypes")
	public static NetworkDataSet normalizeDataSet(NetworkDataSet rawSet){
		NetworkDataSet set = new NetworkDataSet(rawSet.getModel());
		try{
			double[][] maxMin = NetworkDataSuite.getMaxMin(rawSet);
			for (DataRow r : rawSet.getRowList()){
				Date rDate = (Date)r.getData(0).get();
				if (!rDate.before(new SimpleDateFormat("dd/MM/yy").parse("01/01/10"))){
					System.currentTimeMillis();
				}
				DataRow row = new DataRow();
				row.addData(r.getData(0));
				for (int i=1;i < r.getColCount();i++){
					Data data = r.getData(i);
					Data cdata = NetworkDataSuite.calculateData(data, new MinMaxNormalizer(), maxMin[i-1][0],maxMin[i-1][1]); 
					row.addData(cdata);
				}
				set.add(row);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return set;
	}
	@SuppressWarnings("rawtypes")
	public static Data calculateData(Data data, IFunction function,double... params){
		if (data.get() instanceof Double){
			return new Data<Double>(function.calculate((Double)data.get(), "", params));
		}else{
			return data;
		}
	}
	public static double[][] getMaxMin(NetworkDataSet set){
		double[][] result = new double[set.getInputCount()+set.getOutputCount()][2];
		try{
			int a = 0;
			for (DataRow r : set.getRowList()){
				Date rowDate = (Date)r.getData(0).get();
				if (!rowDate.before(set.getModel().getTrainFrom()) && !rowDate.after(set.getModel().getTestTo())){
					for (int i=1;i<set.getInputCount()+1;i++){
						double v = (Double)r.getData(i).get();
						if (a==0){
							result[i-1][0] = v;
							result[i-1][1] = v;
						}
						if (result[i-1][0] < v) result[i-1][0] = v;
						if (result[i-1][1] > v) result[i-1][1] = v;
					}
					for (int i=set.getInputCount()+1;i<set.getInputCount()+set.getOutputCount()+1;i++){
						double v = (Double)r.getData(i).get();
						if (a==0){
							result[i-1][0] = v;
							result[i-1][1] = v;
						}
						if (result[i-1][0] < v) result[i-1][0] = v;
						if (result[i-1][1] > v) result[i-1][1] = v;
						
					}
					a++;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
