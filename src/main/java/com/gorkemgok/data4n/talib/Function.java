package com.gorkemgok.data4n.talib;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.tictactec.ta.lib.meta.annotation.InputParameterType;

public class Function {
	private String name;
	private int inputCount;
	private int optInputCount;
	private int outputCount;
	private Method talibMethod;
	
	private ArrayList<Param> inputs = new ArrayList<Param>();
	private ArrayList<Param> optInputs = new ArrayList<Param>();
	private ArrayList<Param> outputs = new ArrayList<Param>();
	
	
	public Function(Method talibMethod,String name, int inputCount, int optInputCount, int outputCount) {
		super();
		this.talibMethod = talibMethod;
		this.name = name;
		this.inputCount = inputCount;
		this.optInputCount = optInputCount;
		this.outputCount = outputCount;
	}
	
	public Method getTalibMethod() {
		return talibMethod;
	}

	public void addInput(Param param){
		inputs.add(param);
	}
	
	public void addOptInput(Param param){
		optInputs.add(param);
	}
	
	public void addOutput(Param param){
		outputs.add(param);
	}
	
	public Param[] getInputs(){
		return inputs.toArray(new Param[inputs.size()]);
	}
	
	public Param[] getOptInputs(){
		return optInputs.toArray(new Param[optInputs.size()]);
	}
	
	public Param[] getOutputs(){
		return outputs.toArray(new Param[outputs.size()]);
	}
	
	public int getInputParameterCount(){
		int c = 0;
		if (outputs.size()>1) c++;
		for (Param p : inputs){
			if (!p.getType().equals(InputParameterType.TA_Input_Price.toString())) c++;
		}
		return optInputCount+c;
	}
	public String getName() {
		return name;
	}

	public String toString(){
		String r = name+"("+inputCount+","+optInputCount+","+outputCount+") \n";
		for (Param p : inputs){
			r += "\t"+p.getName()+"="+p.getType()+"\n";
		}
		for (Param p : optInputs){
			r += "\t"+p.getName()+"="+p.getType()+"\n";
		}
		for (Param p : outputs){
			r += "\t"+p.getName()+"="+p.getType()+"\n";
		}
		return r;
	}
	
	public static class Param{
		private String name;
		private String type;
		public Param(String name, String type) {
			super();
			this.name = name;
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isSame(Param param){
			if (this.name.equals(param.getName()) && this.type.equals(param.getType())) return true;
			return false;
		}
		
	}
}
