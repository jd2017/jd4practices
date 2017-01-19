package cn.com.jd.javabase.Advance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ReflectTest {
	// 反射出java 类：
		private static void method_1() throws Exception {
			int it = 1;
			String str="abc";
			Class clazz1=str.getClass();
			Class clazz2=String.class;
			Class clazz3=Class.forName("java.lang.String");
			
			sop(clazz1);			//class java.lang.String
			sop(clazz1.getName());	//java.lang.String
			
			System.out.println(clazz1==clazz2);
			System.out.println(clazz2==clazz3);
			sop(clazz1.isPrimitive());//false
			sop(int.class.isPrimitive());//true; 判定指定的 Class 对象是否表示一个基本类型。
			sop(int.class==Integer.class);//false  
			sop(int.class==Integer.TYPE);//true
			sop(Double.TYPE==double.class);		
			
			sop(int[].class.isPrimitive());//false
			sop(int[].class.isArray());		//true
	}
	//反射 类构造方法：
	private static void method_Constructor() throws Exception {

		Constructor[] constructor=
			Class.forName("java.lang.String").getConstructors();
		for(Constructor cons:constructor){
			sop(cons);
		}
		Constructor constructor1=String.class.getConstructor(StringBuffer.class);//编译时
		//通常做法，new String(new StirngBuffere("abc"));
		String str=(String)constructor1.newInstance(/*"abc"运行时报错*/new StringBuffer("abc"));//运行时。用到上面的实例对象。
		sop(str);
		//Class.newInstance()方法。该方法内部先得到默认的构造方法，用该构造方法创建实例对象。
		Constructor con=Class.forName("java.lang.String").getConstructor();
		String obj= (String)con.newInstance();
		sop(obj);
	}
	//修改 对象中的字段。
	public static void updateField() throws Exception{
		class FieldBean {  
		    public String name = "abc";  
		    public String address = "cba";  
		    @Override  
		    public String toString() {  
		        return name + ", " + address;   
		    }  
		}
		FieldBean fb = new FieldBean();
		sop(fb);
		Field[] fields=fb.getClass().getFields();
		for(Field field:fields){
			if(field.getType()==String.class){	//比较的字节码类型
				String oldStr=(String)field.get(fb);
				String newStr=oldStr.replace('a', '?');
				field.set(fb, newStr);
			}
		}
		sop(fb);
	}
	// 反射字段
	private static void method_Fields() throws  Exception {
		class ReflectPoint{
			private int x;
			public int y;
			public ReflectPoint(int x,int y) {
				this.x = x;
				this.y = y;
			}
		}
		ReflectPoint pt1=new ReflectPoint(3,5);
		Field fieldY=pt1.getClass().getField("y");//必须要为 public
		//fieldY=?? 不代表某个对象的变量。而是类上，要用它去取对某个对象对应的值。
		sop(fieldY.get(pt1));
		Field fieldX=pt1.getClass().getDeclaredField("x");//privte
		fieldX.setAccessible(true);	//暴力反射
		sop(fieldX.get(pt1));
		
	}
	//反射出对象中的方法。
	private static void method_Method(String[] args) throws Exception {
		String str1="abcde";
		//str1.charAt(1);
		Method methodCharAt=String.class.getMethod("charAt",int.class);//传递参数方法与参数类型字节码。
		Character ch=(Character)methodCharAt.invoke(str1, 1);
//			Character ch1=(Character)methodCharAt.invoke(null, 2);
		sop(methodCharAt.invoke(str1,new Object[]{4}));//数组长度为1，对象为2，jdk1.4
		sop(ch);
		
		//调用main方法
		//传统方式。TestArguments.main(new String[]{"12","23","345"});
		//调用main方法
		//传统方式。TestArguments.main(new String[]{"12","23","345"});
		Class clz = Class.forName("cn.com.jd.javabase.Advance.MainReflect");
		Object obj = clz.newInstance();
		Method m = clz.getMethod("main", String[].class);
		m.invoke(obj, new Object[]{new String[]{"12","ab"}}); 	//会拆分数组。
		m.invoke(obj, (Object)new String[]{"12","ab"});	//当作一个对象。
	}
	//数组构造方法。
	private static void method_Array() {
		int[] 	a1=new int[]{1,2,3};
		int[] 	a2=new int[4];
		int[][] a3=new int[3][4];
		String[] a4=new String[]{"a","b","c"};
		sop(a1.getClass()==a2.getClass());	//true
//		sop(a1.getClass()==a3.getClass()); //错误
//		sop(a1.getClass()==a4.getClass()); 错误
		sop(a1.getClass().getName());		// [I
		sop(a1.getClass().getSuperclass().getName());	//java.lang.Object
		sop(a4.getClass().getSuperclass().getName());	//java.lang.Object
		
		Object 		a1Obj=a1;
		Object 		a2Obj=a2;
	//	Object[] 	aObj1=a1;	错误.
		Object[] 	aObj3=a3;
		Object[]	aObj5=a4;
		
		sop(a1);					//[I@18a992f	        					 
		sop(a4);					//[Ljava.lang.String;@4f1d0d
		sop(Arrays.asList(a1));		//符合 JDK1.5	[[I@18a992f]
		sop(Arrays.asList(a4));		//符合JDK 1.4	[a, b, c]
		
		//打印数组：
		printArray(a4);
	}
	//数组的反射：
	public static void printArray(Object obj){
		Class clazz=obj.getClass();
		if(clazz.isArray())						//是否为一个数组
		{
			int length=Array.getLength(obj);	//获得数组长度
			for(int i=0;i<length;i++){			//打印数组
				sop(Array.get(obj,i));
			}
		}else{
			sop("not array");
		}
	}
	public static void reflectArrayList() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		/*getRealPath();//金山词霸/内部
		 * 一定要记住用完整的路径，但完整路径不是硬编码，而是运算出来的。*/
//		InputStream ips=new FileInputStream("config.properties");		//加载 硬盘文件
		
//		类加载器 加载文件，没有FileOutputSteam
//		InputStream ips=ReflectTest.class.getClassLoader().getResourceAsStream("cn/com/jd/javabase/Advance/config.properties");
//		Class 加载 配置文件,相对路径加载。
//		InputStream ips=ReflectTest.class.getResourceAsStream("config.properties");
//		Class 加载 配置文件,绝对路径加载。
		InputStream ips=ReflectTest.class.getResourceAsStream("/cn/com/jd/javabase/Advance/config.properties");
		
		Properties prop=new Properties();								//创建与流相关集合。
		prop.load(ips);													//加载在流对象，关联流。
		ips.close();													//关闭window占用的对象。
		Set set=prop.keySet();
		for(Iterator it=set.iterator();it.hasNext();){
			System.out.println(it.next());
		}
		String value=(String) prop.getProperty("className");		//通过key 获取 value
		System.out.println(value);
		Collection collection=
			(Collection) Class.forName(value).newInstance();		//获取文件集合对象
		ReflectPoint pt1=new ReflectPoint(1, 3);
		ReflectPoint pt2=new ReflectPoint(2, 3);
		ReflectPoint pt3=new ReflectPoint(1, 3);
		collection.add(pt1);
		collection.add(pt2);
		collection.add(pt3);//唯一性，没加进去。
		
		//pt1.y=9; 若为hashCode时，不能修改
		System.out.println(collection.size());
		System.out.println(collection.toString());
	}
	public static<T> void sop(T t){
		System.out.println(t);
	}
	public static void main(String[] args) throws Exception {
		reflectArrayList();
	}
}
class MainReflect {
	public static void main(String[] names) {
		for(String str:names){
			System.out.println(str);
		}
	}
}
