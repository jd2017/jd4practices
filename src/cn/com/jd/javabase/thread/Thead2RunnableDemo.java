package cn.com.jd.javabase.thread;

import org.junit.Test;

//ThreadDemo
class Demo1 extends Thread
{
	public void run(){						//*封装要运行的代码*
		for(int i=0; i<100;i++){
			System.out.println((Thread.currentThread()==this)+"="+this.getName()+i);
		}
	}
}
//RunnableDemo
class Ticket implements Runnable{
	private  int tick=1000;
	public void run(){
		boolean bo=true;
		while(bo){
				if(tick>0){
					System.out.println(Thread.currentThread().getName()+"tic"+tick--);
				}else{	
					bo=false;
				}
			}
	}
}
//模拟金库存钱：
/*
* 1,银行：增加钱金额、总数；
* 2，客户：不同的客户存钱；bank 增加钱。
*/
class Bank{
	private int sum;
	public synchronized void add(int x){
			try {Thread.sleep(10);} catch (Exception e) {e.printStackTrace();}
			sum=sum+x;
			System.out.println(Thread.currentThread().getName()+"增加后的总金额"+sum);
	}
}
class Person1 implements Runnable{
	private Bank b=new Bank();	//new一个对象防止空指针异常
	public  void run(){
		for(int i=0;i<100;i++){
			b.add(1);
		}
	}
}
public class Thead2RunnableDemo {
	
	@Test
	public void threadTest(){
			Demo1 d=new Demo1();
			d.start();			//开启线程，并执行run方法。
			//d.run();			//仅仅是对象的调用方法。
				for(int i=0; i<100;i++){	//main线程运行。
					System.out.println("main_"+i);
			}	
	}
	/**
	 * 只要主线程结束，整个程序将会退出，这就是采用junit的时候奇怪退出程序的原因。 
	 * @throws InterruptedException 
	 */
	@Test
	public void runnableTest() throws InterruptedException{
		Ticket t=new Ticket();
		Thread td1=new Thread(t);
		Thread td2=new Thread(t);
		Thread td3=new Thread(t);
			td1.start();
			td2.start();
			td3.start();
			
			td3.join();//等待该线程终止
	}
	@Test
	public void bankTest() throws InterruptedException{
		Person1 p=new Person1();
		Thread t1=new Thread(p);
		Thread t2=new Thread(p);
		t1.start();
		t2.start();
		
		t2.join();
		t1.join();
		
		
	}
	@Test
	public void ticketTest() throws InterruptedException{
		Ticket t=new Ticket();
		Thread td1=new Thread(t);
		Thread td2=new Thread(t);
		Thread td3=new Thread(t);
			td1.start();
			td2.start();
			td3.start();
		td3.join();
	}
//	public static void main(String[] args) {
//		
//	}
}
