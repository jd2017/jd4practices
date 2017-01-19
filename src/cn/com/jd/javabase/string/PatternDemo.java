package cn.com.jd.javabase.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {
	//1,邮箱、手机号：匹配
		public static void  telMehod(){
			String tel="13234567890";
			String regex="[1-9][387]\\d{9}";
			boolean b=tel.matches(regex);
			sop(b);
			// 邮件地址的校验：
			String mail="abcde23sf@sina.com";
			//邮箱准确校验：
			String reg="[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";
			//reg="\\w+@\\w+(\\.\\w+)+"; 不准确的校验；
			System.out.println(mail.matches(reg));	
		}
		//str=c:\\abc\\a.txt 按照\\ 切割
		public static void method_1(){
			String str="c:\\abc\\a.txt";
			String regex="\\\\";
			String strs[]=str.split(regex);
			for(String str1:strs){
				sop(str1);
			}
		}
		//str="absaasdfjfjjsdfdsllsdfkksdf"  按照叠词切割，封装成组。
		public static void method_2(){
			String str="absaaasdfjfjjskkabcde";
			String regex="(.)\\1+";
			String strs[]=str.split(regex);
			for(String str1:strs){
				sop(str1);
			}
		}
		public static void method_Replace(){
			String str="asd1234fk12345lj3214435lkj435656kll";
			//连续五个数字替换XX
			String regex="\\d{5,}";
			str=str.replaceAll(regex, "XX");
			sop(str);

			String str1="abEEEDDDljfDDDljlfdddd";
			//叠词替换成**
			String regex1="(.)\\1+";
			str1=str1.replaceAll(regex1, "**");
			sop(str1);
			
			//重叠字母替换成 单字符。  通过符号获取 组方式：$
			String str2="abQQljDDDDljlfdEE";
			String regex2="(.)\\1+";
			str2=str2.replaceAll(regex2, "$1");
			sop(str2);
		
			String str3="我要要要.......学学习..哈哈呵呵呵..编程";
			String regex3="\\.+";
			str3=str3.replaceAll(regex3,"");
			String regex4="(.)\\1+";
			str3=str3.replaceAll(regex4,"$1");
			sop(str3);
		}
		/*
		 *  1，匹配： string matches() 方法。
		 * 	2，切割：String  split();
		 * 	3, 替换：String replaceAll();
		 *	4,获取：将字符串中的符号规则的子串取出。
			 * 操作步骤：
			 * 1，将正则表达式封装成对象。
			 * 2，将正则对象和要操作的字符串相关联。
			 * 3，管理后获取匹配引擎。
			 * 4，通过引擎将符合规则的子串进行操作，比如取出。
		*/
		public static void method_Get(){
			String str="hell world ,Hard work forever,xiao bai";
			String regex="\\b\\w{4}\\b";
				// 将给定的正则表达式编译到模式中。
			Pattern p=Pattern.compile(regex);
				//创建匹配给定输入与此模式的匹配器。
			Matcher m=p.matcher(str);
			while(m.find()){
				sop(m.group());
				sop(m.start()+"..."+m.end());
			}
		}
		public static void isSort(){
			String str="192.168.0.1 102.23.59.1  5.34.12.6  10.34.12.6 20.28.103.1";
			//在字符串前加 00
			str=str.replaceAll("(\\d+)","00$1" );
			sop(str);
			str=str.replaceAll("0*(\\d{3})", "$1");
			sop(str);
			//str=str.replaceAll("0*(\\d+)","$1" );
			String[] strs=str.split(" +");
			//Arrays.sort(strs);
			TreeSet<String> ts=new TreeSet<String>();
			for(String s:strs){
				sop(s);
				ts.add(s);		
			}
			for(String s1:ts){
				s1=s1.replaceAll("0*(\\d+)","$1" );
				sop(s1);
			}
		}
		public static void findURL() throws IOException{
			URL url=new URL("http://blog.sina.com.cn/s/blog_515617e60101e151.html");
			URLConnection conn=url.openConnection();
			BufferedReader bufIn=
				new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			//BufferedReader bufr=new BufferedReader(new FileReader("D:\\Workspaces\\MyEclipse 8.5\\JavaTest\\email.txt"));
			String mailreg="\\w+@\\w+(\\.\\w)+";
			Pattern p=Pattern.compile(mailreg);
			
			String line=null;
			while((line=bufIn.readLine())!=null)
			{
				Matcher m=p.matcher(line);
				while(m.find()){
					System.out.println(m.group());
				}
			}
		}
		public static <T> void sop(T t){
			System.out.println(t);
		}
		public static void main(String[] args) throws IOException {
			method_Replace();
		}
}
