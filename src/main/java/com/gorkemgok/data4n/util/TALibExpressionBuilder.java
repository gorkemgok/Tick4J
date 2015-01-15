package com.gorkemgok.data4n.util;

import com.gorkemgok.data4n.core.row.TickDataRow;
import com.gorkemgok.data4n.core.set.DataSet;
import com.gorkemgok.data4n.talib.Exp4jFunction;
import com.gorkemgok.data4n.talib.Exp4jPosFunction;
import com.gorkemgok.data4n.talib.Function;
import com.gorkemgok.data4n.talib.TALibFunctions;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TALibExpressionBuilder extends ExpressionBuilder {

	public TALibExpressionBuilder(DataSet set,String expression) {
		super(expression);
		this.variables("O","H","L","C","V");
		for (Function function : TALibFunctions.getFunctions()){
			if (function.getName().equals("CDLHARAMICROSS") || 1==1){
			this.function(new Exp4jFunction(set,function.getName(),function.getInputParameterCount()))
				.function(new Exp4jPosFunction(set,function.getName(),function.getInputParameterCount()));
			}
		}
	}
	
	public Expression build(){
		Expression expression = super.build();
        expression.setVariable("O", TickDataRow.OPEN)
				  .setVariable("H", TickDataRow.HIGH)
				  .setVariable("L", TickDataRow.LOW)
				  .setVariable("C", TickDataRow.CLOSE)
				  .setVariable("V", TickDataRow.VOLUME);
        return expression;
	}

}
