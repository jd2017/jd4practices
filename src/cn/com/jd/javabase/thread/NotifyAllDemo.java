package cn.com.jd.javabase.thread;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

class Resoure{
	private String name;
	private int counte=1;
	boolean bo=false;
		//	t1,t2
	public synchronized void setResource(String name){
		if(bo)						//用if时，多线程时两个以上，会出现数据错乱情况； 用while 解决此问题；
			try{
				//3，t1(放弃资格)、t2(获得执行权进入  等待 放弃资格)；//6,(t1获取资格)(t2放弃资格)；//12,(t1,放弃资格，t2获取资格，不判断标记执行)
				wait();}catch(Exception e){}
			this.name=name+"-------"+counte++;
			System.out.println(Thread.currentThread().getName()+"生产者-----------"+this.name);	//1, t1(获得执行权)
			bo=true;
			notify();		//2，t1 唤醒线程			//10,	t1,执行后， 唤醒本方线程池的t2；
	}
	//	t3,t4			//8,	t4或者t1执行；(若t4 获得执行权)
	public synchronized void getProduct(){
		if(!bo)			//7,  t3(放弃资格);		//9,	t3(放弃资格)t4(放弃资格)   只有t1运行	
			try{wait();}catch(Exception e){}
			System.out.println(Thread.currentThread().getName()+"消费者"+this.name);		//4, t3获得执行权，
			bo=false;
			notify();		//5, t3,唤醒线程。
	}
}
class Producer implements Runnable{
	private Resoure r;
	public Producer(Resoure r){
		this.r=r;
	}
	public void run(){
		while(true){		
				r.setResource("商品制造");
		}
	}
}
class Consumer implements Runnable{
	private Resoure r;
	public Consumer(Resoure r){
		this.r=r;
	}
	public void run(){
		while(true){		
			r.getProduct();
		}
	}
}
class Resoure1{
	private String name;
	private int counte=1;
	boolean bo=false;
		//	t1,t2
	public synchronized void setResource(String name){
		while(bo)			//多次判断标记
			try{
				wait();}catch(Exception e){}
			this.name=name+"-------"+counte++;
			System.out.println(Thread.currentThread().getName()+"生产者-----------"+this.name);
			bo=true;
			notifyAll();	//唤醒所有等待
	}
	public synchronized void getProduct(){
		while(!bo)				
			try{wait();}catch(Exception e){}
			System.out.println(Thread.currentThread().getName()+"消费者"+this.name);		
			bo=false;
			notifyAll();	
	}
}
class Producer1 implements Runnable{
	private Resoure1 r;
	public Producer1(Resoure1 r){
		this.r=r;
	}
	public void run(){
		while(true){		
				r.setResource("商品制造");
		}
	}
}
class Consumer1 implements Runnable{
	private Resoure1 r;
	public Consumer1(Resoure1 r){
		this.r=r;
	}
	public void run(){
		while(true){		
			r.getProduct();
		}
	}
}
class Resoure2{
	private String name;
	private int counte=1;
	boolean bo=false;
	//创造一个锁
	private final Lock lock=new ReentrantLock();
	private Condition  condition_Pro=lock.newCondition();
	private Condition  condition_Con=lock.newCondition();
	public void setResource(String name) throws InterruptedException{
		
		lock.lock();
		try {
			while (bo) {
				condition_Pro.await();	//造成当前线程在接到信号或被中断之前一直处于等待状态。
				}
				this.name = name + "-------" + counte++;
				System.out.println(Thread.currentThread().getName()
						+ "生产者-----------" + this.name);
				bo = true;
				condition_Con.signal();
		} finally {
			lock.unlock();
		}

	}
	public void getProduct() throws InterruptedException {

		lock.lock();
		try {
			while (!bo) {
				condition_Con.await();
				}
				System.out.println(Thread.currentThread().getName() + "消费者"+ this.name);
				bo = false;
				condition_Pro.signal();
		} finally {
			lock.unlock();
		}
	}
}
class Producer2 implements Runnable{
	private Resoure2 r;
	public Producer2(Resoure2 r){
		this.r=r;
	}
	public void run(){
		while(true){		
				try {
					r.setResource("商品制造");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}
class Consumer2 implements Runnable{
	private Resoure2 r;
	public Consumer2(Resoure2 r){
		this.r=r;
	}
	public void run(){
		while(true){		
			try {
				r.getProduct();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class NotifyAllDemo {
	public static void main(String[] args) {
		testResoure();
	}
	 public static void testResoure(){
		 Resoure r=new Resoure();
			Thread t1=new Thread(new Producer(r));
			Thread t2=new Thread(new Consumer(r));
			Thread t3=new Thread(new Producer(r));
			Thread t4=new Thread(new Consumer(r));
			t1.start();
			t2.start();
			t3.start();
			t4.start();
	 }
	 public static void testResoure1(){
		 Resoure1 r=new Resoure1();
			Thread t1=new Thread(new Producer1(r));
			Thread t2=new Thread(new Consumer1(r));
			Thread t3=new Thread(new Producer1(r));
			Thread t4=new Thread(new Consumer1(r));
			t1.start();
			t2.start();
			t3.start();
			t4.start();
	 }
	 public static void testResoure2(){
		Resoure2 r=new Resoure2();
			Thread t1=new Thread(new Producer2(r));
			Thread t2=new Thread(new Consumer2(r));
			Thread t3=new Thread(new Producer2(r));
			Thread t4=new Thread(new Consumer2(r));
			t1.start();
			t2.start();
			t3.start();
			t4.start();
	 }
}
