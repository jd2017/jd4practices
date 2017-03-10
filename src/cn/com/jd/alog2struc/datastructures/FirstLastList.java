package cn.com.jd.alog2struc.datastructures;
//双端链表
public class FirstLastList {
	private Link first;
	private Link last;
	public FirstLastList(){
		first = null;
		last = null;
	}
	public  boolean isEmpty(){
		return first == null;
	}
	public void insertFirst(int i){
		Link newLink = new Link(i);
		if(isEmpty())
			last = newLink;	//last -->newLink;
		newLink.next = first;//newLink-->old first
		first = newLink;	//first --> newLink
	}
	public void insertLast(int i){
		Link newLink = new Link(i);
		if(isEmpty())
			first = newLink;	//first -->newLink
		else
			last.next = newLink;//old last -->newLink
		last = newLink;			//last -->newLink
	}
	public int deleteFirst(){	//assumes non-empty list
		int temp = first.dData;
		if(first.next == null)	//if only one item
			last = null;		//last -->null
		first = first.next;		//first -->old next
		return temp;
	}
	public void displayList(){
		System.out.println("List (first-->last):");
		Link current = first;	//start at begginning
		while(current !=null){	//util end of list
			current.displayLink();	//print data
			current = current.next;//move to next link
		}
		System.out.println("");
	}
}
