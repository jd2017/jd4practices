package cn.com.jd.javabase.Advance.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)				//元注解是一个枚举:注解上添加注解
@Target({ElementType.METHOD,ElementType.TYPE})	//让其可以加在类和方法上。
public @interface Ancation {
	//缺省属性
	String color() default "blue";
	String value();
	int[] arrAttr() default {2,3,4};			//数组的属性。
	EnumTest.ThrafficLamp lamp() default EnumTest.ThrafficLamp.RED;//枚举属性
	MetaAnnotation annocationAttr() default @MetaAnnotation(value = "meta");//注解属性。value=可以省略
		
	}
/*
Retetion:元注解的其三种取值： 默认为class 阶段
RetetionPolicy.SOURCE、
RetentionPolicy.CLASS、
RetentionPolicy.RUNTIME;
分别对应：java源文件->clas文件->内存中的字节码。

Override：			SOURCE		
SuppressWarnings：	SOURCE
Deprecated：			RUNTIME
*/
