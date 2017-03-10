package cn.com.jd.alog2struc.test;

import cn.com.jd.alog2struc.datastructures.FirstLastList;

public class FirstLastListTest {
		public static void main(String[] args) {
			
		FirstLastList flList = new FirstLastList();
		flList.insertFirst(11);
		flList.insertFirst(22);
		flList.insertFirst(33);
		flList.insertLast(44);
		flList.insertFirst(55);
		
		flList.displayList();
		
		flList.deleteFirst();

		flList.displayList();
	}
}
