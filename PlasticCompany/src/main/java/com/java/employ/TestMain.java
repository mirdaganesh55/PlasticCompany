package com.java.employ;

public class TestMain {

	
	public static void main(String[] args) {
		EmployDAOImpl impl = new EmployDAOImpl();
		System.out.println(impl.generateEmployID());
	}
}
