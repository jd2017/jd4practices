package cn.com.jd.javabase.thread;

import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer> {
	private int sum;
	@Override
	public Integer call() throws Exception {
		System.out.println("Callable子线程开始计算了！");
		Thread.sleep(1000);
		for(int i =0;i<10000;i++){
			sum = sum+i;
		}
		System.out.println("Callable子线程计算结束！");
		return sum;
	}

}
