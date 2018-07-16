package com.trms.util;

import java.lang.reflect.Field;
import java.util.function.Function;

public class FunctionBuilder {
	Object o;
	//Retrieves fields from a given class
	private Field[] getFields(Object o) {
		return o.getClass().getDeclaredFields();
	}
	
	private static Class<? extends Object> getClass(Object o) { return o.getClass(); }

	public static Function<Object[], ?> getORBehaviour(Object type) {
		return null;
	}
	
	
}
