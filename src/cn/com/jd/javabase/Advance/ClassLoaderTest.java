package cn.com.jd.javabase.Advance;
import java.util.Date;
public class ClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//得到类加载的名字；AppClassLoader
		System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName()); 
		
		//null  ,说明类加载器为:BootStrap;
		//System.out.println(System.class.getClassLoader().getClass().getName()); 	
		
		ClassLoader loader=ClassLoaderTest.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName()+"-----");
			loader=loader.getParent();
			System.out.println(loader+"==parent");
		}
	 	
		System.out.println(new ClassLoadAttachment().toString());
		//出现问题：
		Class clazz=new MyClassLoader("loadlib").loadClass("ClassLoadAttachment");
		Date d1=(Date) clazz.newInstance();
		System.out.println(d1);
	}
}
