package cn.com.jd.alog2struc.test;

import cn.com.jd.alog2struc.datastructures.Link;
import cn.com.jd.alog2struc.datastructures.LinkList;

public class LinkListTest {
	public static void main(String[] args) {
		LinkList theList = new LinkList();
		theList.insertFirst(22);
		theList.insertFirst(33);
		theList.insertFirst(55);
		theList.insertFirst(66);
		theList.insertFirst(77);
		
		theList.displayList();
//		while(!theList.isEmpty()){
//			Link aLink = theList.deleteFirst();
//			System.out.println("Delete");
//			aLink.displayLink();
//			System.out.println("");
//		}
//		theList.displayList();
		
		Link f = theList.find(33);
		if(f !=null)
			System.out.println("Found link with key："+f.dData);
		else
			System.out.println("Can't find link");
		Link d = theList.delete(66);
		if(d != null)
			System.out.println("Deleted link with key："+d.dData);
		else
			System.out.println("Can't find link");
		theList.displayList();
	}
}
