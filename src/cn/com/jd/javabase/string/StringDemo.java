package cn.com.jd.javabase.string;

public class StringDemo {
	public static void method_get(){
		String str="abcgcgdefcg";
		
		//字符串的长度。
		sop(str.length());
		//1.2 根据位置获取位置上某个字符。
		sop(str.charAt(3));
		//1.3 根据字符获取该字符串中的位置。
		sop(str.indexOf('c'));
		sop(str.indexOf("cd",4));
		//字符在此字符串中最后一次出现处的索引。
		sop(str.lastIndexOf('d'));
	}
	public static void  method_is(){
		String str="Stirng.java";
		//2.2 字符是否有内容。
		sop(str.isEmpty());
		//2.1 字符是否包含字符串。
		sop(str.contains("tt"));
		//2.3 字符串是否以指定内容开头。
		sop(str.startsWith("Str"));
		//2.4 返回字符串是否是以指定内容结尾。
		sop(str.endsWith(".java"));
		//2.5 判断字符串内容是否相同，
		sop(str.equals("abc"));
		//2.6 判断内容是否相同，并且忽略大小写。
		sop(str.equalsIgnoreCase("str"));
	}
	public static void method_reverse(){
		
		//3.1 将字符数组转成字符串。
		char[] chs={'1','a','b','c'};
		sop(new String(chs));
		sop(String.copyValueOf(chs));
		//3.2 将字符串转换成字符数组。
		String str="abcde";
		char[] chs1=str.toCharArray();
		for(char ch:chs1){sop(ch);}
		//3.3 将此 String 编码为 byte 序列。
		byte[] bs=str.getBytes();
		for(byte b:bs){sop(b);}
		
		String str2="金abc";
		char[] chs2=str.toCharArray();		
		for(char c:chs){
			int i=c;
			sop(i);
		}
		//3.5 将基本数据类型转成字符串。
		sop(String.valueOf(true));
		sop(String.valueOf(6));		
	}
	public static void method_replace(){
		String str="ab,cdf";
		//替换
		sop(str.replace('a', 'b'));
		sop(str.replace("ab","ccc"));
		//切割 
		String[] sts=str.split("b");
		for(String st:sts){sop(st);}
		//子串
		str.substring(2);//不能超出角标
		str.substring(2,5);//包含头不包含尾！！
		//转换
		sop(str.toUpperCase());
		str.trim();
		String str2="abcd";	
		sop(str.compareTo(str2));	
	}
	//StringBuffer
	public static void method_c(){
		StringBuffer sb=new StringBuffer();

		sb=sb.append(3).append(true).append("abc");
		sop(sb.toString());
		sb.insert(1, "数据");
		sop(sb);
	}
	public static void method_d(){
		StringBuffer sb=new StringBuffer("abcde");
		//删除角标2-4
		sb.delete(2, 3);
		sop(sb);
		//删除一个时
		sb.deleteCharAt(3);	
		//清空缓冲区；
		sb.delete(0, sb.length());
		sop(sb.toString());
	}
	public static void method_r(){
		StringBuffer sb=new StringBuffer("abcde");
		String s=sb.substring(1,3);	//包头，不包含尾
		sop(s);
		
		int i=sb.indexOf("bc");
		sop(i);

	}
	public static void method_u(){
		StringBuffer sb=new StringBuffer("abcdef");
		sb.reverse();
		sop(sb);
		
		sb.replace(0,70,"hello");	//替换时头不能角标越界，尾巴可以不受约束，类似于重载
		sop(sb);
		
		sop(sb.charAt(1));
	}
	public static void sop(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		 method_replace();
	}
}
