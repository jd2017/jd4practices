package cn.com.jd.javabase.tcp2udp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//import org.junit.Test;

/*
	客户端：
	1，建立socket服务。指定要连接主机和端口。
	2，获取socket流中的输出流。将数据写到该流中。通过网络发送给服务端。
	3，获取socket流中的输入流，将服务端反馈的数据获取到，并打印。
	4，关闭客户端资源。
	
	服务端:
	1，建立服务端的socket服务。ServerSocket(10000);
		并监听一个端口。
	2，获取连接过来的客户端对象。
		通过ServerSokcet的 accept方法。没有连接就会等，所以这个方法阻塞式的。
	3，客户端如果发过来数据，那么服务端要使用对应的客户端对象，并获取到该客户端对象的读取流来读取发过来的数据。
		并打印在控制台。
	4，关闭服务端。（可选）
*/
public class TCPDemo {
	
	public static void main(String[] args) {
		
		sendTcp1();
	}
	public static void sendTcp(){
		try {
			Socket socket = new Socket("127.0.0.1",10001);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String len = null;
			while((len = br.readLine())!=null){
				OutputStream out = socket.getOutputStream();
				out.write(len.getBytes());
			}
			InputStream in = socket.getInputStream();
			byte[] b = new byte[1024];
			int num = in.read(b);
			Sop(new String(b,0,num));
			in.close();
			br.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//接受数据TCP
	public static  void receviceTcp(){
		try {
			ServerSocket ss = new ServerSocket();
			ss.bind(new InetSocketAddress(10001));
			Socket socket = ss.accept();
			String IP  = socket.getInetAddress().getHostAddress();
			int port = socket.getPort();
			System.out.println("ip="+IP+"port:"+port);
			InputStream in = socket.getInputStream();
			byte[] buf = new byte[1024];
			int num =-1;
			while((num=in.read(buf))!=-1){
				Sop(new String(buf,0,num));
			}
			//回写数据
			OutputStream out = socket.getOutputStream();
			out.write("Recevice already know".getBytes());
			
			//关闭连接
			out.close();
			in.close();
			socket.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void receviceTcp1(){
		
		ServerSocket ss;
		try {
			ss = new ServerSocket(10002);
			Socket s=ss.accept();
			String ip=s.getInetAddress().getHostAddress();
			Sop(ip+"......port:"+s.getPort());
		
			//读取Socket 流中的数据。
			BufferedReader br=
				new BufferedReader(new InputStreamReader(s.getInputStream()));
			//目的，输出Socket流。变成大写发送客户端。
			BufferedWriter bw=
				new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			String len=null;
			while((len=br.readLine())!=null)		//出现阻塞
			{
				Sop(len);
				bw.write(len.toUpperCase());
				bw.newLine();		//防止客户端堵塞。
				bw.flush();
			}
			s.close();
			ss.close();
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendTcp1(){
		
		Socket s;
		try {
			s = new Socket("127.0.0.1",10002);
			//定义一个读取键盘数据
			BufferedReader bufIn=
				new BufferedReader(new InputStreamReader(System.in));
			//定义目的，将 数据写入 Socket 流 发送服务端。
			BufferedWriter bufOut=
				new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			//定义一个 服务端读取流，读取服务端大写数据。
			BufferedReader bufSocket=
				new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			String len=null;
			while((len=bufIn.readLine())!=null)		//堵塞式方法
			{
				if("over".equals(len))
					break;
				bufOut.write(len);
				bufOut.newLine();					//防止  服务端 堵塞。
				bufOut.flush();
				
				String line=bufSocket.readLine();
				Sop(line);
			}
			bufIn.close();
			bufOut.close();
			bufSocket.close();
			s.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static <T>  void Sop(T t){
		System.out.println(t);
	}
}

