package cn.com.jd.javabase.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author jd.bai
 * @date 2016年12月27日
 * @time 下午4:55:31

 */
public class MapAndCollection {
	/**
	 * TreeMap
	 */
	@Test
	public void map_Test(){
		TreeMap<Worker,String> ts=new TreeMap<Worker,String>();
		ts.put(new Worker("zhangsan1",10), "天津");
		ts.put(new Worker("zhangsan1",10), "哈尔滨");
		ts.put(new Worker("zhangsan2",12), "上海");
		ts.put(new Worker("zhangsan3",19), "天津");
		ts.put(new Worker("zhangsan5",19), "北京");
		ts.put(new Worker("zhangsan4",60), "天津");
		ts.put(new Worker("zhangsan4",60), "天津");
		ts.put(new Worker("zhangsan4",60), "天集合津");

		Set<Entry<Worker, String>> set=ts.entrySet();
		//Iterator<Entry<Worker, String>> it=set.iterator();
		for(Iterator<Entry<Worker,String>> it=set.iterator();it.hasNext();)
		{
			Entry<Worker,String> entry=it.next();
			Worker w=entry.getKey();
			String addr=entry.getValue();
			sop("name="+w.getName()+"----sage="+w.getAge()+"---------"+addr);
		}
		sop("------------方法二：----------------");
		Set<Worker> set1 =ts.keySet();
		Iterator<Worker> it=set1.iterator();
		while(it.hasNext()){
			Worker w=it.next();
			String ar=ts.get(w);
			System.out.println("name="+w.getName()+"----sage="+w.getAge()+"---------"+ar);
		}
	}
	/**
	 * HashSet:底层数据结构是哈希表
	 * TreeSet:可以对Set集合中的元素进行排序
	 */
	@Test
	public void set_Test(){
		HashSet hs=new HashSet();
		hs.add("123");
		hs.add(true);
		hs.add(true);
		hs.add(12L);
		hs.add(null);
		hs.remove(true);
		for(Iterator it=hs.iterator();it.hasNext();)
		{
			sop(it.next());
		}
		sop(hs);
		//提供的 Comparator 进行排序
		TreeSet<String> tree = new TreeSet<String>(new StrLen());
		tree.add("1");
		tree.add("123");
		tree.add("12");
		sop(tree);
	}
	/**
	 * List:凡是操作角标的方法都是该体系特有的方法。
	 * 	如果想要其他的操作如：添加、修改等，需要使用其子接口：ListIterator。
	 */
	@Test
	public  void list_Test(){
		List al=new ArrayList();		
		//1,add
		al.add("123");
		al.add(true);
		al.add(321);
		al.add("hello");
		sop(al);  //[123, true, 321, hello]
		al.add(2,false);
		List al1=new ArrayList();
		al.add(3,al1);
		sop(al); //[123, true, false, [], 321, hello]
		//2,remove(index)
		al.remove(2);
		al.remove(al1);
		sop(al); //[123, true, 321, hello]
		//3,set(index,element);
		al.set(1, "345");
		sop(al); //[123, 345, 321, hello]
		//4,get(index)/subList(start,end)/listIterator();
		sop(al.get(1));
		sop(al.subList(1,3));
		sop(al.indexOf("abc"));		//获取对象的位置。
		sop(al);		// [123, 345, abc, hello]
		//列表中的 迭代器 ListIterator;
		for(ListIterator it=al.listIterator();it.hasNext();)
		{
			Object obj=it.next();
			if("123".equals(obj)){
				it.set("Sett");
				it.add("ListIterator Element");
//				it.remove();//只是移除了 集合中的引用；
				sop(obj+"查找的数值");//对象obj 依然存在。	123查找的数值
			}
			sop(obj);		
		}
		sop(al);// 修改后：[Sett, ListIterator Element, 345, abc, hello]
		//java.util.ConcurrentModificationException：当方法检测到对象的并发修改，但不允许这种修改时，抛出此异常。
		//解决方案，要么用集合，要么用iterator， 用其一。
	}
	/**
	 * Collections 的部分方法
	 */
	@Test
	public void collections_Test(){
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("acbd");
		list.add("123");
		sop(list); //[abc, acbd, 123]
		Collections.sort(list);
		sop(list); //[123, abc, acbd]
		Collections.reverse(list);
		sop(list); //[acbd, abc, 123]
		String max=Collections.max(list,new StrLen());
		sop(max); //acbd
		Collections.swap(list, 0, 2);
		sop(list); //[123, abc, acbd]
	}
	/**
	 * for循环获取集合数据；
	 */
	@Test
	public void for_test(){
		//ArrayList
		List<String> al = new ArrayList<String>();
		al.add("abc1");
		al.add("acb2");
		al.add("abc3");
		al.add("abc3");
		//方式一：
		for(String s : al)
		{
			sop(s);
		}
		//方式二：
		Iterator<String> it = al.iterator();
		while(it.hasNext())
		{
			sop(it.next());
		}
		//HashMap
		Map<Integer,String> hm = new HashMap<Integer,String>();
		hm.put(1,"a");
		hm.put(2,"b");
		hm.put(3,"c");
		//方式一：
		Set<Integer> keySet = hm.keySet();
		for(Integer i : keySet)
		{
			sop(i+"::"+hm.get(i));
		}
//		Set<Map.Entry<Integer,String>> entrySet = hm.entrySet();
		//方式二：
		for(Map.Entry<Integer,String> me : hm.entrySet())
		{
			sop(me.getKey()+"----"+me.getValue());
		}
	}
	public <T> void sop(T t){
		System.out.println(t);
	}
}
//Comparator的子类 自定义 依照长度 大小自定义
class StrLen implements Comparator<String>{
	public int compare(String str1,String str2){		
			int num=new Integer(str1.length()).compareTo(new Integer(str2.length()));
			if(num==0){
				return str1.compareTo(str2);
			}
			return num;
	}
}
class Worker implements Comparable<Worker>{
	private String name;
	private int	age;
	public Worker(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//TreeSet存储，要 复写 的方法
	public int compareTo(Worker w)
	{
		int num=new Integer(this.getAge()).compareTo(new Integer(w.age));
		if(num==0){
			return w.getName().compareTo(this.name);
		}
		return num;		//不要误写成-1； 否则第一次插入数据，null值 会 代替Value。
	}
	
	 //*  HashMap 存储要复写 的方法：
	public int hashCode(){
		return this.name.hashCode()+age*31;	
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Worker))
			throw new ClassCastException("不是 Worker对象");
		Worker w=(Worker)obj;
		return w.getName().equals(this.name) && w.getAge()==this.age;
	}
	public String toString(){
		return "name+"+this.name+"age="+this.age;
	}
}
