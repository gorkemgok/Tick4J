package com.gorkemgok.tick4j.util;

public class ArrayMutator<T> {
	public T mutate(T[] tArray, double percentage){
		int itemCount = tArray.length;
		double percentagePerItem = 1d/itemCount;
		Double itemIndexPercentage = percentage / percentagePerItem;
		int itemIndex = itemIndexPercentage.intValue();
		return tArray[itemIndex];
	}
}
