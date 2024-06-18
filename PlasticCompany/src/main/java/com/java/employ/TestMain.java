package com.java.employ;

import javassist.bytecode.stackmap.TypeData.UninitThis;

public class TestMain {

	int a = 20;
	
	void meth() {
		int a = 10;
		System.out.println(this.a);
		System.out.println(a);
	}
	public static void main(String[] args) {
		TestMain main = new TestMain();
		System.out.println(UninitThis.class);
	}
}
