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
	
	public static String retrieveType(String extension) {
		
		for(String[] s : mimeTypes.values()) {
			
			if(extension.equals(s[1])) return s[0];
		}
		
		return null;
	}
	
	public static String retrieveExtension(String type) {
		
		for(String[] s : mimeTypes.values()) {
			
			if(type.equals(s[0])) return s[1];
		}
		
		return null;
	}
}
