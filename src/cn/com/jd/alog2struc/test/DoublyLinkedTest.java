package cn.com.jd.alog2struc.test;

import cn.com.jd.alog2struc.datastructures.DoublyLinked;

public class DoublyLinkedTest {
	public static void main(String[] args) {
		DoublyLinked theList = new DoublyLinked();
		theList.insertFirst(10);
		theList.insertFirst(20);
		theList.insertFirst(30);
		
		theList.insertLast(40);
		theList.insertLast(50);
		theList.insertLast(60);
		
		theList.displayForward();
		theList.displayBackward();
		
		theList.deleteFirst();
		theList.deleteLast();
		theList.deleteKey(10);
		
		theList.displayForward();
		
		theList.insertAfter(40, 41);
		theList.insertAfter(41, 45);
		
		theList.displayForward();
	}
}
