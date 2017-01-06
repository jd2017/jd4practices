package cn.com.jd.javabase.tcp2udp;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyWindowSocket {
	private Frame fr;		//是带有标题和边框的顶层窗口。
	private Button bt;
	private TextField tf;
	private TextArea ta;
	
	private Dialog dg;	//加对话框
	private Button okbt;
	private Label	lb;	//封装文字
	MyWindowSocket(){
		init();
		myEvent();
	}
	public void init(){
		fr=new Frame("MyWindowSocket");		
		fr.setBounds(200, 100, 600, 600);		//移动组件并调整其大小。
		fr.setLayout(new FlowLayout());			//设置此容器的布局管理器。
		
		bt=new Button("转到");
		tf=new TextField(60);					// 构造具有指定列数的新空文本字段。
		ta=new TextArea(30,70);
		
		fr.add(tf);
		fr.add(bt);
		fr.add(ta);
		
		//构造一个最初不可见的 Dialog，它带有指定的所有者 Frame、标题和模式。
		dg=new Dialog(fr,"dialog_self",true);
		dg.setBounds(300, 200, 250, 200);
		dg.setLayout(new FlowLayout());
		
		lb=new Label();
		okbt=new Button("确定");
		
		dg.add(lb);
		dg.add(okbt);
		
		fr.setVisible(true);
	}
	public void myEvent(){
		fr.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//按钮 监听器。
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					showDir();					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		//文本框 加 键盘 监听器
		tf.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					try {
						showDir();					
					} catch (Exception e2) {
						// TODO: handle exception
					}
			}
		});
		
		//对 Dialog 加入监听器。
		dg.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dg.setVisible(false);
			}
		});
		okbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dg.setVisible(false);
			}
		});
		okbt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					dg.setVisible(false);
			}
		});
	}
	
	//提取  获取 文件 的方法。
	public  void showDir() throws UnknownHostException, IOException {
		
		ta.setText("");
		String str=tf.getText();//http://127.0.0.1:8088/myWeb/index.html
		int index1=str.indexOf("//")+2;
		int index2=str.indexOf("/", index1);
		
		String host=str.substring(index1,index2);
		String[] hosts=host.split(":");
		String url=hosts[0];
		int  port=Integer.parseInt(hosts[1]);
		
		String  file=str.substring(index2);
		
		Socket s=new Socket(url,port);
		//发送给服务器   信息
		PrintWriter out =
			new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
		out.println("GET "+file+" HTTP/1.1");
		out.println("Accept: */*");
		out.println("Accept-Language: zh-cn");
		out.println("Host: "+url+":8088");
		out.println("Connection: closed");
		
		out.println();
		
		//读取服务端信息
		BufferedReader bufr=
			new BufferedReader(new InputStreamReader(s.getInputStream()));
		String line=null;
		while((line=bufr.readLine())!=null)
		{
			ta.append(line+"\r\n");
		}
		s.close();	
	}
	public static void main(String[] args) {
		new MyWindowSocket();
	}
}
