package cn.com.jd.javabase.Advance.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
	//创建一个与流相关的集合。
	Properties prop  = new Properties();
	//将流对象传进去加载文件到集合中。
	public BeanFactory(InputStream ips){
		try {
			prop.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//通过流文件名，获取文件中的key, 通过key反射出Class类，通过默认的构造方法获取一个对象(代理类或者目标类)。
	public Object getBean(String name){
		String className = prop.getProperty(name);
		Object bean = null;
		try {
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(bean instanceof ProxyFactoryBean){
			Object proxy = null;
			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)bean;
			try {
				Advice advice =(Advice)Class.forName(prop.getProperty(name + ".advice")).newInstance();
				Object target =(Object)Class.forName(prop.getProperty(name + ".target")).newInstance();
				proxyFactoryBean.setAdvice(advice);
				proxyFactoryBean.setTarget(target);
				bean = proxyFactoryBean.getProxy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		}
		return bean;
	}
}
