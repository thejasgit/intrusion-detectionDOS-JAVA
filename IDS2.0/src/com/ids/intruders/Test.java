package com.ids.intruders;

import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r=new Random();
		
		int msg=r.nextInt(2)+1;
		
		System.out.println("   msg    "+msg);
	}

}
