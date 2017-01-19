package cn.com.jd.javabase.Advance.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

//生成动态代理的工厂。
public class ProxyFactoryBean {
	private Advice advice;
	private Object target;
	public Advice getAdvice() {
		return advice;
	}
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	//获取一个动态	代理类。
	public Object getProxy(){
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				//调用方法之前。
				advice.beforeMehod(method);
				//调用目标相应的方法：把参数传进去让其运行起来。
				Object obj = method.invoke(target, args);
				TimeUnit.SECONDS.sleep(1);
				//调用方法之后。
				advice.afterMethod(method);
				return obj;
			}
		});
		return proxy;
	}
}
