package com.amazonbyod.test;

import java.util.Random;

public class Test {
	
	public void testing(){
		Sample s = new Sample();
		System.out.println(s.getSample());
	}
	
	public static void main(String args[]){
		Random random = new Random();
		int randomNumber = random.nextInt(100 - 10) + 10;
		System.out.println(randomNumber);
	}
}
