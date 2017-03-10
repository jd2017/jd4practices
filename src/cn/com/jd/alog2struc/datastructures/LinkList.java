package cn.com.jd.alog2struc.datastructures;
public class LinkList {
	private Link first;
	public LinkList(){
		first = null;
	}
	public boolean isEmpty(){
		return (first == null);
	}
	public void insertFirst(int id){
		Link newLink = new Link(id); //make new link
		newLink.next = first;		//newLink -->old first
		first = newLink;			// first -->newLink
	}
	public Link deleteFirst(){
		Link temp = first;		//save reference to link
		first = first.next;		//delete it; first -->old next
		return temp;			//return deleted link
	}
	public void displayList(){
		System.out.println("List (first-->last):");
		Link current = first;	//start at beginning of list
		while(current != null){	//until end of list
			current.displayLink();//print data
			current = current.next;//move to next link
		}
		System.out.println("");
	}
	public Link find(int key){  //assumes non-empty list
		Link current = first;
		while(current.dData != key){
			if(current.next == null)
				return null;		//didn't find it
			else
				current = current.next;//go to next link
		}
		return current;				//found it
	}
	public Link delete(int key){ 
		Link current = first;
		Link previous = first;
		//遍历目标值
		while(current.dData != key){
			if(current.next == null)
				return null;			//didn't find it
			else{
				previous = current;		//go to next link
				current = current.next;
			}
		}							//found it
		if(current == first)		//if first link
			first = first.next;		//change first
		else
			previous.next = current.next;//bypass it
		
		return current;
	}
}
