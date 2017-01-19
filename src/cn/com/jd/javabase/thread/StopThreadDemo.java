package cn.com.jd.javabase.thread;

class Interrupt implements Runnable{
	private boolean bo=true;
	@Override
	public void run() {
		while(bo){
			System.out.println(Thread.currentThread().getName());
		}
	}
	public void changeB(){
		bo=false;
	}
	
}
//二、停止程序的特殊情况：
class Interrupt1 implements Runnable{
	private boolean bo=true;
	@Override
	public synchronized void run() {
		while(bo){
			try{
				wait();
			}catch(InterruptedException e){
				System.out.println("interrupter excp");
				bo=false;	//改变标识符终止线程。
			}
			System.out.println(Thread.currentThread().getName());
		}
	}
	public void changeB(){
		bo=false;
	}
	
}
public class StopThreadDemo {
	public static void main(String[] args) {
		interrupt1Test();
	}
	public static void interrupt1Test(){
		Interrupt1 in=new Interrupt1();
		Thread t=new Thread(in);
		Thread t2=new Thread(in);
		t.start();
		t2.start();
		
		int num=0;
		
		while(true){		
			if(num++ ==60){
				//in.changeB();		//控制线程，改变标识。
				t.interrupt();
				t2.interrupt();		//将处于冻结状态，让其运行状态。
				break;
			}
			System.out.println(num);
		}
		System.out.println("over");
	}
	public static void interruptTest(){
		Interrupt in=new Interrupt();
		Thread t=new Thread(in);
		Thread t2=new Thread(in);
		t.start();
		t2.start();
		
		int num=0;
		while(true){		
			if(num++ ==60){
				in.changeB();	//标识符控制循环；
				System.out.println(Thread.currentThread().getName()+num);
				break;
			}
			System.out.println(num);
		}
		
	}
}
