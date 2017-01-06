package cn.com.jd.javabase.tcp2udp;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import cn.com.jd.javabase.tcp2udp.ServerDemo;

import java.util.*;

public class TCPTest {
	public static void main(String[] args) throws IOException {
		new ServerDemo().server();
	}
}
