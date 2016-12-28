package cn.com.jd.javabase.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import sun.rmi.runtime.RuntimeUtil.GetInstanceAction;

/**
 * @author jd.bai
 * @date 2016年12月28日
 * @time 上午11:37:35
 * 
 * Writer：
 写入字符流的抽象类。
直接已知子类： 
	BufferedWriter, CharArrayWriter, FilterWriter, OutputStreamWriter, PipedWriter, 
	PrintWriter, StringWriter 
 */
public class FileWriterDemo {
	@Test
	public void test(){
		fileWriterTest2();
	}
	public void fileWriterTest(){
		//创建一个FileWriter对象。该对象一被初始化就必须要明确被操作的文件。
		//而且该文件会被创建到指定目录下。如果该目录下已有同名文件，将被覆盖。
		//其实该步就是在明确数据要存放的目的地。
		FileWriter fw;
		try {
			fw = new FileWriter(this.getClass().getResource("").getPath()+"demo.txt");
			//调用write方法，将字符串写入到流中。
			
			fw.write("abcde");
			//刷新流对象中的缓冲中的数据。
			//将数据刷到目的地中。
			//fw.flush();

			//关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据。
			//将数据刷到目的地中。
			//和flush区别：flush刷新后，流可以继续使用，close刷新后，会将流关闭。
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fileWriterTest2(){
		//创建一个共享引用
		FileWriter fw=null;
		try{	
			//传递一个true 参数，代表不覆盖，已有文件，并在已有文件尾部续写数据。
			fw=new FileWriter(this.getClass().getResource("").getPath()+"demo.txt",true);
			//在记事本中写入换行数据。
			fw.write("abc\r\ndefg"); 
		}catch(Exception e){
			System.out.println("Catch="+e.toString());
		}
		finally{
			try{
				//防止空指针异常。
				if(fw!=null)
					fw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
