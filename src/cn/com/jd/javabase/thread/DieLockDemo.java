package cn.com.jd.javabase.thread;
//死锁：
//同步中 对象 相互嵌套；
class TicketDemo implements Runnable{
	private static int ticket=1000;
	boolean flag=true;
	Object obj=new Object();
	public void run(){
		if(flag){
			while(true){
				synchronized(obj){		//object;
					show();			//this
				}
			}
		}else{
			while(true)
				show();
		}
	}
	public synchronized void show()			//this对象
	{
		synchronized(obj){			//object
			if(ticket>0){
				try{Thread.sleep(1);}catch(Exception e){}
				System.out.println(Thread.currentThread().getName()+"code,,"+"_"+ticket);ticket--;
			}
		}
	}
	
}
class Lock implements Runnable{
	boolean bo=true;
	public void run(){
		if(bo){
			while(true){
				synchronized(LockObject.objA){
					System.out.println("objA--------");
					synchronized(LockObject.objB){
						System.out.println("objB");
					}
				}
			}
		}else{
			while(true){
				synchronized(LockObject.objB){
					System.out.println("objBBBB");
					synchronized(LockObject.objA){
						System.out.println("objA0000000000");
					}
				}
			}
		}
	}
}
class LockObject{
	static LockObject objA=new LockObject();
	static LockObject objB=new LockObject();
}
public class DieLockDemo {
	public static void main(String[] args) throws InterruptedException {
		ticketDemoTest();
	}
	public static void ticketDemoTest() throws InterruptedException{
		TicketDemo t=new TicketDemo();
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		t1.start();
		Thread.sleep(1000);
		t.flag=false;
		t2.start();
	}
	public static void lockTest() throws InterruptedException{
		Lock l=new Lock();
		Thread t=new Thread(l);
		Thread t2=new Thread(l);
		t.start();
		Thread.sleep(1);	//防止main函数一次性执行完不能切换。进入死循环！！
		l.bo=false;
		t2.start();
	}
}
