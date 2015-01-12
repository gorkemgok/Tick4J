package com.gorkemgok.data4n.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

/**
 * Created by gorkemgok on 12/01/15.
 */
public class CallIndicator {
    public static void main(String[] args){
        Function gok = new Function("gok",1){

            @Override
            public double apply(double... doubles) {
                return Math.pow(doubles[0],2);
            }
        };
        double val = 2d;
        Expression expression = new ExpressionBuilder("gok(gok(C))").variables("C").function(gok).build().setVariable("C",2d);
        System.out.print(expression.evaluate());
    }
}
