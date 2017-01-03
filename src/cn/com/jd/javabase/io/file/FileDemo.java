package cn.com.jd.javabase.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.junit.Test;

public class FileDemo {
	String path = System.getProperty("user.dir")+File.separator+"filetest"+File.separator;
	@Test
	public void test(){
		print_file();
	}
	//封装成file对象
	public void file(){
		//方法一：将a.txt 封装成file对象。可以将已有的和未出现的文件或者文件封装成对象。
				File f1=new File(path+"a.txt");
				//方法二：
				File f2=new File(path,"b.txt");
				//方法三：
				File ff=new File(path);
				File f3=new File(ff,"c.txt");
				sop("f1="+f1);
				sop("f2="+f2);
				sop("f3="+f3);
				//跨平台的分割符
				File f4=new File(path+File.separator+"d.txt");
				sop(f4);
				//f1.createNewFile();
				sop(f1.renameTo(f2));
	}
	public  void delete_file() {
		File f=new File(path,"a.txt");
		try {
			if(!f.exists()){
				f.createNewFile();
				sop("creatNewFile");
			}
			sop(f.isDirectory());
			sop(f.isFile());
			sop(f.canExecute());
			File f3=new File("aa\\bb\\cc\\a.txt");
			sop(f3.mkdirs());
			sop(f.lastModified());
			sop(f.length());
			//退出时 删除
			f.deleteOnExit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  void print_file() {

		File[] fs=File.listRoots();
		for(File f1:fs){
			sop(f1);
		}
		//filetest 下文件
		File f1=new File(path);
		//调用list方法，file对象必须封装成一个目录，该目录必须存在。
		String[] fs1=f1.list();	
		for(String s:fs1)
		{
			sop(s);
		}
		//文件夹 下的 文件 过滤！！
		File dir=new File(path);
		String[] names=dir.list(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {			
				return name.endsWith(".txt");		//重写匿名内部类的方法。
			}
		});
		for(String name:names)
		{
			sop(name);
		}
		//打印文件夹下的所有文件名、路径及大小
		File[] files=dir.listFiles();
		for(File f:files)
		{
			sop("F="+f+"--------Name="+f.getName()+"------Length="+f.length());
		}
	}
	public <T> void  sop(T t){
		System.out.println(t);
	}
}
