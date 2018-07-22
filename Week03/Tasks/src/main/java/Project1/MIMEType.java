package Project1;

import java.util.*;

public class MIMEType {
	private static HashMap<Integer, String[]> mimeTypes = new HashMap<>();
	
	public static String[] insertType(Integer index, String extension, 
									  String type) {
		return mimeTypes.put(index, new String[]{ extension, type });
	}
	
	public static HashMap<Integer, String[]> getTypes() {
		return mimeTypes;
	}
	
	public static String retrieveType(Integer index, String extension) {
		return mimeTypes.get(index)[0];
	}
	
	public static String retrieveExtension(Integer index, String type) {
		return mimeTypes.get(index)[1];
	}
}
