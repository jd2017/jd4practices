package cn.com.jd.javabase.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CopyFileDemo {
	public static void main(String[] args) {
		FileReader fr=null;
		FileWriter fw=null;
		try{
			String root=System.getProperty("user.dir");
			fr=new FileReader(root+File.separator+"filetest/readFileTest.txt");
			fw=new FileWriter(root+File.separator+"filetest/copy_ReadFileTest.txt");
			char[] chs=new char[1024];
			int len=0;
			while((len=fr.read(chs))!=-1){
				fw.write(chs, 0, len);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fr!=null)
					fr.close();
				if(fw!=null)
					fw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
