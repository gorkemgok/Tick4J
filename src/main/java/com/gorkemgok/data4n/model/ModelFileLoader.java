package com.gorkemgok.data4n.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.gorkemgok.data4n.util.Console;

public class ModelFileLoader {
	private ArrayList<Model> models = new ArrayList<Model>();
	public ModelFileLoader(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		String modelPattern = "";
        while ((line = reader.readLine()) != null) {
	        String[] lineSplit = line.split(" ");
	        if (!line.isEmpty()){
		        if (lineSplit.length>1 && lineSplit[0].equals("model") && lineSplit[1].equals("end")){
		        	if(!modelPattern.isEmpty()){
		        		Model modelToBeAdded = null;
		        		try{
		        			modelToBeAdded = new ModelInterpreter(modelPattern).interprete();
		        		}catch(Exception ex){
		        			Console.appendMust("Model pattern hata:"+ex.getMessage());
		        		}
		        		models.add(modelToBeAdded);
		        		modelPattern = "";
		        	}
		        }
		        modelPattern += line.trim()+" ";
	        }
        }
        reader.close();
	}
	
	public Model getModel(int index){
		return models.get(index);
	}

	public ArrayList<Model> getModels() {
		return models;
	}
	
}
