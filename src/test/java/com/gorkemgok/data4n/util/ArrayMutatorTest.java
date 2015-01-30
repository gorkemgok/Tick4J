package com.gorkemgok.data4n.util;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ArrayMutatorTest {

	@Test
	public void test() {
		Integer[] array = {1,2,3,4,5};
		Random random = new Random();
		ArrayMutator<Integer> mutator = new ArrayMutator<Integer>(); 
		for (int i=0;i<100;i++){
			double percentage = random.nextDouble();
			int result = mutator.mutate(array, percentage);
			System.out.println(percentage+", "+result);
		}
	}

}
