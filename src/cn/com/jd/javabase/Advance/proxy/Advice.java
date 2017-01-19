package cn.com.jd.javabase.Advance.proxy;
import java.lang.reflect.Method;

public interface Advice {
	void beforeMehod(Method method);
	void afterMethod(Method method);
}
