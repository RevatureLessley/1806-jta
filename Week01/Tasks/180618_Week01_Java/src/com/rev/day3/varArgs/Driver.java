package com.rev.day3.varArgs;

public class Driver {

	public static void main(String[] args) {
		Driver d = new Driver();
		int result = d.addition(10, 20);

		System.out.println(result);
	}

	public int addition(int a, int b) {
		return a + b;
	}

	public int addition(int... nums) {
		int result = 0;

		for (int i : nums)
			result += i;

		return result;
	}
}
