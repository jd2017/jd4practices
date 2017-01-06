package cn.com.jd.javabase.tcp2udp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP4ThreadDemo {
	
	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(10002);
			while(true){
				Socket s=ss.accept();
				//多线程客户端循环 建立
				new Thread(new UserLogin(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static  void clientImg(String[] args){
		//1，防止传入多个参数
		if(args.length!=1){
			System.out.println("请选择一个 jpg 格式图片");
			return;
		}
		//2，封装文件路径及文件名
		File file=new File(args[0]);
		if(!(file.exists()&&file.isFile())){
			System.out.println("该文件有问题，要么不存在，要不是文件");
			return ;
		}
		if(!(file.getName().endsWith(".jpg"))){
			System.out.println("图片格式不对，请选择 .jpg 格式");
			return ;
		}
		if(file.length()>1024*1024*5){
			System.out.println("文件过大，没安好心");
			return;
		}
		
		Socket s;
		try {
			s = new Socket("127.0.0.1",10007);
			//读取上传文件。
			FileInputStream fis=new FileInputStream(file);
			//发送数据流
			OutputStream out=s.getOutputStream();
			byte[] buf=new byte[1024];
			int num=0;
			while((num=fis.read(buf))!=-1)
			{
				out.write(buf, 0, num);
			}
			s.shutdownOutput();
			
			//获取服务端  上传信息
			InputStream in=s.getInputStream();
			byte[] buf1=new byte[1024];
			System.out.println(new String(buf1,0,in.read(buf1)));
			in.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loginTest(){
		//建立客户端 服务
			Socket s;
			try {
				s = new Socket("127.0.0.1",10002);
				//键盘录入：
				BufferedReader  bufr=new BufferedReader(new InputStreamReader(System.in));
				//将数据封装 ，发送客户端服务信息。
				PrintWriter out=new PrintWriter(s.getOutputStream(),true);
				
				//读取服务端 信息
				BufferedReader bufSocket=
						new BufferedReader(new InputStreamReader(s.getInputStream()));
				for(int i=0;i<3;i++)
				{
					//读取键盘录入
					String name=bufr.readLine();
					if(name==null)
						break;
					//发送客户端信息
					out.println(name);
					//读取服务端信息
					String info=bufSocket.readLine();
					
					if(info.contains("欢迎")){
						System.out.println(info);
						break;
					}else{
						System.out.println(info);
					}
				}
				bufr.close();
				s.close();		 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
/*------------------------------------------------------------------------
 * 为了让 多个客户端同时 并发访问服务端。
 * 那么服务端 最好将每个客户端封装 到一个单独的线程中，这样可以处理多个客户端请求。
 * 
 * 如何定义线程。‘
 * 只要明确每个客户端 要在 服务端执行的代码即可，将改代码放入 run中。
 */
class PicServerThread implements Runnable{
	static String root = System.getProperty("user.dir");
	private Socket s;
	PicServerThread(Socket s){
		this.s=s;
	}
	public void run(){
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+s.getPort());
		int count=1;		//未定义成员变量，防止多线程共享数据。
		try {
			//获取 读取流对象
			InputStream in=s.getInputStream();
			
			//以 ip 来命名
			File file=new File(root+"/filetest/"+ip+"("+(count)+")"+".jpg");
			while(file.exists())		//多次判断是否存在文件。
				file=new File(root+"/filetest/"+ip+"("+(count++)+")"+".jpg");
			
			//定义 上传后的 文件 位置。
			FileOutputStream  fos=new FileOutputStream(file);
			byte[] buf=new byte[1024];
			int num=0;
			while((num=in.read(buf))!=-1)
			{
				fos.write(buf, 0, num);
			}
			OutputStream out=s.getOutputStream();
			out.write("上传 成功".getBytes());
			
			fos.close();
			out.close();
			s.close();
		} catch (Exception e) {
			throw new RuntimeException(ip+"上传失败");
		}
	}
}
/*
 * 客户端 通过键盘录入用户名。
 * 服务端对 这个用户进行校验。
 * 
 * 如果存在， 在服务端显示xxx ，已经登录。
 * 并在客户端，显示xxx，欢迎光临。
 * 
 * 如果该用户不存在，服务端显示xxx，尝试登陆。
 * 并在客户端显示xxx，该用户不存在。
 */
class UserLogin implements Runnable{
	String root = System.getProperty("user.dir");
	private Socket s;
	UserLogin(Socket s){
		this.s=s;
	}
	public void run(){
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+":"+s.getPort());
		try{
			//服务端校验三次
			for (int i = 0; i < 3; i++) {
				// 读取服务端的信息。
				BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));
				// 获取 客户端 姓名信息
				String name = bufr.readLine();
				if (name == null)
					break;
				
				// 读取本地 硬盘信息。
				BufferedReader in = new BufferedReader(new FileReader(root+"/login.txt"));
				String acount = null;
				
				// 回写 客户端信息。
				PrintWriter out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
			
				boolean flag = false; // 定义一个跳出循环的 标记，判断文件中信息注册信息的结果。
				while ((acount = in.readLine()) != null) {
					if (acount.equals(name)) {
						flag = true;
						break;
					}
				}
				if(flag){
					System.out.println(name+",已经登陆");
					out.println(name+",欢迎光临");
				}else {
					System.out.println(name + "正在尝试登陆");
					out.println(name+"用户不存在，请重新输入");
				}
			}
			s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
