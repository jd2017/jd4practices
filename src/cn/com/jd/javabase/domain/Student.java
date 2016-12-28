package cn.com.jd.javabase.domain;

import java.util.Arrays;
/**
 * 
 * @author jd.bai
 * @date 2016年12月6日
 * @time 下午4:42:33
 */
public class Student {
	private int age;
	private String name;
	private String[] hobby;
	private String birthday;
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String[] getHobby() {
			return hobby;
		}
		public void setHobby(String[] hobby) {
			this.hobby = hobby;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String string) {
			this.birthday = string;
		}
		@Override
		public String toString() {
			return "Student [age=" + age + ", name=" + name + ", hobby="
					+ Arrays.toString(hobby) + ", birthday=" + birthday + "]";
		}
}
