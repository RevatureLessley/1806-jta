package com.crypt;

import java.util.Dictionary;
import java.util.List;

public class ByteArray {
	private byte[][][] data;
	private byte[] fileOutput;
	private byte[] currentDataItem;
	private List<byte[]> currentDataset;
	private List<List<List<byte[]>>> dataset;
	private Object current;
	
	ByteArray(){ data = new byte[8][8][64];	}
	public Object getCurrent() { return current; }
	public void setCurrent(Object current) {
		this.current = current;
		currentToBytes();
	}
	public void currentToBytes() { currentDataItem = current.toString().getBytes();	}
	public void addNewDataset(List<byte[]> newdata) { dataset.add(newdata); }
	public void addCurrentDataset() { dataset.add(currentDataset); }
	public void addCurrentData() { currentDataset.add(currentDataItem);	}
}
