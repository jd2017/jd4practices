package cn.com.jd.javabase.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

public class SystemDemo {
	String path=System.getProperty("user.dir")+File.separator+"filetest";
	@Test
	public void main(){
		load();
	}
	/**
	 * Properties 类
		表示了一个持久的属性集。Properties 可保存在流中或从流中加载。属性列表中每个键及其对应值都是一个字符串。 
		Properties  是Hashtable 的子类，也就是说，它具备map集合的特点，它里面存的键值对都是字符串。
		
		数据存储到集合中。
	 * 需求：将info.txt中的键值对 数据存到集合中操作。
	 * 1，用一个流 和 info.txt 文件关联。
	 * 2，读取一行数据，将改行数据用"="进行切割。
	 * 3，等号左边 为键，右边为 值。 存入Properties集合中。
	 */
	public  void load(){
		FileInputStream fis;
		try {
			fis = new FileInputStream(path+File.separator+"info.txt");
			Properties prop=new Properties();
			//数据加进集合
			prop.load(fis);
			System.out.println(prop);
			//打印到控制台
			prop.list(System.out);
			
			//改变的是内存信息
			prop.setProperty("zhangsan", 34+"");
//			System.out.println(prop);
			
			//改变硬盘信息时：
			FileOutputStream fos=new FileOutputStream(path+File.separator+"info.txt");
			prop.store(fos,"xiaoju");
			System.out.println(prop);
			fos.close();
			fis.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * 实现一个功能，记录运行次数，
	 * 次数已经到，给出注册提示。
	 * 
	 * 计数器： 启动程序，重新开始从0 计数，不能满足。
	 * 为了让程序即使结束，该计数器的值也在。
	 * 下次程序启动会先加载该 计数器的值，并加1户从新存储起来。
	 * 所以建立一个配置文件。记录该软件使用次数。
	 * 该配置文件使用键值对的形式。
	 * 这样便于阅读，并操作。
	 * 
	 * 键值对数据是 map集合。
	 * 数据是 以 文件 形式存储， 使用IO技术。
	 * 那么 map+IO ——>Properties.
	 * 
	 * 配置文件可以实现应用 程序数据的共享。
	 */
	public  void creatFile(){
		Properties prop=new Properties();
			//将文件封装为一个对象。方便进行操作处理。
			File file=new File(path+File.separator+"prop.ini");
		try {
			if(!file.exists())
				file.createNewFile();
		//prop 加载 InputStream 对象，所以用FileInputStream();
		FileInputStream fis=new FileInputStream(file);
		
		//加载流中的数据。到Properties。
		prop.load(fis);
		
		int count=0;
		String value=prop.getProperty("time");
		if(value!=null){
			count=Integer.parseInt(value);
			if(count>2){
				System.out.println("使用3次数已经到，请注册使用");
				return ;
			}
		}
		count++;
		prop.setProperty("time",Integer.toString(count));
		//创建一个存储 写入 字节流。
		FileOutputStream fos=new FileOutputStream(file);
		prop.store(fos,"juanjuan");
		fis.close();
		fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//调取电脑应用程序；
	public  void RuntimeMethod(){
		Runtime r=Runtime.getRuntime();
		Process p = null;
		try {
			//r.exec("notepad.exe RuntimeDemo.java");
			p = r.exec("open /Applications/iTunes.app");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.destroy();
	}
	//输出异常日志；
	public  void exceptionMethod() {
		try{
			int arr[]={1,2,3};
			System.out.println(arr[5]);
		}catch(Exception e)
		{
			Date d=new Date();
			PrintStream ps = null;
			try {
				ps = new PrintStream(path+File.separator+"exception.log");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss a");
				String date=sdf.format(d);
				ps.println(date);	
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace(ps);
		}
	}
	public void properties_test(){
		Properties pro=System.getProperties();
		System.out.println(pro);
		pro.list(System.out);
		try {
			pro.list(new PrintStream(path+File.separator+"systeminfo.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void system_test(){
		//因为Properties是Hashtable的子类，也就是Map集合的一个子类对象。
		//那么可以通过map的方法取出该集合中的元素。
		//该集合中存储都是字符串。没有泛型定义。
		
		//如何在系统中自定义一些特有信息呢？
		System.setProperty("mykey","myvalue");

		//获取指定属性信息。
		String value = System.getProperty("os.name");
		System.out.println("value="+value);

		//可不可以在jvm启动时，动态加载一些属性信息呢？
		String v = System.getProperty("mykey");
		System.out.println("v="+v);

		Properties props=System.getProperties();
		for(Entry<Object, Object> entry:props.entrySet())
		{
			Object key=entry.getKey();
			Object value1=entry.getValue();
			System.out.println("key="+key+"========"+"value="+value1);
		}
	}
	
}
