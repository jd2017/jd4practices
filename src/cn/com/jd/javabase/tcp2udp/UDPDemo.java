package cn.com.jd.javabase.tcp2udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/*用udp传输方式，将 一段文字数据发送出去。
 * 发送端：
 * 1，建立udpSocket 服务。
 * 2，提供数据，并将数据封装到数据包中。
 * 3，通过socket 服务的发送功能，将数据包发送出去。
 * 4，关闭资源。
 * 
 * 接受端：
 * 1，定义UdpSocket服务，通常会监听一个端口。
 * 2，定义一个数据包，存储接受到的字节数据。提出不同的信息。
 * 3，通过socket服务的receive 方法将数据存入 已定义好的数据包中。
 * 4，通过数据包对象的特有功能，将这些不同的数据取出，打印到控制台。
 * 5，关闭资源。
 */
public class UDPDemo {
	//Udp发送端：
	public static void sendMethod() throws Exception {
		//1,建立Udp服务，通过DatagramSocket 对象。
		DatagramSocket ds=new DatagramSocket(8888);
		byte[] buf="udp information".getBytes();
		//2,确定数据，并封装成数据包，DatagramPacket(byte[] buf,int length,InetAddress address,int port) 
		DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),10000);
		//3,从此套接字发送数据报包
		ds.send(dp);
		//4,关闭资源
		ds.close();
	}
	//Udp:接收端
	public static void receiveMethod() throws Exception {
		//1,创建udp服务，接受端口
		DatagramSocket dataScokect=new DatagramSocket(10000);
		//2,定义一个接受数据缓冲区，存储字节数据。
		byte[] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf,buf.length);
		//3,接受数据。阻塞式方法。
		dataScokect.receive(dp);
		//4,通过数据包的方式  获取其中的数据。
		String ip=dp.getAddress().getHostAddress();
		String data=new String(dp.getData(),0,dp.getLength());
		int port=dp.getPort();
		System.out.println(ip+"::"+data+"::"+port);
		
		//5,关闭资源
		dataScokect.close();		
	}
	public static void main(String[] args) throws Exception {
		//返回本地主机。 
		InetAddress inet=InetAddress.getLocalHost();
		//返回 IP 地址字符串（以文本表现形式）。
		String address=inet.getHostAddress();
		//获取此 IP 地址的主机名。
		String name=inet.getHostName();
		System.out.println("adress="+address+"name="+name);
		//在给定主机名的情况下确定主机的 IP 地址。
		InetAddress address2=InetAddress.getByName("127.0.0.1");
		System.out.println(address2.getHostAddress()+"==="+address2.getHostName());
		 
	}
}
