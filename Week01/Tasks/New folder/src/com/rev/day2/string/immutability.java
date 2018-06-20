package com.rev.day2.string;

public class immutability {

		
		
		public static void main(String[] args) {
			String s1 = "bob"; // this is a string literal
			
			String s2 = new String("bob");
			String s3 = "bob";
			
			System.out.println(System.identityHashCode(s1)+":" + s1);
			System.out.println(System.identityHashCode(s3)+":" + s3);
			System.out.println(System.identityHashCode(s2)+":" + s2);
			System.out.println(System.identityHashCode(s2.intern())+":" + s2);
			String s4 = s2;
			System.out.println(System.identityHashCode(s4)+":" + s4);
			
			s2 += 's';
			
			System.out.println(System.identityHashCode(s2) +":" + s2);
			
			
			System.out.println("=======Perfrmance");
			
			String s = "";
			StringBuilder sbuilder = new StringBuilder("");
			StringBuffer sbuffer = new StringBuffer("");
			double tstart;
			int iterations = 100_000_000;
			
//			tstart = System.currentTimeMillis();			
//			for(int i = 0; i < iterations; i++) {
//				s += 's';
//				
//			}
//			System.out.println(System.currentTimeMillis() - tstart);
			
			tstart = System.currentTimeMillis();			
			for(int i = 0; i < iterations; i++) {
				sbuilder.append('s');
				
			}
			System.out.println(System.currentTimeMillis() - tstart);
			
			tstart = System.currentTimeMillis();			
			for(int i = 0; i < iterations; i++) {
				sbuffer.append('s');
				
			}
			System.out.println(System.currentTimeMillis() - tstart);
		}
}
