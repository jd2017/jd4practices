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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class MyWindowURL {
	private Frame fr;		//是带有标题和边框的顶层窗口。
	private Button bt;
	private TextField tf;
	private TextArea ta;
	
	private Dialog dg;	//加对话框
	private Button okbt;
	private Label	lb;	//封装文字
	 MyWindowURL(){
		init();
		myEvent();
	}
	public void init(){
		fr=new Frame("MyWindowURL");		
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
		String urlPath=tf.getText();//http://127.0.0.1:8088/myWeb/index.html
		
		URL url=new URL(urlPath);
		URLConnection  conn=url.openConnection();
		
		InputStream in=conn.getInputStream();
		byte[] buf=new byte[1024];
		int num=in.read(buf);
		
		ta.setText(new String(buf,0,num));
		
	}
	public static void main(String[] args) {
		new MyWindowURL();
	}
}
