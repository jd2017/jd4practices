package cn.com.jd.javabase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemoTest {
	public static void main(String[] args) {
		//创造线程池
		ExecutorService es = Executors.newSingleThreadExecutor();
		//创建Callable 对象任务
		CallableDemo callTask = new  CallableDemo();
		//提交任务并获取执行结果
		Future<Integer> future = es.submit(callTask);
		/*
		 * 使用Callable+FutureTask获取执行结果
		//创建FutureTask  
        FutureTask<Integer> futureTask=new FutureTask<>(calTask);  
        //执行任务  
        es.submit(futureTask);  
         */
		//关闭线程池
		es.shutdown();
		try {
//			Thread.sleep(1000);
			 System.out.println("主线程在执行其他任务"); 
			 if(future.get()!=null){
				 System.out.println("future.get()-->"+future.get());  
			 }else{
				 //输出获取到的结果  
	             System.out.println("future.get()未获取到结果"); 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
			System.out.println("主线程在执行完成");  
	}
}
