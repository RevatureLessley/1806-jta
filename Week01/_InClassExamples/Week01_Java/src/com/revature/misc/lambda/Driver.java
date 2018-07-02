package com.revature.misc.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Driver {

	public static void main(String[] args) {
		PerformMath addition = (param1, param2) -> (param1 + param2);
		PerformMath subtraction = (a, b) -> (a - b);
		
		
		System.out.println(addition.perform(2, 5));
		System.out.println(subtraction.perform(2, 5));
		
		/*
		 * A lambda expression is an expression that makes use of '->' (Arrow notation)
		 * to define that reference at run time. In this case, we provided 
		 * implementation to the signature in the functional interface, 'Perform';
		 * 
		 * Lambda syntax goes as follows:
		 * 
		 * (Parameters to be used) -> (Expression to be executed)
		 * Java will determine how to sue them, and what gets returned for you.
		 * If yiou want, you can let Java also determine what datatypes should be used.
		 * 
		 * Note: If you only have one parameter, parenthesis on the left are 
		 * optional: e.g. a -> (a*a);
		 */
		
		PrintString ps = m -> System.out.println("Hello, " + m);
		ps.print("Bobbert");
		
		/*
		 * Method References
		 * -A method reference provides a way to call specific method
		 * on each element of a collection.
		 * A few syntax notes:
		 * (classname::methodofclass)
		 * (classname::new) <- in the case we want to provide a new instance for every
		 * 					spot in an array.
		 */
		System.out.println("=================");
		List<Integer> nums = Arrays.asList(5,2,1,7,8);
		for(Integer i: nums){
			System.out.println(i);
		}
		
		System.out.println("=====Using streams=====");
		
		nums.forEach(System.out::println); //ONLY ONE LINE! AAAAAND ITS FREE.
		
		System.out.println("=====SORTED LIST=====");
		System.out.println(nums.stream().sorted().collect(Collectors.toList()));
		
		/*
		 * We take our list: nums
		 * -We take it as a stream. This means we go through each element
		 * sequentially. Provides a way to perform aggregate functions on them.
		 * -We call sorted(). this method that applies to each element of the stream.
		 * -We cal collect(). This as the anme suggests, collects all of the altered
		 * elements of the list into one data structure.
		 * --Inside of it, we provide a way to combine our colelction, using the
		 * collectors utility class.
		 * --In this case, a list.
		 */
		
//		System.out.println(
//				nums.stream()
//				.sorted()
//				.filter(n -> n%2 == 0)
//				.collect(Collectors.toList()));
		System.out.println(
		nums.stream()
		.sorted()
		.filter(String -> String%2 == 0)
		.collect(Collectors.toList()));
		
		List<DietNpc> npcs = Arrays.asList(
				new DietNpc(1, "Bobbert", 99),
				new DietNpc(2, "Alice", 32),
				new DietNpc(3, "Little Tommy", 3),
				new DietNpc(4, "Tombert", 1),
				new DietNpc(6, "Ryan", 98)
				);
		System.out.println("======NPCS====");
		System.out.println(
				npcs.stream()
				.filter(levelHigherThan10())
				.sorted()
				.collect(Collectors.toList())
				);
		
		//Maps are used to take each element and change it to a result;
		System.out.println(
				nums.stream()
				.map(n -> n*n*n).collect(Collectors.toList()));
				
	}

	public static Predicate<DietNpc> levelHigherThan10(){
		return p -> p.getLvl() > 10;
	}
}
