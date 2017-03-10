package cn.com.jd.alog2struc.test;

import org.junit.Test;

import cn.com.jd.alog2struc.datastructures.StackX;

public class StackXTest {
 @Test
 public void test(){
	 StackX sx = new StackX(10);
	 int index = 0;
	 while(!sx.isFull()){
		 sx.push(index++);
		 System.out.println(sx.peek());
	 }
	 System.out.println("can't insert, stack is full");
	 while(!sx.isEmpty()){
		 long value = sx.pop();
		 System.out.println(value);
	 }
	 System.out.println("stack is empty");
 }
}
