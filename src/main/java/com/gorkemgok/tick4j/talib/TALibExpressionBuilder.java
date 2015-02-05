package com.gorkemgok.tick4j.talib;

import com.gorkemgok.tick4j.core.row.TickDataRow;
import com.gorkemgok.tick4j.core.set.DataSet;
import com.gorkemgok.tick4j.exp4j.Exp4jFunction;
import com.gorkemgok.tick4j.exp4j.Exp4jPosFunction;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

public class TALibExpressionBuilder extends ExpressionBuilder {

	public TALibExpressionBuilder(DataSet set,String expression) {
		super(expression);
		this.variables("P","o","h","l","c","v","O","H","L","C","V");
		for (TALibFunction TALibFunction : TALibFunctions.getTALibFunctions()){
			this.function(new Exp4jFunction(set, TALibFunction.getName(), TALibFunction.getInputParameterCount()))
				.function(new Exp4jPosFunction(set, TALibFunction.getName().toLowerCase(), TALibFunction.getInputParameterCount()));
		}
		Operator gteq = new Operator(">=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... values) {
				if (values[0] >= values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		Operator leeq = new Operator("<=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... values) {
				if (values[0] <= values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		Operator eq = new Operator("=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... values) {
				if (values[0] == values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		Operator gt = new Operator(">", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... values) {
				if (values[0] > values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		Operator le = new Operator("<", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... values) {
				if (values[0] < values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		
		Operator and = new Operator("&", 2, true, Operator.PRECEDENCE_ADDITION - 2) {
			@Override
			public double apply(double... values) {
				if (values[0]==1 && values[1]==1) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		
		Operator or = new Operator("|", 2, true, Operator.PRECEDENCE_ADDITION - 2) {
			@Override
			public double apply(double... values) {
				if (values[0]==1 || values[1]==1) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};
		this.operator(gteq).operator(leeq).operator(gt).operator(le).operator(eq).operator(and).operator(or);
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
