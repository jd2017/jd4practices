package cn.com.jd.javabase.tcp2udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPChatDemo {
	public static void main(String[] args) throws SocketException {
		DatagramSocket send=new DatagramSocket(12345);
		new Thread(new SendDemo(send)).start();
	}
}
class SendDemo implements Runnable{
	private DatagramSocket ds;
	SendDemo(DatagramSocket ds){
		this.ds=ds;
	}
	public void run(){
		BufferedReader reader=
			new BufferedReader(new InputStreamReader(System.in));
		String len=null;	
		try{
			while((len=reader.readLine())!=null){
				if("886".equals(len))
					break;
				byte[] buff=len.getBytes();
				DatagramPacket dp=new DatagramPacket(buff,0,buff.length,InetAddress.getByName("10.23.7.187"),8888);
				ds.send(dp);
			}
			reader.close();
			ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
class ReceviceDemo implements Runnable{
	private DatagramSocket ds;
	ReceviceDemo(DatagramSocket ds){
		this.ds=ds;
	}
	public void run(){
		try{
			while(true){
				byte[] buff=new byte[1024];
				DatagramPacket dp=new DatagramPacket(buff,0,buff.length);
				ds.receive(dp);	//阻塞式方法。			
				String ip=dp.getAddress().getHostName();
				String data=new String(dp.getData(),0,dp.getLength());
				int port=dp.getPort();
				System.out.println(ip+":"+port+"-------"+data);
				if("over".equals(data))
					break;
			}
			ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
