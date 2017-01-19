package cn.com.jd.javabase.Advance.proxy;

import java.lang.reflect.Method;

public class AdviceImpl implements Advice{
	private long startTime=0;
	@Override
	public void beforeMehod(Method method) {
		startTime=System.currentTimeMillis();
		System.out.println("去学习");
	}
	@Override
	public void afterMethod(Method method) {
		System.out.println("去工作");
		Long endTime=System.currentTimeMillis();
		System.out.println(method.getName()+"...Running Time is..."+(endTime-startTime));	
	}

}
