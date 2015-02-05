package com.gorkemgok.tick4j.talib;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import net.objecthunter.exp4j.Expression;

import org.junit.Before;
import org.junit.Test;

import com.gorkemgok.tick4j.core.set.TickDataSet;
import com.gorkemgok.tick4j.listener.CSVTickListener;
import com.gorkemgok.tick4j.util.csv.CSVLoader;

public class Exp4JTALibFunctionTest {
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
		Expression expression = new TALibExpressionBuilder(set,"C>SMA(c,20)").build();
		set.begin();
		while (set.next()){
			expression.setVariable("C", set.getRow().getClose());
			double expressionResult = expression.evaluate();
			System.out.println(expressionResult);
			assertNotEquals(expressionResult, 0);
		}
		set.reset();
	}
	
	@Test
	public void test2() {
		double expressionResult = new TALibExpressionBuilder(set,"1 & 1").build().evaluate();
		assertEquals("Worng and logic",expressionResult, 1d,0);
		expressionResult = new TALibExpressionBuilder(set,"1 & 0").build().evaluate();
		assertEquals("Worng and logic",expressionResult, 0d,0);
		expressionResult = new TALibExpressionBuilder(set,"0 & 0").build().evaluate();
		assertEquals("Worng and logic",expressionResult, 0d,0);
		expressionResult = new TALibExpressionBuilder(set,"1 | 1").build().evaluate();
		assertEquals("Worng or logic",expressionResult, 1d,0);
		expressionResult = new TALibExpressionBuilder(set,"1 | 0").build().evaluate();
		assertEquals("Worng or logic",expressionResult, 1d,0);
		expressionResult = new TALibExpressionBuilder(set,"0 | 0").build().evaluate();
		assertEquals("Worng or logic",expressionResult, 0d,0);
	}
	
	@Test
	public void test3() {
		double expressionResult = new TALibExpressionBuilder(set,"1 < 1").build().evaluate();
		assertEquals("Worng < operator",expressionResult, 0d,0);
		expressionResult = new TALibExpressionBuilder(set,"0 < 1").build().evaluate();
		assertEquals("Worng < operator",expressionResult, 1d,0);
		
		expressionResult = new TALibExpressionBuilder(set,"1 > 1").build().evaluate();
		assertEquals("Worng > operator",expressionResult, 0d,0);
		expressionResult = new TALibExpressionBuilder(set,"1 > 0").build().evaluate();
		assertEquals("Worng > operator",expressionResult, 1d,0);

	}
	
	@Test
	public void test4() {
		double expressionResult = new TALibExpressionBuilder(set,"(1 > 0) & (1 < 2)").build().evaluate();
		assertEquals("Worng <,>, and combination",expressionResult, 1d,0);
		
		expressionResult = new TALibExpressionBuilder(set,"(1 > 4) & (1 < 2)").build().evaluate();
		assertEquals("Worng <,>, and combination",expressionResult, 0d,0);
		
		expressionResult = new TALibExpressionBuilder(set,"(1 > 4) | (1 < 2)").build().evaluate();
		assertEquals("Worng <,>, and combination",expressionResult, 1d,0);

	}

}
