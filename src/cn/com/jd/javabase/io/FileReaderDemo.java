package cn.com.jd.javabase.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.processing.FilerException;

import org.junit.Test;

/**
 * @author jd.bai
 * @date 2016年12月28日
 * @time 上午10:25:05
	Reader:
	用于读取字符流的抽象类。
	直接已知子类：
		BufferedReader, CharArrayReader, FilterReader, InputStreamReader, PipedReader, StringReader 
 */
public class FileReaderDemo {
	@Test
	public void test(){
		readerChars();
	}
	public void readerChar(){
		FileReader fr = null;
		 String root=System.getProperty("user.dir");
		try {
			fr = new FileReader(new File(root+File.separator+"filetest/readFileTest.txt"));
			//记录读取单个字符
			int num = 0;
			while((num =fr.read())!=-1)
				System.out.print((char)num);
		} catch (Exception  e) {
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void readerChars(){
		FileReader fr = null;
		String root=System.getProperty("user.dir");
		try {
			fr = new FileReader(new File(root+File.separator+"filetest/readFileTest.txt"));
			//定义一个字符 容器数组 ，存储读取后的字符
			char[] chs=new char[100];
			//记录读取单个字符
			int num = 0;
			while((num =fr.read(chs))!=-1){
				//最好不要换行，否则超过容器后会自动换行
				System.out.print(new String(chs,0,num));
			}
		} catch (Exception  e) {
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
