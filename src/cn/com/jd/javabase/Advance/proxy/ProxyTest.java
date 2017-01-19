package cn.com.jd.javabase.Advance.proxy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ProxyTest {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		
		//创建动态类：
		Class clazzProxy1=
			Proxy.getProxyClass(Collection.class.getClassLoader(),Collection.class);
		System.out.println(clazzProxy1.getName());//$Proxy0
		
		System.out.println("begin constructors list ------------");
		/*$Proxy0
		 * $Proxy0(InvocationHandler,int)
		 */
		Constructor[] constructors=clazzProxy1.getConstructors();
		
		for(Constructor constructor:constructors){
			String name=constructor.getName();
			StringBuilder sb=new StringBuilder(name);
			sb.append("构造方法列表：(");
			Class[] clazzParams=constructor.getParameterTypes();//取的一组参数列表 Class 对象。
			for(Class clazzParam:clazzParams){
				sb.append(clazzParam.getName()).append(',');
			}
			if(clazzParams!=null&&clazzParams.length!=0){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append(")");
			System.out.println(sb.toString()); 
		}
		System.out.println("begin Methods list ------------");
		
		Method[] methods=clazzProxy1.getMethods();
		for(Method method:methods){
			String name=method.getName();
			StringBuilder sb=new StringBuilder(name);
			sb.append('(');
			Class[] clazzParams=method.getParameterTypes();
			for(Class clazzParam:clazzParams){
//				System.out.println(clazzParam+"。。。");
				sb.append(clazzParam.getName()).append(",");
			}
			if(clazzParams!=null&&clazzParams.length!=0)
				sb.deleteCharAt(sb.length()-1);
			sb.append(')');
			System.out.println(sb.toString()); 
		}
		
		System.out.println("begin begin create instance Object --方法一：---------");
		//类字节码，关联的类加载器对象。
		Constructor constructor =
			clazzProxy1.getConstructor(InvocationHandler.class);
		class MyInvocationHandler implements InvocationHandler{
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		}
		Collection proxy1=
			(Collection)constructor.newInstance(new MyInvocationHandler());
		System.out.println("proxy=="+proxy1.toString());//测试 proxy1 为null 还是 proxy1.toString() 为null；结果为toString() 返回结果为null
		proxy1.clear();		//调用此方法正常，说明 对象存在。
//		proxy1.size();
		
		System.out.println("begin begin create instance Object --方法二：---------");
		Collection proxy2=(Collection) constructor.newInstance(new InvocationHandler(){
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		});
	/*
		System.out.println("begin begin create instance Object --方法三：---------");

		Collection proxy3=(Collection) Proxy.newProxyInstance(
				Collection.class.getClassLoader(),
				new Class[]{Collection.class},
				new InvocationHandler(){
					
					ArrayList target=new ArrayList();					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						Long startTime=System.currentTimeMillis();
						Object retValue=method.invoke(target, args);//调用目标：把参数传进 去   让其运行起来。
						Thread.sleep(500);
						Long endTime=System.currentTimeMillis();
						System.out.println(method.getName()+"...Running Time is..."+(endTime-startTime));
						
						return retValue;
					}
				});
			
			Object obj=proxy3.add("abcdef");	
			proxy3.add("defghi");
			proxy3.add("opdrst");
			System.out.println(proxy3.size());
			System.out.println(proxy3.getClass().getName());//为何为   $Proxy0 呢?
			//Object类只将 hashcode 与equals 和toString 委托给InvokeHandler去实现，其他的不委托。
			System.out.println(proxy3.getClass().toString());
	
	//		 * 调用objProxy.add("abc")时：invoke(proxy,method,abc);
	//		 *proxy	:	objProxy
	//		 *method:	add方法
	//		 *args	: 	"abc"参数
		 
	*/
		ArrayList target=new ArrayList();					
		Collection proxy3=(Collection) getProxy(target,new AdviceImpl());
		
		proxy3.add("abcdef");	
		proxy3.add("defghi");
		proxy3.add("opdrst");
		System.out.println(proxy3.size());
		System.out.println(proxy3.getClass().getName());
	}
	//生成动态代理，传递给 目标和实现的功能对象即可。
	private static  Object getProxy(final Object target,final Advice advice) {
		Object proxy3= Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new InvocationHandler(){
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {	
						
						advice.beforeMehod(method);
						Object retValue=method.invoke(target, args);//调用目标：把参数传进 去   让其运行起来。
						TimeUnit.SECONDS.sleep(1);
						advice.afterMethod(method);
						return retValue;
					}
				});
		return proxy3;
	}
}
