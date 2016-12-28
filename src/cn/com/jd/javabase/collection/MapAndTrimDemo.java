package cn.com.jd.javabase.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class MapAndTrimDemo {
	@Test
	public void test(){
		String str =" aa1a11b22b测试00defccc ";
		myTrim(str);
		byte[] bs = str.getBytes();
		for(byte b:bs)
			System.out.println(b);
		chArray(str);
	}
	//1，去两端的空格操作！！
	public  String myTrim(String str){
		int start=0,end=str.length()-1;
		while(start<=end&&str.charAt(start)==' '){
			start++;	
		}
		while(start<=end&&str.charAt(end)==' '){
			end--;
		}
		return str.substring(start,end+1);
	}
	/**
	 * 练习：
	"abdcdfsansfjlsjlg" 获取该字符串中的字母出现的次数。
	 希望打印结果：a(2)b(1)c(1)...
	 * 分析：
		字母和次数之间 都有映射关系。
		
		当发现有映射关系时， 可以选择map集合。
		因为map 集合存放的就是 映射关系。
		数据间有 映射 关系时， 就要先想map 集合。
		
		思路：
		  1，将字符转为数组。因为要对每个字符进行操作。
		  2，定义一个map 集合，打印的字母有序，所以要用TreeMap集合。
		  3，遍历字符数组。
			将每个字母作为键去查 map 集合。
			如果返回 null，将该字母和 1 存入map集合。
			如果返回不是null， 说明该字母在map已存在，并有对应次数。
			那么就获取该次数 并进行自增，然后将该字母和自增的次数，存入map，
				覆盖原来键所对应的值。
		  4，将map集合中的数据变成指定的字符串形式返回。
	 */
	public  String chArray(String str){
		int count=0;				//定义字符出现的次数。
		char[] chs=str.toCharArray();
		TreeMap<Character,Integer> tm=new TreeMap<Character,Integer>();
		for(int i=0;i<chs.length;i++)
		{	
			if(!(chs[i]>='a'&&chs[i]<='z'||chs[i]>='A'&&chs[i]<='Z'||chs[i]>='0'&&chs[i]<='9'))
				continue;
			
				Integer value=tm.get(chs[i]);
			/*	if(value==null)
				{
					count=1;
				}else{
					count=value+1;
				}*/
				if(value!=null)
					count=value;
				count++;
				
				tm.put(chs[i], count);
				count=0;
		}
			StringBuilder sb =new StringBuilder();
			Set<Map.Entry<Character,Integer>> set=tm.entrySet();
					//Iterator<Entry<K, V>> it=set.iterator();
			for(Iterator<Entry<Character,Integer>> it=set.iterator();it.hasNext();)
			{
				Entry<Character,Integer> entry =it.next();
				Character key=entry.getKey();
				Integer value=entry.getValue();
				sb.append(key+"("+value+")");
			}
				System.out.println(sb.toString());
			return sb.toString();
	}
}
