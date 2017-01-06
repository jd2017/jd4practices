package cn.com.jd.javabase.tcp2udp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP4File {
	static String root = System.getProperty("user.dir");
	public static void main(String[] args) {
		sendTcp4Pic();
		
	}
	public static void sendTCPFile(){
		//定义一个客服端
		Socket s;
		try {
			s = new Socket("127.0.0.1",10001);
			// 将数据封装，读取。
			BufferedReader br=
					new BufferedReader(new FileReader(root+"/filetest/readFileTest.txt"));
			// 发送数据到 服务端。刷新输出缓冲区
			PrintWriter pw=
					new PrintWriter(s.getOutputStream(),true);
			String len=null;
			while((len=br.readLine())!=null){
				pw.write(len);
				pw.println();
				pw.flush();		//输出数据
			}
			// 结束标记，禁止此套接字输出流。
			s.shutdownOutput();
			BufferedReader brSocket=
					new BufferedReader(new InputStreamReader(s.getInputStream()));
			String length=brSocket.readLine();
			System.out.println(length);
			
			br.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void receiveTCPFile(){
		try {
			ServerSocket ss=new ServerSocket(10001);
			Socket s=ss.accept();
			String ip=s.getInetAddress().getHostAddress();
			System.out.println(ip+":"+s.getPort());
			
			//读取 客户端 数据
			BufferedReader br=
				new BufferedReader(new InputStreamReader(s.getInputStream()));
			//写入 客户端数据。 刷新输出缓冲区
			PrintWriter pw;
			 pw =new PrintWriter(new FileWriter(root+"/filetest/server.txt"),true);
			String len=null;
			while((len=br.readLine())!=null){
				pw.write(len);
				pw.println();
				pw.flush();
			}
			PrintWriter pwSocket=
					new PrintWriter(s.getOutputStream(),true);
			pwSocket.println("上传成功");
			s.close();
			ss.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void sendTcp4Pic(){
		Socket socket;
		try {
			socket = new Socket("10.23.7.187",10007);
			//读取上传文件。
			FileInputStream fis=new FileInputStream(root+"/filetest/img.jpeg");
			//发送数据流
			OutputStream out=socket.getOutputStream();
			byte[] buf=new byte[1024];
			int num=0;
			while((num=fis.read(buf))!=-1){
				out.write(buf, 0, num);
			}
			socket.shutdownOutput();
			//获取服务端  上传信息
			InputStream in=socket.getInputStream();
			byte[] buf1=new byte[1024];
			System.out.println(new String(buf1,0,in.read(buf1)));
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void receveTcp4Pic(){
		ServerSocket ss;
		try {
			ss = new ServerSocket(10001);
			Socket socket=ss.accept();
			String ip=socket.getInetAddress().getHostAddress();
			System.out.println(ip+socket.getPort());
			//获取 读取流对象
			InputStream in=socket.getInputStream();
			//定义 上传后的 文件 位置。
			FileOutputStream  fos=new FileOutputStream(root+"/filetest/server_img.jpeg");
			byte[] buf=new byte[1024];
			int num=0;
			while((num=in.read(buf))!=-1)
			{
				fos.write(buf, 0, num);
			}
			OutputStream out=socket.getOutputStream();
			out.write("上传 成功".getBytes());
			
			fos.close();
			out.close();
			socket.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
