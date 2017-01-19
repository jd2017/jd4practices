package cn.com.jd.javabase.Advance.proxy;
import java.io.InputStream;
import java.util.List;

public class BeanFactoryAOPTest {
	public static void main(String[] args) {
		InputStream ips = BeanFactoryAOPTest.class.getResourceAsStream("config.properties");
		BeanFactory beanFactory = new BeanFactory(ips);
		Object bean=beanFactory.getBean("key");
		System.out.println(bean.getClass().getName());
		((List)bean).clear();
	}
}
