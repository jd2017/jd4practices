package cn.com.jd.javabase.Advance.annotation;

@Ancation(annocationAttr=@MetaAnnotation("flx"), color="red",value="abc",arrAttr={2,3,4})//添加一个注解对象，设置属性。
public class AnnotationTest {
	
	@Ancation("xzy")					//如果只有Value属性时，就可以这样填写	
	@SuppressWarnings("deprecation")	//注解：压缩警告提示。
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		System.runFinalizersOnExit(true);
		
		if(AnnotationTest.class.isAnnotationPresent(Ancation.class)){					//检查注解是否存在
			Ancation annocation = AnnotationTest.class.getAnnotation(Ancation.class);	//若存在，得到注解.
			System.out.println(annocation);								//获取注解
			System.out.println(annocation.color());						//获取属性值。
			System.out.println(annocation.value());						//只有value值时
			System.out.println(annocation.arrAttr().length);			//数组属性
			System.out.println(annocation.lamp().nextLamp());			//枚举属性。
			System.out.println(annocation.annocationAttr().value());	//注解属性
		}
	}	
	@Deprecated	//通过注解：说明过时 。
	public void sayHello()
	{
		System.out.println(23);
	}
}
