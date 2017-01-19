package cn.com.jd.javabase.Advance;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class IntroSpectorTest {
	public static void main(String[] args) throws Exception {
		ReflectPoint pt1=new ReflectPoint(3,5);
		String popertyName="x";
		/*x->X-getX->MethodGetX->
		Method methodGetX=pt1.getClass().getMethod("getX", null);
		System.out.println(methodGetX.invoke(pt1, null));
		*/
		Object retVal = setProperties(pt1, popertyName);			//获得x值
		System.out.println(retVal);
		Class clazzpt1=pt1.getClass();
	
		//读取 JavavBean 方法：
		PropertyDescriptor pd=
			new PropertyDescriptor(popertyName,clazzpt1);
		Method methodGetX=pd.getReadMethod();
		System.out.println(methodGetX.invoke(pt1, null)+"xxxxxxxxxxx");
		//修改：JavaBean 方法：
		PropertyDescriptor prod=
			new PropertyDescriptor(popertyName,clazzpt1);
		Method mWriterX=prod.getWriteMethod();
		mWriterX.invoke(pt1, 12);
		System.out.println(pt1.getX()+"xxxxxxxxxxx");
		
		
		int valueObj=7;
		getProperties(pt1, popertyName, valueObj);
		System.out.println(pt1.getX());
		
		
		System.out.println("----------------BeanUtils包操作---------------");
		//获取属性的类型。
		System.out.println(BeanUtils.getProperty(pt1,"x").getClass().getName());
		BeanUtils.setProperty(pt1, "x", "9");
		System.out.println(pt1.getX());
		
//		Map map={name:"xyz",age:32};		JDK1.7
//		BeanUtils.setProperty(map, "name", "juanjuan");
		BeanUtils.setProperty(pt1, "birthday.time", "2017");//date对象为符合属性
		System.out.println(BeanUtils.getProperty(pt1,"birthday.time"));
		
		//类型不需要转换
		PropertyUtils.setProperty(pt1, "x", 19);
		System.out.println(PropertyUtils.getProperty(pt1, "x").getClass());
	}

	//重构getProperties
	private static void getProperties(Object pt1, String popertyName,
			int valueObj) throws Exception {
		PropertyDescriptor pd2
			=new PropertyDescriptor(popertyName,pt1.getClass());
		Method methodSetX=pd2.getWriteMethod();
		methodSetX.invoke(pt1, valueObj);
	}
	//重构setProperties
	private static Object setProperties(Object pt1, String popertyName)
			throws Exception {
		PropertyDescriptor pd1
			=new PropertyDescriptor(popertyName,pt1.getClass()); //获得JavaBean 的属性
		Method methodGetX=pd1.getReadMethod();				//x属性 读方法。
		Object retVal=methodGetX.invoke(pt1, null);
		return retVal;
	}
}
