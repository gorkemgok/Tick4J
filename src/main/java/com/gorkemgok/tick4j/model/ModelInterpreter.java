package com.gorkemgok.tick4j.model;


public class ModelInterpreter {
	private static final int SEGMENT_MODEL = 0;
	private static final int SEGMENT_DATA = 1;
	private static final int SEGMENT_TRAIN = 2;
	private static final int SEGMENT_TEST= 3;
	private Model model = new Model();
	private String dataPattern = "model name:StandartModel data:vob,usd" +
								 "data step:1 timeunit:day normalize:bc in:1,4,/_1000>sqrt-2,4-1,9-2,9 out:3,4" +
			 					 "train from:01/01/2013.00:00 to:01/09/2013.00:00  " +
			 					 "test from:01/09/2013.00:00 to:01/01/2014.00:00";
	private String[] currentFunctions = {};
	private int maxDataRow = 0;
	public ModelInterpreter(String dataPattern) {
		super();
		this.dataPattern = dataPattern;
	}
	public Model interprete(){
		int segment = -1;
		String[] split1 = dataPattern.split(" ");
		for (int i=0;i<split1.length;i++){
			if (split1[i].equals("model")){
				segment = SEGMENT_MODEL;
			}else if (split1[i].equals("data")){
				segment = SEGMENT_DATA;
			}else if(split1[i].equals("train")){
				segment = SEGMENT_TRAIN;
			}else if(split1[i].equals("test")){
				segment = SEGMENT_TEST;
			}else{
				String[] splitd = split1[i].split(":");
				if (splitd[0].equals("timeunit")){
					if (splitd[1].equals("minute")) model.setTimeUnit(Model.TIMEUNIT_MINUTE);
					else if (splitd[1].equals("hout")) model.setTimeUnit(Model.TIMEUNIT_HOUR);
					else if (splitd[1].equals("day")) model.setTimeUnit(Model.TIMEUNIT_DAY);
				}else if (splitd[0].equals("name")){
					model.setName(splitd[1]);
				}else if (splitd[0].equals("normalize")){
					if (splitd[1].equals("bc")) model.setNormalize(Model.NORMALIZE_BEFORECALCULATION);
					else if (splitd[1].equals("ac")) model.setNormalize(Model.NORMALIZE_AFTERCALCULATION);
					else if (splitd[1].equals("none")) model.setNormalize(Model.NORMALIZE_NONE);
				}else if (splitd[0].equals("step")){
					model.setDataStep(Integer.parseInt(splitd[1]));
				}else if (splitd[0].equals("set")){
					model.setSet(splitd[1].split(","));
				}else if (splitd[0].equals("from")){
					switch (segment){
						case SEGMENT_TRAIN:
							model.setTrainFrom(splitd[1]);
						case SEGMENT_TEST:
							model.setTestFrom(splitd[1]);
						default:
					}
				}else if (splitd[0].equals("to")){
					switch (segment){
						case SEGMENT_TRAIN:
							model.setTrainTo(splitd[1]);
						case SEGMENT_TEST:
							model.setTestTo(splitd[1]);
						default:
					}
				}else if (splitd[0].equals("in")){
					int[][] result = interpreteCoordinateArray(splitd[1]);
					model.setDataIn(result);
					model.setDataInFn(currentFunctions);
					model.setMaxDataRowNumber(maxDataRowNumber(result));
				}else if (splitd[0].equals("out")){
					int[][] result = interpreteCoordinateArray(splitd[1]);
					model.setDataOut(result);
					model.setDataOutFn(currentFunctions);
					model.setMaxDataRowNumber(maxDataRowNumber(result));
				}
			}
		}
		return model;
		
	}
	private int[][] interpreteCoordinateArray(String coordinates){
		String[] splitn =  coordinates.split("-");
		currentFunctions = new String[splitn.length];
		int[][] result = new int[splitn.length][2];
		for (int j=0;j<splitn.length;j++){
			String[] splitv = splitn[j].split(",");
			result[j][0] = Integer.parseInt(splitv[0]);
			result[j][1] = Integer.parseInt(splitv[1]);
			if (splitv.length>2) currentFunctions[j] = splitv[2];
			else currentFunctions[j] = "";
		}
		return result;
	}
	
	private int maxDataRowNumber(int[][] coor){
		for (int[] c : coor){
			if (maxDataRow<c[0]) maxDataRow=c[0];
		}
		return maxDataRow;
	}

	public Model getModel(){
		return model;
	}
}
