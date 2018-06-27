package com.revature.day4.maps;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Driver {

	public static void main(String[] args) {
		/*
		 * Maps are a collection of key/value pairs.
		 * More specifically, a combination of a keyset and entry values.
		 * It is worth noting that m aps are NOT a part of the Collection API.
		 */
		
		Map<Integer, String> map = new HashMap<>();
		//At which point will this code fail
		map.put(1, "Dora");
		map.put(2, "Boots");
		map.put(3, "Map");
		map.put(4, "Diego");
		map.put(2, "Swiper");
		map.put(0, null);
		map.put(null, null);
		map.put(null, "zero");
		
		for(Integer i : map.keySet()){
			System.out.println(i + ": " + map.get(i));
		}
		
		System.out.println("=============");
		Hashtable<Integer,String> ht = new Hashtable<>();
		ht.put(1, "Dora");
		ht.put(2, "Boots");
		ht.put(3, "Map");
		ht.put(4, "Diego");
		ht.put(2, "Swiper");
		//ht.put(0, null);	   //Null pointer EXception!
		//ht.put(null, null);  //Null pointer Exception!
		//ht.put(null, "zero");  //Null Pointer exception!
		
		
		System.out.println("END OF EXECUTION");
		
		/*
		 * Hashtable vs HashMap
		 * -The key difference between HashTables and HashMaps is that Hashtables do not
		 * accept null keys OR values.
		 * There are more differences:
		 * -Hashmaps are not synchronized, Hashtables are.
		 * -Hashtables are considered a legacy class.
		 * 		-Legacy classes are classes that are form older version of java that have
		 * 			proper replacements (IE HashMap in this case) but they can still have use
		 * 			and hterefore are not a candidate for deprecation. (In this case, HashTable
		 * 			is threadsafe.
		 * 
		 */
	}

}
