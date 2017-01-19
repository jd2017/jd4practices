package cn.com.jd.javabase.Advance;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class MyClassLoader extends ClassLoader{		//变成类加载器。
	private String classDir;
	public MyClassLoader(){	}
	public MyClassLoader(String classDir){	//class 的目录，传过来目录
		this.classDir =classDir;
	}
	//对 class 文件 进行加密
	private static void cypher(InputStream ips,OutputStream ops) throws IOException
	{
		int b=-1;
		while((b=ips.read())!=-1){
			ops.write(b^0xff);		//异或 一个字节255
		}
	}
	//覆盖方法：
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("override");
		String classFileName = classDir + File.separator + name + ".class";
		try{
			FileInputStream fis=new FileInputStream(classFileName);
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			cypher(fis,bos);	//解密
			fis.close();
			byte[] bytes=bos.toByteArray();
			return defineClass(bytes, 0, bytes.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	public static void main(String[] args) throws Exception {
		String srcPath=args[0];
		String destDir=args[1];
		FileInputStream fis=new FileInputStream(srcPath);
		
		String destFileName=srcPath.substring(srcPath.lastIndexOf(File.separator)+1);
		String destPath=destDir+File.separator+destFileName;
		
		FileOutputStream fos=new FileOutputStream(destPath);
		cypher(fis,fos);
		fis.close();
		fos.close();
	}
}

