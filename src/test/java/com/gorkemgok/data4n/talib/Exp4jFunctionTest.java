package com.gorkemgok.data4n.talib;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import net.objecthunter.exp4j.Expression;

import org.junit.Before;
import org.junit.Test;

import com.gorkemgok.data4n.core.set.TickDataSet;
import com.gorkemgok.data4n.listener.CSVTickListener;
import com.gorkemgok.data4n.util.TALibExpressionBuilder;
import com.gorkemgok.data4n.util.csv.CSVLoader;

public class Exp4jFunctionTest {
	TickDataSet set;
	@Before
	public void initialize() throws IOException, ParseException{
		set = new TickDataSet("VOBO30","5DK");
		CSVLoader loader = new CSVLoader("resources/vob30_5dk_100.csv","DATE>MM/dd/yy kk:mm:SSS,HOUR,OPEN,HIGH,LOW,CLOSE,VOLUME");
        loader.addListener(new CSVTickListener(set));
        loader.load();
        set.reset();
	}
	@Test
	public void test() {
		Expression expression = new TALibExpressionBuilder(set,"BBANDS(c,20,2,2,0,0)-BBANDS(c,20,2,2,0,1)").build();
		set.begin();
		while (set.next()){
			double expressionResult = expression.evaluate();
			System.out.println(expressionResult);
			assertNotEquals(expressionResult, 0);
		}
		set.reset();
	}

}
