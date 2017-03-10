package cn.com.jd.alog2struc.datastructures;

public class DoublyLinked {
	private Link first;
	private Link last;
	public DoublyLinked(){
		first = null;
		last = null;
	}
	public boolean isEmpty(){
		return (first == null);
	}
	public void insertFirst(int i){
		Link newLink = new Link(i);
		if(isEmpty())
			last = newLink;			//last -->newLink
		else
			first.previous = newLink;//newLink <-- old first
		newLink.next = first;		//newLink -->old first
		first = newLink;			//first --> newLink
	}
	public void insertLast(int i){
		Link newLink = new Link(i);
		if(isEmpty())
			first = newLink;		//first -->newLink
		else{
			last.next = newLink;	//old last -->newLink
			newLink.previous = last;//old last <-- newLink
		}
		last = newLink;				//last -->newLink
	}
	public Link deleteFirst(){		//delete first link assumes non-empty list
		Link temp = first;
		if(first.next == null)
			last = null;
		else
			first.next.previous = null;//old next -->null
		first = first.next;				//first --> old next
		return temp;
	}
	public Link deleteLast(){		//delete first link assumes non-empty list
		Link temp = last;
		if(first.next == null)
			first = null;
		else
			last.previous.next = null;
		last = last.previous;
		return temp;
	}
	public boolean insertAfter(int key, int dd){
		 Link current = first;
		 while(current.dData !=key){
			 current = current.next;
			 if(current == null)
				 return false;
		 }
		 Link newLink = new Link(dd);	//make new link
		 if(current == last){
			 newLink.next =null;
			 last = newLink;			//last -->newLink
		 }else{
			 newLink.next = current.next;//newLink --> old next
			 current.next.previous = newLink;//old next--> newLink
		 }
		 newLink.previous = current;	//newLink -->old current;
		 current.next = newLink;		//old current -->newLink
		return true;
	}
	public Link deleteKey(int key){
		Link current = first;
		while(current.dData !=key){
			current = current.next;
			if(current ==null)
				return null;
		}
		if(current == first)
			first = current.next;			//first -->old next;
		else
			current.previous.next = current.next;//old previous --> old next;
		if(current == last)
			last = current.previous;			//last -->old previous
		else
			current.next.previous = current.previous;//old next -->old previous
		
		return current;
	}
	public void displayForward(){
		System.out.println("List first-->last:");
		Link current = first;
		while(current!=null){
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
	public void displayBackward(){
		System.out.println("last -->first:");
		Link current = last;
		while(current !=null){
			current.displayLink();
			current = current.previous;
		}
		System.out.println("");
	}
	
	
	
}
