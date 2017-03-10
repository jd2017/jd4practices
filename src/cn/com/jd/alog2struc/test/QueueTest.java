package cn.com.jd.alog2struc.test;

import cn.com.jd.alog2struc.datastructures.Queue;

public class QueueTest {
public static void main(String[] args) {

	Queue queue = new Queue(5);
	for(int i=5;i<12;i++){
		queue.insert(i);
		if(queue.isFull())
			break;
	}
	for(int i=0;i<10;i++){
		long value = queue.remove();
		System.out.println(value);
		if(queue.isEmpty())
			break;
	}
}
}
