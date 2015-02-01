package com.gorkemgok.tick4j.util;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

@SuppressWarnings("unchecked")
public class Console {
	public static final int APPENDER_CONSOLE = 0;
	public static final int APPENDER_JLIST = 1;
	public static final int APPENDER_TEXTAREA = 2;
	public static final int LEVEL_MUST = 0;
	public static final int LEVEL_INFORMATION = 1;
	public static final int LEVEL_DETAIL = 2;
	public static final int LEVEL_DEBUG = 3;
	@SuppressWarnings("rawtypes")
	private static DefaultListModel model = null;
	private static JTextArea ta = null;
	private static int level = 1;
	private static int appender = 0;
	public static boolean ln = true;

	public static void append(String s, int l){
		if (Console.level >= l){
			if(Console.appender == Console.APPENDER_TEXTAREA){
				ta.append(s+"\n");
				final int length = ta.getText().length();
				ta.setCaretPosition(length);
			}else if (Console.appender == Console.APPENDER_CONSOLE){
				if (ln)	System.out.println(s);
				else {
					System.out.print(s);
					ln = true;
				}
			}else if(Console.appender == Console.APPENDER_JLIST && model!=null){
				model.addElement(s);
			}
		}
	}
	public static void appendMust(String s){
		Console.append(s, LEVEL_MUST);
	}
	public static void appendInformation(String s){
		Console.append(s, LEVEL_INFORMATION);
	}
	public static void appendDetail(String s){
		Console.append(s, LEVEL_DETAIL);
	}
	public static void appendDebug(String s){
		Console.append(s, LEVEL_DEBUG);
	}
	public static void appendError(Exception ex){
		Console.appendError(ex.getMessage());
	}
	public static void appendError(String ex){
		Console.append("ERROR : "+ex, LEVEL_MUST);
	}
	public static void switchLevel(int level){
		Console.level = level;
	}
	public static void switchListModel(@SuppressWarnings("rawtypes") DefaultListModel model){
		Console.model = model;
	}
	public static void switchTextArea(JTextArea ta){
		Console.ta = ta;
	}
	public static void switchAppender(int appender){
		Console.appender = appender;
	}
	public static void toggleAppender(){
		if (appender==APPENDER_CONSOLE) appender = APPENDER_JLIST;
		else if (appender==APPENDER_JLIST) appender = APPENDER_CONSOLE;
	}
}
