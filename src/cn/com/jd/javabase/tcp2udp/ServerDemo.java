package cn.com.jd.javabase.tcp2udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class ServerDemo {
	
	public static void myIE() throws IOException{
		Socket s=new Socket("127.0.0.1",10009);
		//发送给服务器   信息
		PrintWriter out =
			new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
		out.println("GET /myWeb/index.html HTTP/1.1");
		out.println("Accept: */*");
		out.println("Accept-Language: zh-cn");
		out.println("Host: 127.0.0.1:8088");
		out.println("Connection: closed");
		out.println();
		
		//读取服务端信息
		BufferedReader bufr=
			new BufferedReader(new InputStreamReader(s.getInputStream()));
		String line=null;
		while((line=bufr.readLine())!=null)
		{
			System.out.println(line);
		}
		s.close();
	}
	public static void server() throws IOException {
		//服务器套接字等待请求通过网络传入。
		ServerSocket ss=new ServerSocket(10009);
		//获取客户端套接字（
		Socket s=ss.accept();
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+":"+s.getPort());
			
		//读取客户端信息
		InputStream in=s.getInputStream();
		byte[] buf=new byte[1024];
		int num=in.read(buf);
		System.out.println(new String(buf,0,num));
				
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		out.println("欢迎光临 my Server");
		
		s.close();
		ss.close();
	}
	/*
	类 URL
		代表一个统一资源定位符，它是指向互联网“资源”的指针。
		URL(String spec) 
	          根据 String 表示形式创建 URL 对象。 
		URL(String protocol, String host, int port, String file) 
	          根据指定 protocol、host、port 号和 file 创建 URL 对象。
		 String getFile() 
				  获取此 URL 的文件名。 
		 String getHost() 
				  获取此 URL 的主机名（如果适用）。 
		 String getPath() 
				  获取此 URL 的路径部分。 
		 int getPort() 
				  获取此 URL 的端口号。 
		 String getProtocol() 
				  获取此 URL 的协议名称。 
		 String getQuery() 
	          获取此 URL 的查询部分。 
		
		URLConnection openConnection() 
	          返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。 
		InputStream openStream() 
	          打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。 
	 

	类 URLConnection
		抽象类 URLConnection 是所有类的超类，它代表应用程序和 URL 之间的通信链接。
		InputStream getInputStream() 
	          返回从此打开的连接读取的输入流。 
		OutputStream getOutputStream() 
	          返回写入到此连接的输出流。 
		URL getURL() 
	          返回此 URLConnection 的 URL 字段的值。 
	*/
	public static void url() throws IOException {
		URL url=new URL("http://localhost:10009/myWeb/index.html");
		URLConnection  conn=url.openConnection();
		System.out.println(conn);
		
		InputStream in=conn.getInputStream();
		byte[] buf=new byte[1024];
		int num=in.read(buf);
		System.out.println(new String(buf,0,num));
	}
}
