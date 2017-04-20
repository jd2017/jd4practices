package cn.com.jd.alog2struc.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.jna.platform.win32.COM.TypeLibUtil.FindName;

public class ThiangleApp {
	static int theNumber;
	public static void main(String[] args) throws IOException {
//		testTriangle();
		
		int[] array = {2,6,8,9,10,12,15};
		int i = find(9, array.length, array);
		System.out.println(i);
	}
	public static void testTriangle() throws IOException{
		System.out.println("Enter a number: ");
		theNumber = getInt();
		int theAnswer = triangle(theNumber);
		System.out.println("Triangle = "+theAnswer);
	}
	//递归 N！ 
	public static int triangle(int n){
		if(n==1){
			return 1;
		}else{
			return (n * triangle(n-1));
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
	//二分查找法
	public static int find(long seachKey,int nElems,int[] array){
		int lowerBound = 0;
		int upperBound = nElems-1;
		int curIn;
		while(true){
			curIn = (lowerBound + upperBound)/2;
			if(seachKey == array[curIn]){
				return curIn;
			}else if(lowerBound > upperBound){
				return nElems;	// can't find it;
			}else{
				if(seachKey > array[curIn]){
					lowerBound = curIn +1;
				}else{
					upperBound = curIn -1;
				}
			}
		}
	}
}
