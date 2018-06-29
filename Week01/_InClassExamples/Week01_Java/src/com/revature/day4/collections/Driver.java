package com.revature.day4.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;

public class Driver {

	public static void main(String[] args) {
		/*
		 * collection: A term for data structures
		 * Collection: The API for java collections
		 * Collections: Utility for interacting with collections
		 */
		
		//Note: Collections only deal with objects! Therefore primitive values are not
		//allowed. But, they will still work because of autoboxing.
		
		System.out.println("=====LISTS=====");
		
		ArrayList l1 = new ArrayList();
		l1.add("Bobbert");
		l1.add(12);
		l1.add(false);
		System.out.println(l1);
		
		/*
		 * Generics
		 * -enforce a type for a collection
		 * 		-This type, could be used as a variable, enforcing dynamically
		 * 			typed lists.
		 * 		-In addition to making a strong collection, you can also use 
		 * 			generics to dynamically type a colleciton at runtime.
		 * 				(Parametric polymorphism)
		 */
		
		//At what line number will this app crash
		//Or will it crash at all?
		List<Integer> ints = new ArrayList<>();
		ints.add(15); //Add new element
		ints.add(new Integer(20));
		ints.add(5);
		System.out.println(ints);
		ints.remove(new Integer(15)); //removes element
		System.out.println(ints.remove("ryan"));
		System.out.println(ints);
		
		System.out.println(ints.get(1)); //Retrieve an element at an index.
		/*
		 * Methods of LIst;
		 * -add
		 * -remove
		 * -get
		 * -set //Sets a specific index to a different number.
		 * -listIterator //Returns a LIstIterator Object which and iterate back AND
		 * 		forth.
		 */
		
		/*
		 * Since all collections in Collection subclasses Iterable, we can use a 
		 * foreach loop to iterate through it.
		 */
		System.out.println("======");
		for(int i: ints){
			System.out.println(i*i);
		}
		
		/*
		 * Iterable offers us iterators, which are interactable cursors for a collection
		 */
		
		System.out.println("======");
		Iterator it1 = ints.iterator();
		System.out.println(it1.next());
		System.out.println(it1.next());
		//it1.next(); no such element exception.
		//To safely iterate through, make sure you use the hasNext() method in
		//conjunction with a while loop.
		//How do I put the iterator back to element 0?
		it1 = ints.iterator();
		System.out.println(it1.next());
		
		System.out.println("====ListIterator====");
		//List offers us, specifically, a special iterator. The ListIterator
		ListIterator li = ints.listIterator();
		System.out.println(li.next());
		System.out.println(li.next());
		System.out.println(li.previous());
		System.out.println(li.previousIndex()); //Retrive the next index called via previous()
		
		//LinkedLists
		List ll = new LinkedList();
		Queue ll2 = new LinkedList();
		
		LinkedList ll3 = new LinkedList();
		
		
		System.out.println("=====SETS=====");
		Set<Integer> set = new HashSet();
		set.add(5);
		set.add(10);
		set.add(4);
		set.add(5);
		set.add(1);
		set.add(53);
		set.add(24);
		set.add(132);
		System.out.println(set);
		
		/*
		 * Sets will not allow duplicate information.
		 * In addition it will auto sort based on hash codes.
		 */
		System.out.println("============");
		set = new TreeSet<>();
		set.add(5);
		set.add(10);
		set.add(4);
		set.add(5);
		set.add(1);
		set.add(53);
		set.add(24);
		set.add(132);
		System.out.println(set);
		
		System.out.println(set.contains(53));
		//NO RANDOM ACCESS for sets.
		
		//TBD Hashcodes

		System.out.println("====Queue====");
		Queue<Integer> q = new LinkedList<>();
		q.offer(5);	//Inserts an element from the back.
		q.offer(10);
		q.offer(13);
		q.offer(1);
		System.out.println(q);
		System.out.println(q.poll()); //Return and remove first item
		System.out.println(q);
		System.out.println(q.peek()); //Return but maintain first item
		System.out.println(q);
		System.out.println("=====Limited Queue====");
		ArrayBlockingQueue<Integer> q2 = new ArrayBlockingQueue<>(4);
		q2.offer(5);	
		q2.offer(10);
		q2.offer(13);
		q2.offer(1);
		q2.offer(22);	//Queue is full at this point, offer ignored
		q2.offer(28);
		q2.offer(32);
		q2.offer(99);
		q2.poll();
		q2.poll();
		q2.poll();
		q2.poll();
		q2.poll(); //Queue is empty, but its still safe to poll
		//q2.remove();
		//q2.element(); //No such element
		q2.offer(22);	
		q2.offer(28);
		q2.offer(32);
		q2.offer(99);	
		//q2.add(105); IllegalStateException
		System.out.println(q2);
		/*
		 * The queue methods have counterparts (Brought form the list family
		 * remove() will remove an element, but throw an exception queue is empty
		 * add() will add an element, but throw and exception if queue full
		 * element() which shows the front element, but throws an exception if queue
		 * 		is empty.
		 * The queue counterparts do not throw exceptions.
		 */
		
		
		
	}

}
