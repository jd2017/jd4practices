package cn.com.jd.javabase.string;
/**
 * @author jd.bai
 * @date 2017年1月6日
 * @time 下午5:04:36
	1,模拟一个trim方法，去除字符串两端的空格。
	  思路：
	  1.1 判断字符串 第一个位置是否是空格，如果是继续向下判断，直到不是空格为止。结尾判断同理。
	  1.2 当开始和结尾都判断到不是空格时，就是要获取的字符串。
	
	2，将一个字符串进行反转。将字符串指定部分进行反转，例如："abcdefg",abfedcg;
	   思路：
	   1 曾经学过对数组的元素进行反转。
	   2 将字符变成数组，对数组反转。
	   3，将反转后的数组变成字符串。
	   4，只要将要反转的部分的开始和结束位置作为参数传递即可。
	
	3，获取一个字符串在另一个字符串中出现的次数。
		"abkkcdkkefkkskk"
	   思路：
	   1，定义一个计数器。
	   2，取kk第一次出现的位置。
	   3，从第一次出现的位置后剩余的字符串继续获取kk出现的位置。每获取一次就计数一次。
	   4，当获取不到时，计数完成。
	
	4，获取两个字符串中最大相同子串，第一个动作：将短的那个串进行长度一次递减的子串打印。
		"abcwerthellououodef"
		"cvhellobbnm"
	   思路：
	   1，将短的那个子串按照长度递减的方式获取到。
	   2，将没获取到的子串去长串中判断是否包含，
		如果包含，已经找到。
 */
public class StringPractice {
	//1，去空格操作！！
		public static String myTrim(String str){
		int start=0,end=str.length()-1;
			while(start<=end&&str.charAt(start)==' '){
				start++;	
			}
			while(start<=end&&str.charAt(end)==' '){
				end--;
			}
			return str.substring(start,end+1);
		}
		//2， 反转字符串操作；
		public static String reverse() {
			String str = "abcde";
			// 2.1	将字符串变为数组
			char[] chs = str.toCharArray();
			// 2.2 	反转数组
			arrReverse(chs);
			//2.1	将数组变为字符串
			return new String(chs);
		}
		//反转数组指针判断操作;
		public static void arrReverse(char[] arr) {
			//指针头、尾移动操作
			for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
				swap(arr, start, end);
			}
		}
		//翻转指定对象
		public static void swap(char[] arr, int x, int y) {
			char temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
		//方法二：反转部分字符串操作
		public static String reverse(String str, int x, int y) {

			char[] chs = str.toCharArray();
			// 反转指定部分数组
			arrReverse(chs, x, y);
			return new String(chs);
		}
		//反转指定部分数组。
		public static void arrReverse(char[] arr, int x, int y) {
			for (int start = x, end = y - 1; start < end; start++, end--) {
				swap(arr, start, end);
			}
		}
		//3，方法一：查找字符串出现的次数：
		public static void strCount(String str,String dest){
			//str="abcdeabefgabcdead";
			int count=0;
			int index=0;	//字符串角标位置
			//判断是否包含dest，获取dest的角标
			while((index=str.indexOf(dest))!=-1){			
				str=str.substring(dest.length()+index);
				count++;
			}
			System.out.println(count);
		}
		//	方法二：查找字符串出现次数：
		public static void strCount_2(String str,String dest){
			int count=0;
			int index=0;
			//通过查找判断是否包含，获取包含后的角标
			while((index=str.indexOf(dest,index))!=-1){
				//改变查找后的角标值
				index=index+dest.length();
				count++;
			}
			System.out.println(count);
		}	
		//4，  查找字符串中的 最大长度相同的字符串：
		public static String strLength(String str,String dest){
			
			//获取最大和最小 字符串；
			String max,min;
			max=(str.length()>dest.length())?str:dest;
			min=(str==max)?dest:str;
			
			//打印最小字符串的所有组合字符串；
			for(int x=0;x<min.length();x++){

				//z：新字符串的末尾角标， y：新字符串的零角标。z:防止角标越界！
				for(int y=0,z=min.length()-x;z!=min.length()+1; y++,z++){				
					
					String temp=min.substring(y,z);
					sop(temp);
					if(max.contains(temp))
						return temp;
				}
			}
			return "not find";
		}
		public static void sop(Object obj){
			System.out.println(obj);
		}
		public static void main(String[] args) {
			sop(strLength("abcdefdabcdabcdefc","12345a"));
		}
}
