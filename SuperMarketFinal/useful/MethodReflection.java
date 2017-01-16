package main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import superclasses.Entity;

public class MethodReflection {
	final protected static int MODE_INSERT = 0;
	final protected static int MODE_UPDATE = 1;
	
	private String removeComma(String str) {
		return str.substring(0, str.length()-2);
	}
	
	private String makeBaseSQL(Object obj, int mode, String table, String[][] params) {
		String sql = "", fieldsPart = "";
		Method method;
		
		if (mode == MODE_INSERT) {
			sql = "INSERT INTO " + table;
			fieldsPart = " (";
			String valuesPart = "VALUES (";
			
			for (String[] field : params) {
				fieldsPart += field[0] + ", ";
				
				try {
					method = (obj.getClass()).getDeclaredMethod(field[2]);
					valuesPart += String.format(field[1], method.invoke(obj)) + ", ";
				} 
				catch (NoSuchMethodException | SecurityException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();} 
				catch (IllegalArgumentException e) {e.printStackTrace();} 
				catch (InvocationTargetException e) {e.printStackTrace();}
			}
			
			fieldsPart = removeComma(fieldsPart) + ") ";
			valuesPart = removeComma(valuesPart) + ")";
			
			sql += fieldsPart + valuesPart;
		} else {
			sql = "UPDATE " + table;
			fieldsPart = " SET ";
			
			for (String[] field : params) {
				try {
					method = ((Class<?>) obj.getClass()).getDeclaredMethod(field[2]);
					fieldsPart += field[0] + "=" + String.format(field[1], method.invoke(obj)) + ", ";
				} 
				catch (NoSuchMethodException | SecurityException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();} 
				catch (IllegalArgumentException e) {e.printStackTrace();} 
				catch (InvocationTargetException e) {e.printStackTrace();}
			}
			
			fieldsPart = removeComma(fieldsPart);
			sql += fieldsPart;
		}
		
		return sql;
	}
	
	protected String makeSQL(Object obj, int mode, String table, String[][] params) {
		String sql = makeBaseSQL(obj, mode, table, params);
		
		if (mode == MODE_UPDATE)
			sql += " WHERE id=" + ((Entity) obj).getId();
		
		return sql;
	}
	
	protected String makeSQL(Object obj, int mode, String table, String[][] params, String wherePart) {
		String sql = makeBaseSQL(obj, mode, table, params);
		
		if (mode == MODE_UPDATE)
			sql += " WHERE " + wherePart;
		
		return sql;
	}

}
