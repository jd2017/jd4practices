package cn.com.jd.javabase.io.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.junit.Test;
/**
 * @author jd.bai
 * @date 2016年12月28日
 * @time 下午2:54:23
 * 字符流：
	Reader Writer
	
	FileReader
	FileWriter
	
	BufferedReader
	BufferedWriter
	
	InputStreamReader	是字节流通向字符流的桥梁：
	OutputStreamWriter	是字符流通向字节流的桥梁：
	--------------------------------------------------------------------------------
	字节流：
	  InputStream OutputStream
	
	  FileInputStream FileOutputStream
	
	  BufferedInputStream  BufferedOutputStream
	
	OutputStream:			JDK1.0 
		此抽象类是表示输出字节流的所有类的超类。输出流接受输出字节并将这些字节发送到某个接收器.
	
	直接已知子类： 
	ByteArrayOutputStream, FileOutputStream, FilterOutputStream, ObjectOutputStream, OutputStream, PipedOutputStream 
	
	InputStream
		此抽象类是表示字节输入流的所有类的超类。 
	直接已知子类： 
	AudioInputStream, ByteArrayInputStream, FileInputStream, FilterInputStream, InputStream, ObjectInputStream, PipedInputStream, SequenceInputStream, StringBufferInputStream 
 */
/*
 * 1，明确源和目的。
		源：输入流。 InputStream Reader
		目的：输出流。OutputStream Writer
	2，操作的数目是否是纯文本。
		是：字符流。FileReader	FileWriter
		否：字节流  FileIntputStream  FileOutputStream
	3，当体系明确后，再明确要使用哪个具体的对象。
		通过设备来区分：
		源设备：内存，硬盘，键盘。
		目的设备：内存，硬盘，控制台。
 */

public class StreamDemo {
	String path=System.getProperty("user.dir")+File.separator+"filetest";
	@Test
	public void test(){
		systemIn();
	}
	public void systemIn() {
		InputStream in=System.in;
		int num=0;
	
		StringBuilder sb=new StringBuilder();
		while(true)
		{
			try {
				num=in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(num=='\r')
				continue;
			if(num=='\n'){
				String s=sb.toString();	
				if("over".equals(s))
					break;
				System.out.println(s.toUpperCase());
				sb.delete(0,s.length());			//清空容器
			}else{
				sb.append((char)num);			//存储字节
			}
		}
	}
	//控制台－》控制台
	public  void InputStreamReaderDemo() {
		/*//获取键盘录入对象。
		InputStream in=System.in;
		//将字节流对象转成字符流对象，使用转换流。InputStreamReader
		InputStreamReader is=new InputStreamReader(in);
		//为了提高效率，将字符串进行缓冲区技术高效操作。使用BufferedReader
		BufferedReader br=new BufferedReader(is);*/
		
		BufferedReader reader=
				new BufferedReader(new InputStreamReader(System.in));
		/*
		OutputStream out=System.out;		
		OutputStreamWriter osw=new OutputStreamWriter(out);
		BufferedWriter bw=new BufferedWriter(osw);*/
		
		BufferedWriter writer=
				new BufferedWriter(new OutputStreamWriter(System.out));
		
		String len=null;
		try {
			while((len=reader.readLine())!=null){
				if("over".equals(len))
					break;
				writer.write(len.toUpperCase());
				writer.newLine();		//换行，操作。
				writer.flush();			//刷新缓冲区数据
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//通过控制台保存数据到文件中；
	public void OutputStreamWriterMethod(){
		
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader reader= new BufferedReader(isr);
		try {
			//字符流 转换为字节流
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path+File.separator+"outPutStreamfile.txt",true),"UTF8");
			BufferedWriter writer= new BufferedWriter(osw);
				
				String len=null;
				while((len=reader.readLine())!=null){
					writer.write(len);
					if("over".equals(len))
						break;
					writer.newLine();
					writer.flush();
				}
				writer.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//copy 文件
	public void copyTest(){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(new File(path+File.separator+"img.jpeg"));
			fos = new FileOutputStream(new File(path+File.separator+"copy_img.jpeg"));
			byte[] bs = new byte[1024];
			int len = 0;
			while((len = fis.read(bs))!=-1)
				fos.write(bs,0,len);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null)
					fis.close();
				if(fos!=null)
					fos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
