package Project1;

import java.util.*;

public class EventType {
	private static HashMap<Integer, Pair> eventTypes = new HashMap<>();
	
	public static class Pair {
		String type;
		double coverage;

		public Pair(String type, double coverage) {
			this.type = type;
			this.coverage = coverage;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public double getCoverage() {
			return coverage;
		}

		public void setCoverage(double coverage) {
			this.coverage = coverage;
		}
	}
	
	public static Pair insertType(Integer index, String type, double coverage) {
		return eventTypes.put(index, new Pair(type, coverage));
	}
	
	public static Pair retrieveType(Integer index) {
		return eventTypes.get(index);
	}
}