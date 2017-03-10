package cn.com.jd.alog2struc.datastructures;

public class Link {
	public int dData;
	public Link next;		//next link in list;
	public Link previous;	//
	public Link(int id){
		dData = id;
	}
	public void displayLink(){
		System.out.println(dData+" ");
	}
}
