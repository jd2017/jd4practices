package cn.com.jd.javabase.io.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class StreamDemo {
	@Test
	public void test(){
		copyTest();
		
	}
	public void copyTest(){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			String path=System.getProperty("user.dir")+File.separator+"filetest";
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
