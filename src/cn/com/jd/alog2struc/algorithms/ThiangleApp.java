package cn.com.jd.alog2struc.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThiangleApp {
	static int theNumber;
	public static void main(String[] args) throws IOException {
		System.out.println("Enter a number: ");
		theNumber = getInt();
		int theAnswer = triangle(theNumber);
		System.out.println("Triangle = "+theAnswer);
	}
	public static int triangle(int n){
		if(n==1){
			return 1;
		}else{
			return (n + triangle(n-1));
		}
	}
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
	public static int getInt() throws IOException{
		String s = getString();
		return Integer.parseInt(s);
	}
}
