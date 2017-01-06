	网络模型：
	OSI 参考模型
	TCP/IP参考模型
	
	网络通讯要素
	IP地址
	端口
	协议
	
	定义通信规则。这个通讯规则称为协议。
	国际组织定义了通用协议	TCP/IP.
	
		OSI			TCP/IP 参考模型
	数	应用层			应用层
	据	表示层			
		会话层			
	封	传输层			传输层
	包	网络层			网际层
		数据链路层															
		物理层			主机至网络
	
	Ip地址：
	网络中设备的标识。
	不易记忆，可用主机名
	本地回环地址：127.0.0.1 主机名：localhost
	
	端口号：
	用于标识进程的逻辑地址，不同进程的标识。
	有效端口：0~65535，其中0~1024系统使用和保留端口号。
	
	传输协议：
	通讯的规则。
	常见协议：TCP，UDP
	
	UDP:
	1，将数据及源和目的封装成数据包中，不需要建立连接。
	2，每个数据报的大小在限制在 64k。
	3，因无连接，是不可靠协议。
	4，不需要建立连接，速度快。
	例如：步话机，凌波共享桌面，飞秋,短信。
	
	TCP：
	1，建立连接，形成传输数据的通道。
	2，在连接中进行大数据传输。
	3，通过三次握手完成连接，是可靠协议。
	4，必须建立连接，效率会稍低。
	
	例如：打电话，迅雷下载。
	
	Socket:
	就是为网络服务提供的一种机制。
	通信的两端都是Socket。
	网络通信其实就是Socket间的通信
	数据在两个Socket间通信IO传输。
	例如：Socket370 针数。
	
	UDP传输：
	DatagramSocket 和 DatagramPacket
	
	类 InetAddress:
		此类表示互联网协议 (IP) 地址。
		String getHostAddress() 
	          返回 IP 地址字符串（以文本表现形式）。 
		String getHostName() 
	          获取此 IP 地址的主机名。 
		static InetAddress getByName(String host) 
	          在给定主机名的情况下确定主机的 IP 地址。 
	类 InetSocketAddress
		此类实现 IP 套接字地址（IP 地址 + 端口号）.
	
	DatagramSocket:
		此类表示用来发送和接收数据报包的套接字.
	DatagramSocket() 
	  构造数据报套接字并将其绑定到本地主机上任何可用的端口。
	DatagramSocket(int port) 
	          创建数据报套接字并将其绑定到本地主机上的指定端口。
	
		void receive(DatagramPacket p) 
	          从此套接字接收数据报包。 
		void send(DatagramPacket p) 
	          从此套接字发送数据报包。 
	
	DatagramPacket:
		此类表示数据报包。 数据报包用来实现无连接包投递服务
	DatagramPacket(byte[] buf, int length) 
	  构造 DatagramPacket，用来接收长度为 length 的数据包。
	DatagramPacket(byte[] buf, int offset, int length, InetAddress address, int port) 
	  构造数据报包，用来将长度为 length 偏移量为 offset 的包发送到指定主机上的指定端口号。
	
		InetAddress getAddress() 
	          返回某台机器的 IP 地址，此数据报将要发往该机器或者是从该机器接收到的。 
		int getPort() 
	          返回某台远程主机的端口号，此数据报将要发往该主机或者是从该主机接收到的。 
	
	TCP/IP:
	Socket 和 ServerSocket
	
	类 Socket
		此类实现客户端套接字（也可以就叫“套接字”）。套接字是两台机器间通信的端点。
	  Socket(String host, int port) 
	          创建一个流套接字并将其连接到指定主机上的指定端口号。
	  InetAddress getInetAddress() 
	          返回套接字连接的地址。 
	  InputStream getInputStream() 
	          返回此套接字的输入流。 
	  OutputStream getOutputStream() 
	          返回此套接字的输出流。 
	  void connect(SocketAddress endpoint) 
	          将此套接字连接到服务器。 
	
	类 ServerSocket
		类实现服务器套接字。服务器套接字等待请求通过网络传入。
		ServerSocket(int port) 
	          创建绑定到特定端口的服务器套接字。
		ServerSocket(int port, int backlog) 
	          利用指定的 backlog 创建服务器套接字并将其绑定到指定的本地端口号。
		  backlog - 队列的最大长度。
		Socket accept() 
	          侦听并接受到此套接字的连接。 