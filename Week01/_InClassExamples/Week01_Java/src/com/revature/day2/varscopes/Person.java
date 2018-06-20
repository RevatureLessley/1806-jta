package com.revature.day2.varscopes;

//DESIGN PATTERN
/*
 * A design pattern is a tried and true approach/method for achieving a desired
 * functionality or structure in programming. ARguably considered the 'best' way to 
 * go about certain tasks.
 */

//POJO
/*
 * Plain ol' Java Object
 * A POJO is a design pattern that aims to simply create a class that serves as a container of data.
 * Ideally aims to only store states(fields) and have getters and setters for chaning them.
 */
public class Person {
	//Typical field/method signature/Datatype field/method name;
	//accessModifier (0 - many) returnType/
	public static int personCount = 0;
	//STATIC SCOPE
	/*
	 * Static makes a variable/method available as soon as the application is started. There is no need
	 * to create an instance of the class in order to access anything static.
	 * Static variables and fields are the first things to be created in any java application. 
	 * There can only be ONE instance of anything static.
	 */
	
	
	//Fields at this point of a class are called INSTANCE variables
	//Ergo, this is the INSTANCE scope;
	//Variables here can be accessed from anywhere within THIS class.
	private String name;
	private int age;
	
	//This is a constructor in Java.
	//Upon invoking 'new' keyword on this class, we create an instance of it (IE an object) which created
	//by first calling the following method.
	//Constructors must match the class name in casing, as well as have NO return type.
	public Person(String name, int age){
		this.name = name;
		this.age = age;
		personCount++; //++ increments by 1
		//personCount = personCount + 1
	}
	

	
	/*
	 * CONSTRUCTORS
	 * Default Constructor
	 * -A default constructor is a constructor with
	 * NO arguments. The default constructor is only
	 * provided automatically in a situation where you have no
	 * constructors at all.
	 * -The exact moment you write ANY constructor at all, the JVM no longer
	 * provides a default constructor. This is why you can get away with
	 * Obj o = new Obj() without error if Obj doesnt have a constructor defined.
	 * But it FAILS if you have a constructor with a parameter.
	 * 
	 * NO-ARGS CONSTRUCTOR
	 * -Simply a constructor that takes no arguments.
	 */
	
	
	public void setName(String name){
		//Inside any methods belongs the METHOD scope.
		//Any variables or obejcts created within this scope will exist ONLY within the method.
		//Once the method ends, anything created within is collected for memory.
		//That being said, variables within can only be accessed by other actions within the method.
		this.name = name;
		
		/*
		 * Should you reach a point where you have duplicate variable names across multiple scopes,
		 * you will run into an issue where the compiler does not know which one you are referencing by name.
		 * So it will usually go with the more restricted scope version.
		 * However we can use the 'this' keyword to refer to the instance scope of the class.
		 * 'this' is sort of like calling Person.name within the class itself.
		 * 'this' references the class itself.
		 */
	}
	public String getName(){
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void someMethod(){
		//for loop will loop as many times until a condition is broken.
		//In this case:
		//for(incrementor; condition for looping; increment/decrement)
		for(int i = 0; i < 5; i++){
			//Inside the for loop is consider LOOP scope (or Local)
			//Variable i cannot be accessed anywhere but inside the for loop declaration.
			System.out.println(i);
		}

	}
	
	//Summary: scopes
	/*
	 * STATIC
	 * INSTANCE (Global)
	 * METHOD
	 * LOOP (Local)
	 */
}
