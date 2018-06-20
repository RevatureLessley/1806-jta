package com.crypt;

import java.util.Dictionary;
import java.util.List;

public class ByteTable {	
	private byte[] data;
	private List<byte[]> recordList;
	private byte[][] record;
	private List<byte[][]> table;
	
	public void addData(byte[] newData) { recordList.add(newData); }
	public void commitRecord() { 
		record = new byte[recordList.size()][512];
		int i = 0;
		for(byte[] dataRecord : recordList) {
			record[i] = dataRecord;
			i++;
		}
		recordList.clear();
	}
	public void addRecordToTable() { table.add(record); }
}
