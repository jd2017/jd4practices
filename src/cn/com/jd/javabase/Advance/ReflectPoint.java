package cn.com.jd.javabase.Advance;
import java.util.Date;
public class ReflectPoint {
	private int x;
	public int y;
	private Date birthday=new Date();
	public String str1="ball";
	public ReflectPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	//为了提高效率，根据 哈希值 算法，划分到不同的区域存储对象。
	//为了让相等内容的对象放同一个区域，让其hashCode 也相同。
	//当一个对象被存储进HashSet 以后，就不能修改这个对象中  那些参与计算的哈希值 的字段！！
	//否则，修改后的 哈希值，与最初存储时的哈希值就不同了，操作对象时失效，造成内存泄露。
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReflectPoint other = (ReflectPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	public String toString(){
		return str1+x+y;
	}
}

