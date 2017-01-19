package cn.com.jd.javabase.thread;
/**
 * @author jd.bai
 * @date 2017年1月10日
 * @time 下午4:06:33
 *  同步函数用的哪一个锁？
	函数需要被对象调用，那么函数都有一个所属对象引用，就是this。
	所以同步函数使用的琐是this。
	
	如果同步函数被静态修饰时，对象不是this，因为静态方法不可以定义this对象。
	 静态进内存时，内存中没有本类对象，但是一定有该类对应的字节码文件对象。
	 类名.class	该对象的类型是class
	
	 静态的同步方法，使用的锁是该方法所在类的字节码文件对象。
 */
//同步函数使用的锁为this：
class Ticket1 implements Runnable{
	private int ticket=100;
	public void run(){
		while(true){
				this.show();
			 }
	}
	public synchronized void show()			//this 锁
	{
		if(ticket>0){
			try{Thread.sleep(1);}catch(Exception e){}
			System.out.println(Thread.currentThread().getName()+"_"+ticket);
			ticket--;
		}
	}
}
//控制main中主线程，让t1与t2同时执行。
class Ticket2 implements Runnable{
	private int ticket=1000;
	boolean flag=true;
	public void run(){
		if(flag){
			while(true){
				synchronized(this){
					if(ticket>0){
						try{Thread.sleep(1);}catch(Exception e){}
						System.out.println(Thread.currentThread().getName()+"code,,"+"_"+ticket);ticket--;
					}
				}
			}
		}else{
			while(true)
				show();
		}
	}
	public synchronized void show()			//this锁
	{
		if(ticket>0){
			try{Thread.sleep(1);}catch(Exception e){}
			System.out.println(Thread.currentThread().getName()+"show().."+"_"+ticket);ticket--;
		}
	}
	
}
//静态的同步方法，使用的锁是该方法所在类的字节码文件对象。类名.class
class Ticket3 implements Runnable{
	private static int ticket=1000;
	boolean flag=true;
	public void run()
	{
		if(flag){
			while(true){
				synchronized(Ticket3.class){		//静态的为 类.class
					if(ticket>0){
						try{Thread.sleep(1);}catch(Exception e){}
						System.out.println(Thread.currentThread().getName()+"code,,"+"_"+ticket);ticket--;
						if(ticket==0)
							System.exit(0);
					}
				}
			}
		}else{
			while(true)show();
		}
	}
	public static synchronized void show(){
		if(ticket>0){
			try{Thread.sleep(1);}catch(Exception e){}
			System.out.println(Thread.currentThread().getName()+"show().."+"_"+ticket);ticket--;
			if(ticket==0)
				System.exit(0);
		}
	}
	
}
public class SynchThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		ticket3Test();
	}     
	public static void ticket1Test(){
		Ticket1 t=new Ticket1();
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		t1.start();
		t2.start();
	}
	public static void ticket2Test() throws InterruptedException{
		Ticket2 t=new Ticket2();
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		t1.start();
		Thread.sleep(200); //主线程 暂停一会 t1与t2同时执行, 防止main全加载后，t.flag=false; 只执行show()代码块，死循环。
		t.flag=false;
		t2.start();
	}
	public static void ticket3Test() throws InterruptedException{
		Ticket3 t=new Ticket3();
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		t1.start();
		Thread.sleep(2); //主线程 暂停一会 t1与t2同时执行, 防止main全加载后，t.flag=false; 只执行show()代码块，死循环。
		t.flag=false;
		t2.start();
	}
}
