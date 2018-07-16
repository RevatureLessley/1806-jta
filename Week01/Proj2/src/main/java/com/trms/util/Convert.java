package com.trms.util;

import java.sql.Blob;
import java.sql.SQLException;

public class Convert {
	public static int booleanToInteger(boolean input) {
		if(input) return 1;
		else return 0;
	}

	public static boolean integerToBoolean(int input) {
		if(input == 1) return true;
		else if(input == 0) return false;
		else { 
			System.out.println("INTEGER TO BOOLEAN CONVERSION FAILED. INPUT NOT 1 OR 0.");
			return false;
		}
	}
	
	public static byte[] blobToBytes(Object item) {
		try {
			return ((Blob)item).getBytes(1, (int)((Blob)item).length());
		} catch (SQLException e) {
			System.out.println("BLOB TO BYTES FAILED.");
			e.printStackTrace();
		}
		return null;
	}
	
	public static Blob bytesToBlob(byte[] bytes) {
		try {
			return new javax.sql.rowset.serial.SerialBlob(bytes);
		} catch (SQLException e) {
			System.out.println("BYTES TO BLOB FAILED");
			e.printStackTrace();
		}//    data Blob,
		return null;
	}
}
