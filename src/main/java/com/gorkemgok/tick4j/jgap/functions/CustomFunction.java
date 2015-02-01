package com.gorkemgok.tick4j.jgap.functions;

/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */

import java.awt.*;

import com.gorkemgok.tick4j.util.ArrayMutator;
import org.apache.commons.lang.ArrayUtils;
import org.jgap.*;
import org.jgap.gp.*;
import org.jgap.gp.impl.*;
import org.jgap.util.*;

/**
 * Specifies a color, represented by R, G, B and Alpha.
 *
 * @author Yann N. Dauphin
 * @since 3.4
 */
@SuppressWarnings("rawtypes")
public class CustomFunction extends CommandGene implements IMutateable, ICloneable{

	private static final long serialVersionUID = -7585162580578642995L;

	/** String containing the CVS revision. Read out via reflection!*/
	@SuppressWarnings("unused")
	private final static String CVS_REVISION = "$Revision: 1.4 $";

	private final Class[] childTypes;
	private final String functionName;
	private final int childCount;
	private boolean isOperator = true;
	
	private final static String[] MA_TYPE_FUNCTIONS = {"SMA","WMA","DEMA","TEMA","MIN","MAX","KAMA"};
	private final static String[] RSI_TYPE_FUNCTIONS = {"RSI"};
	private final static String[] COMP_OPERATORS = {"<",">","=",">=","<="};
	private final static String[] LOGIC_OPERATORS = {"&","|"};


	public CustomFunction(boolean isOperator,GPConfiguration a_conf, String functionName ,Class returnType, int childCount, Class... childTypes) throws InvalidConfigurationException {
		super(a_conf, childCount, returnType);
		this.childTypes = childTypes;
		this.functionName = functionName;
		this.childCount = childCount;
		this.isOperator = isOperator;
	}
	public CustomFunction(GPConfiguration a_conf, String functionName ,Class returnType, int childCount, Class... childTypes) throws InvalidConfigurationException {
		this(false, a_conf,functionName, returnType, childCount, childTypes);
	}	
	public CustomFunction(GPConfiguration a_conf, String functionName ,Class returnType, Class childType, int childCount) throws InvalidConfigurationException {
		this(false, a_conf,functionName, returnType, childCount, new Class[]{childType});
	}
	public CustomFunction(GPConfiguration a_conf, String functionName ,Class returnType, Class childType) throws InvalidConfigurationException {
		this(true, a_conf,functionName, returnType, 2, new Class[]{childType});
	}

	@Override
	public Object execute_object(ProgramChromosome a_chrom, int a_n,Object[] a_args) {
		float r = a_chrom.execute_float(a_n, 0, a_args);
		float g = a_chrom.execute_float(a_n, 1, a_args);
		float b = a_chrom.execute_float(a_n, 2, a_args);
		float a = a_chrom.execute_float(a_n, 3, a_args);
		return new Color(r, g, b, a);
	}

	@Override
	public Class getChildType(IGPProgram a_ind, int a_chromNum) {
		if (a_chromNum < childTypes.length){
			return childTypes[a_chromNum];
		}
		return childTypes[0];
	}

	@Override
	public String toString() {
		String fn = isOperator||functionName.isEmpty()?"":"(";
		for (int i = 1;i<=childCount;i++){
			fn += i==1?"&"+i:(isOperator?functionName:",")+"&"+i;
		}
		fn += fn = isOperator||functionName.isEmpty()?"":")";
		return (isOperator?"":functionName)+fn;
	}

	/**
	 * Clones the object. Simple and straight forward implementation here.
	 *
	 * @return cloned instance of this object
	 *
	 * @author Klaus Meffert
	 * @since 3.4.1
	 */
	public Object clone() {
		try {
			CustomFunction result = new CustomFunction(isOperator,getGPConfiguration(),functionName,this.getReturnType(),childCount,childTypes);
			return result;
		} catch (Throwable t) {
			throw new CloneException(t);
		}

	}
	public CommandGene applyMutation(int a_index, double a_percentage) throws InvalidConfigurationException {
		String mutantName = functionName;
		ArrayMutator<String> mutator = new ArrayMutator<String>();
		if (ArrayUtils.contains(MA_TYPE_FUNCTIONS, functionName)){
			mutantName = mutator.mutate(MA_TYPE_FUNCTIONS,a_percentage);
		}else if (ArrayUtils.contains(RSI_TYPE_FUNCTIONS, functionName)){
			mutantName = mutator.mutate(RSI_TYPE_FUNCTIONS,a_percentage);
		}else if (ArrayUtils.contains(COMP_OPERATORS, functionName)){
			mutantName = mutator.mutate(COMP_OPERATORS,a_percentage);
		}else if (ArrayUtils.contains(LOGIC_OPERATORS, functionName)){
			mutantName = mutator.mutate(LOGIC_OPERATORS,a_percentage);
		}
		CustomFunction mutant = new CustomFunction(isOperator,getGPConfiguration(),mutantName,this.getReturnType(),childCount,childTypes);
		return mutant;
	}

}
