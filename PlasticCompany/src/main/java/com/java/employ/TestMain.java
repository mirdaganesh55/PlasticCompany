package com.java.employ;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

	int a = 20;

	void meth() {
		int a = 10;
		System.out.println(this.a);
		System.out.println(a);
	}
	public static void main(String[] args) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(currentDate).trim();  // Trimming the formatted date

	}
}
