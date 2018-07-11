package Project1;

import java.util.*;

public class ApprovalType {
	private static HashMap<Integer, String> approvalTypes = new HashMap<>();
	
	public static String insertType(Integer index, String type) {
		return approvalTypes.put(index, type);
	}
	
	public static String retrieveType(Integer index) {
		return approvalTypes.get(index);
	}
}