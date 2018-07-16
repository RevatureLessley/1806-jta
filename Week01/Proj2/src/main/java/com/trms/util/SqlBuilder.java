package com.trms.util;

public class SqlBuilder<K> {
	public String sAll(String tableName) { return "SELECT * FROM " + tableName; }

	public String sSome(String tableName, String ...columnNames) { 
		for(String column : columnNames) { column.concat(", ");	}
		return "SELECT " + columnNames + "FROM " + tableName;
	}

	public String sWhere(String tableName, String column, K keyValue) { 
		return sAll(tableName) + " WHERE " + column + "='" + keyValue.toString() + "'"; 
	}

	public String callInsert(String tableName, Integer numOfArgs) {
		return "{call insertInto" + tableName + "("
				+ argsArray(numOfArgs) + ")}";
	}	
	public String callUpdate(String tableName, K id, Integer numOfArgs) {
		return "{call update" + tableName + "(" + id.toString() + 
				", " + argsArray(numOfArgs) + ")}";
	}
	
	private String argsArray(Integer numOfArgs) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numOfArgs - 1; i++) {
			sb.append("?, ");
		}
		sb.append('?');
		System.out.println("SqlBuilder: " + sb.toString());
		return sb.toString();
	}
}
