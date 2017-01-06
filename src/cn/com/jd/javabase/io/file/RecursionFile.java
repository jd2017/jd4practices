package cn.com.jd.javabase.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author jd.bai
 * @date 2016年12月30日
 * @time 下午5:49:57
 * 也就是列出指定目录下所有内容。
	因为目录中还有目录，只要用同一个列出目录功能的函数完成即可。
	函数自身调用函数。
	
	递归要注意：
	1，限制条件。
	2，注意递归的次数，尽量避免内存溢出。
 */
public class RecursionFile {
	String path = System.getProperty("user.dir");
	@Test
	public void test(){
		File file = new File(path);
		showFiles(file,0);
//		Sop(files.length);
	}
	//十进制转为二进制
	public  void toBin(int num){
		if(num>1)
			toBin(num/2);
		Sop(num%2);
	}
	//输入 123456 打印出 654321
	public  void toArray(int num){		
		Sop(num%10);
		if(num>10){
			num=num/10;
			toArray(num);
		}
	}
	//累加结果！！
	public  int getSum(int num){		
		if(num==1)
			return 1;
		return num+getSum(num-1);
	}
	//阶乘  累加
	public  int Num(int num)
	{
		if(num==1)
			return 1;
		return num*Num(num-1);
	}
	//
	public  int add(int num,int dest,int sum)
	{
		if(num==dest)
			 return sum+=Num(num);
		else{
			sum+=Num(num);
			return add(num+1,dest,sum);
		}
	}
	//文件名字目录前 加入|-
	public  void showFiles(File dir,int level)
	{
		Sop(getLevel(level)+dir.getName());
		level++;
		// 返回一个抽象路径名  文件 数组.
		File[] files=dir.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory())
				showFiles(files[i],level);
			else{
				try {
					FileInputStream fis = new FileInputStream(files[i]);
					//返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数。
					int in=fis.available();
					Sop(files[i]+"files 文件的大小="+in);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	public  String getLevel(int level)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("|--");
		for(int i=0;i<level;i++)
		{
			sb.insert(0, "|  ");
		}		
		return sb.toString();
	}
	//删除 目录 与目录中的 文件	从里面向 外删除。
	public  void removeFile(File dir){
		
		File[] files=dir.listFiles();
		for(int i=0;i<files.length;i++){
			if(!files[i].isHidden()&&files[i].isDirectory()){
				removeFile(files[i]);
			}else
				Sop(files[i].toString()+"---file remove---"+files[i].delete());
			}
			Sop(dir.getName()+"======dir===="+dir.delete());
	}
	public <T> void Sop(T t){
		System.out.println(t);
	}
}
