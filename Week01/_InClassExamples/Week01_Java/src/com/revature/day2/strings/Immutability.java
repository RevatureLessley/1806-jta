package com.revature.day2.strings;

public class Immutability {

	/*
	 * The String class is one of the most important classes in Java.
	 * As it provides us a means to use human language throughout applications for our
	 * end users, in a convenient fashion.
	 * 
	 * String is essentially an array of characters.
	 * String is also a FINAL class. This means you may NOT inherit in order to make your own
	 * version.
	 * String is also IMMUTABLE
	 * -Once a string is set, it can NOT be changed ever again.
	 * --You would need to create a new String.
	 * --Immutable simply means unchangeable.
	 */
	public static void main(String[] args) {
		
		//Order of operations in most languages is from left to right.
		System.out.println(2 + 5 + 7); //14
		System.out.println(2 * 5 - 7); //3
		System.out.println(2 - 5 * 7); //3
		//Mathmatic operation order is also maintained
		//Parenthesis' first
		//Exponents
		//From left to right, multiplication and divisions
		//From left to right, addition and subtraction
		System.out.println(2 / 5 + 10 - 2 * 4); //2
		
		System.out.println("d" + 5 + 10);
		System.out.println(5 + 10 + "d");
		System.out.println(5 + 10 + "d" + 10  + 5);
		
		System.out.println("====Main exmaple at hand====");
		String s1 = "dog";
		System.out.println(System.identityHashCode(s1) + ":s1 = " + s1);
		
		s1 += "s";
		System.out.println(System.identityHashCode(s1) + ":s1 = " + s1);
		
		s1 = "dogs";
		System.out.println(System.identityHashCode(s1) + ":s1 = " + s1);
		
		String s2 = "dogs";
		System.out.println(System.identityHashCode(s2) + ":s2 = " + s2);
		
		String s3 = new String("dogs");
		System.out.println(System.identityHashCode(s3) + ":s3 = " + s3);
		System.out.println(System.identityHashCode(s3.intern()) + ":s3 = " + s3);
		
		String s4 = new String("dogs");
		s4 = s4.intern();
		System.out.println(System.identityHashCode(s4));
		System.out.println("====s5=====");
		String s5 = new String("cats");
		System.out.println(System.identityHashCode(s5));
		s5 = s5.intern();
		System.out.println(System.identityHashCode(s5));
		
		System.out.println(System.identityHashCode("cats"));
		
		
		//== vs .equals()
		/*
		 * == is a comparator. It will check to see if two resources are exactly the same.
		 * .equals() is a method inhertied by Object class where, in String, it has been
		 * overridden to check values with eachother as opposed to exact matches.
		 * --- NOTE: .equals, as it is in the Object class, behaves the same as ==. It
		 * --- will compare to see if two objects are exactly the same.
		 * --- This is why you need to override the .equals() in any class you make that you
		 * --- intend to compare.
		 */
		
		System.out.println("====comparing====");
		String person1 = "bob";
		String person2 = "bob";
		String person3 = new String("bob");
		System.out.println(person1.equals(person2)); //true
		System.out.println(person1.equals(person3)); //true, values are the smae
		System.out.println(person1 == person2); 	 //true
		System.out.println(person1 == person3); 	 //false;
		
		String person4 = "bo";
		person4 += "b";
		System.out.println(person1 == person4);		//false
		System.out.println(person1.equals(person4));//true
		
		System.out.println("====================");
		/*
		 * In situations where a string will go through numerous manipulations, one can reach
		 * a point where you are dealing with a huge overhead and memory consumption since 
		 * you are creating a new string every time.
		 * Therefore, in order to be efficient, and stop wasting memory, java offers a 
		 * mutable version of a String we can use:
		 * 	StringBuilder and StringBuffer
		 * 
		 * So what is the difference between String, StringBuffer and StringBuilder?
		 * -String is immutable
		 * -StringBuffer and StringBuilder wrap around the String class in order to make it
		 * 		mutable. Therefore, they are mutable. The difference between the two, however,
		 * 		is that StringBuffer is ThreadSafe, whereas StringBuilder is not.
		 * 			-NOTE: Thread safe simply means we only allow one thread to interact with
		 * 				a resource at any given time.
		 * 				This thread safety is extra overhead, and will typically make it a
		 *				slower candidate over StringBuilder.
		 * 			-NOTE: StringBuilder/Buffer is substantially more costly of an object then
		 * 				String, but we only need to create one.
		 */
		
		StringBuilder sb = new StringBuilder("bobbert");
		System.out.println(sb.toString() + ":" + System.identityHashCode(sb));
		sb.append("bobbsbehrksdij");
		System.out.println(sb.toString() + ":" + System.identityHashCode(sb));
		
		System.out.println("=====PERFORMANCE TEST=====");
		String str = "";
		StringBuilder sbui = new StringBuilder("");
		StringBuffer sbuf = new StringBuffer("");
		int iterations = 100000;
		
		long curtime;
		
		curtime = System.currentTimeMillis();
		for(int i = 0; i < iterations; i++){
			str += "s";
		}
		System.out.println("STRING: " + (System.currentTimeMillis() - curtime));
		
		curtime = System.currentTimeMillis();
		for(int i = 0; i < iterations; i++){
			sbui.append("s");
		}
		System.out.println("STRINGBUILDER: " + (System.currentTimeMillis() - curtime));
		
		curtime = System.currentTimeMillis();
		for(int i = 0; i < iterations; i++){
			sbuf.append("s");
		}
		System.out.println("STRINGBUFFER: " + (System.currentTimeMillis() - curtime));
		
		
		
		
	}

}
