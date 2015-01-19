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
			this.function(new Exp4jFunction(set,function.getName(),function.getInputParameterCount()))
				.function(new Exp4jPosFunction(set,function.getName().toLowerCase(),function.getInputParameterCount()));
		}
	}
	
	public Expression build(){
		Expression expression = super.build();
        expression.setVariable("o", TickDataRow.OPEN)
				  .setVariable("h", TickDataRow.HIGH)
				  .setVariable("l", TickDataRow.LOW)
				  .setVariable("c", TickDataRow.CLOSE)
				  .setVariable("v", TickDataRow.VOLUME);
        return expression;
	}

}
