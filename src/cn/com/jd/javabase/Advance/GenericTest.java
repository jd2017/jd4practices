package cn.com.jd.javabase.Advance;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class GenericTest {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		copy2(new Date[10],new String[10]);
		copy1(new Vector<String>(),new String[10]);
		//copy1(new Vector<Date>(),new String[10]); 报错，根据调用泛型方法时实际传递的参数类型或返回值的类型来推断。
		
		GenericDao<ReflectPoint> dao=new GenericDao<ReflectPoint>();
		dao.add(new ReflectPoint(2, 6));
		
		//得到泛型类型：Vector<Date> v1=new Vector<Date>();
		Method applayMethod=
			GenericTest.class.getMethod("applayVector", Vector.class);//获得方法。
		Type[] types=
			applayMethod.getGenericParameterTypes();			//获得返回值类型
		ParameterizedType pType=(ParameterizedType) types[0];	//参数化类型
		System.out.println(pType.getRawType());					//原始类型
		System.out.println(pType.getActualTypeArguments()[0]);	//实际化类型。
	}
	//v1 不能获得 Vector类型，要通过，applayVector方法，得到。
	public static void applayVector(Vector<Date> v1){
		
	}
	//copy 数组
	public static<T> void copy2(T[] dest,T[] src){
		
	}
	//copy 集合
	public static <T> void copy1(Collection<T> dest,T[] src){
		for(int i=0;i<src.length;i++)
		{
			dest.add(src[i]);
		}
	}
	//自定义打印所有任意参数化类型的集合中所有内容 。
	public static<T> void  printCollection(Collection<T> cols,T t){
		System.out.println(cols.size());
		for(Object obj:cols)
		{
			System.out.println(obj);
		}
		cols.add(t);
	}
	
	//任意类型数组中的所有元素，填充为相应类型的某个对象
	public static<T> void fillArray(T[] a,T obj){
		for(int i=0;i<a.length;i++)
		{
			a[i]=obj;
		}
	}
	//任意类型Object转为 其他类型。
	public static<T> T autoConvert(Object obj){
		return (T)obj;
	}
}
