package cn.com.jd.javabase.thread;

import org.junit.Test;
/*
	多线程问题：用synchronized来解决
	1，两个或者两个以上线程。
	2，是否操作同一个对象。
	
	线程通讯：
	其实就是多个线程在操作同一个资源。
	但是操作的动作不同。
	
	wait
	notify
	notifyAll
	都使用在同步中，因为要对持有监视器(锁)的线程操作。
	所以要使用在同步中，因为只有同步才具有锁。
	
	这些方法都定义在Object中?
	因为这些方法在操作同步中线程时，都必须要标识他们所操作线程持有的锁。
	同一个锁上的被等待线程，可以被同一个锁上notify唤醒。
	不可以对不同锁上的线程进行唤醒。
	
	等待和唤醒都必须是同一把锁。
	锁可以是任意对象，所以可以被任意对象调用的方法定义object类中。
*/
//notify作用于两个线程的唤醒操作，若多线程，用while，notifyAll();
//优化前
class Person{
	boolean bo=false;
	String name;
	String sex;
}
class Input implements Runnable{
	private Person p;				//为了操纵同一个对象；
	public Input(Person p){
		this.p=p;
	}
	public void run(){
		int x=0;
		while(true){
			synchronized(p){
				if(p.bo){
					try{p.wait();}catch(Exception e){e.printStackTrace();}	//让线程对象处于休眠状态
				}
				if(x==0){
					p.name="麦克";p.sex="男";
				}else{ 
					p.name="Girl";p.sex="women";
				}
				x=(x+1)%2;
				p.bo=true;
				p.notify();			//唤醒线程对象；
			}
		}
	}
}
class Output implements Runnable{
	private Person p;
	public Output(Person p){
		this.p=p;
	}
	public void run(){
		while(true){
			synchronized(p){
				if(!p.bo)
					try{p.wait();}catch(Exception e){e.printStackTrace();}
				System.out.println(p.name+"______"+p.sex);
				p.bo=false;
				p.notify();
			}
		}
	}
}	
//优化后：
class Animal{
	boolean bo=false;
	private String name;
	private String sex;
	public synchronized void set(String name,String sex){
		if(bo){
			try{System.out.println("set wait");
				wait();}catch(Exception e){e.printStackTrace();}
		}
		this.name=name;
		this.sex=sex;
		bo=true;
		notify();
	}
	public synchronized void getMethod(){
			if(!bo){
				try{System.out.println("get wait");
					wait();}catch(Exception e){e.printStackTrace();}
			}
			System.out.println(this.name+"______"+this.sex);
			bo=false;
			notify();
	}
}
class Input1 implements Runnable{
	private Animal a;
	public Input1(Animal a){
		this.a=a;
	}
	public void run(){
		int x=0;
		while(true){
			if(x==0){
				a.set("麦克", "男");
			}else{
				a.set("LiLy", "women");
			}
			x=(x+1)%2;
		}
	}
}
class output1 implements Runnable{
	private Animal a;
	public output1(Animal a){
		this.a=a;
	}
	public void run(){
		while(true){
			a.getMethod();
		}
	}
}
public class NotifyThreadDemo {
	public static void main(String[] args) {
		test2();
	}
	
	public static void test1() {
		Person p=new Person();		//定义一个共享数据。
		new Thread(new Input(p)).start();
		new Thread(new Output(p)).start();
	}

	public static void test2(){
		Animal a=new Animal();		//定义一个共享对象。
		new Thread(new Input1(a)).start();
		new Thread(new output1(a)).start();
	}
}

