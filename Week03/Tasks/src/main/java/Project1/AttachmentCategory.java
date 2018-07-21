package Project1;

import java.util.*;

public class AttachmentCategory {
	private static HashMap<Integer, String> attachmentCategories = 
			new HashMap<>();
	
	public static String insertType(Integer index, String category) {
		return attachmentCategories.put(index, category);
	}
	
	public static String retrieveType(Integer index) {
		return attachmentCategories.get(index);
	}
}
