	配置java 编译 环境：
	window——>Preferences->java->Complier
	配置运行环境：
	window——>Preferences->Installed JREs
	配置快捷键：
	window——>Preferences->General——>Keys->Content Assist
	查看项目运行环境：
	工程右键——>Run as->Run Configurations
	编辑 模板 代码：
	window——>Preferences->Editor->Templates->new
	增加项目JRE 库：
	右键->Build Path->Configure Build Path-Libraries
	修改复制的项目名称：
	项目->右键->properties->Myeclipse->Web
	
	1.5 新特性：
	
	1，静态导入：
		import System.out.*;
	2，可变参数：
		只能出现参数列表最后；
		args隐含创建一个数组。
	3，增强for循环：
		for(type 变量名：集合变量名){...}
		迭代变量必须在() 中定义；可以修饰
		集合变量可以是数组或者实现了Iterable接口的集合类
	4，自动装箱与拆箱：
		Integer it=2;
		int in=it;
		如果在 -128~127之间；为同一个对象。
		享元模式(flyweight):
			将很多相同属性的小对象变成一个内部对象，不同属性变成方法参数外部对象，需要时调用。
	5，枚举：Enum
		让某个类型的变量的取值只能为若干个固定的值；否则编译报错。
		私有的构造方法。
		每个元素分别为一个共有的静态成员变量。
		可以有若干共有方法或抽象方法。
		自定义枚举：
			private WeekDay{}
			public final static WeekDay SUN=new WeekDay();
			覆盖 String toString() 方法。
		定义枚举构造方法时，要定义在自定义的对象后面，自动覆盖String toString().
	6，注解：
		@SuppressWarnings 取消编译警告。
		@Deprecated		过时标记。
		@Override		覆盖注解
	注解相当于一种标记，程序加了注解，等于为程序打了某种标记。可以通过反射来了解注解。
	标记可以在 包，类，字段，方法，方法的参数及局部变量。
	
	内省：主要对JavaBean 操作
	
	PropertyDescriptor：属性存储器方法：
	 	Class<?> getPropertyType() 
	          	获得属性的 Class 对象。 
	操作JavaBean属性时：
		获取对象的Class类，通过PropertyDescriptor 类构建对象，获取getReadMethod()、getWriteMethod();  
				invoke(Object obj,Object... args)操作JavaBean的属性，			
	可以通过BeanUtils包：
		//类型不需要转换
		PropertyUtils.setProperty(pt1, "x", 9);
		System.out.println(PropertyUtils.getProperty(pt1, "x").getClass());
		//date对象为符合属性
		BeanUtils.setProperty(pt1, "birthday.time", "111");
		System.out.println(BeanUtils.getProperty(pt1,"birthday.time"));
		
	
	
	IntroSpector：构建一个全面描述目标 bean 的 BeanInfo 对象。 
				static BeanInfo getBeanInfo(Class<?> beanClass) 
	接口 BeanInfo
	 		提供实现bean 的方法、属性、事件等显式信息。 
		MethodDescriptor[] getMethodDescriptors() 
	         					 获得 beans MethodDescriptor。 
	 	PropertyDescriptor[] getPropertyDescriptors() 
	          					获得 beans PropertyDescriptor。 
	 		
	 
		IntroSpector->JavaBean->特殊Java类
		格式规则：
		void setAge(int age)
		int getAge()
		属性名：			PropertyDescriptor
		Age->如果第二个字母是小的，则把第一个字母变成小的->age
		getCUP->如果第二个字母是大写，则也是大写->CUP
		两个模块之间传递多个信息，可以将这些信息封装到一个JavaBean 中，
		这种JavaBean实例对象常称之为 值对象（Value Object，简称：VO）。
	
	--------------------------------------------------------------------------------------
	反射的基石：——>Class 类
	java类：用于描述某一类 事物的共性。
	Class cls1= 字节码1;
	class cls2= 字节码2;
	
	方法一：
	Date.class	类名.class
	方法二：		对象.getClass
	new Date().getClass();
	方法三：		Class.forName("类名");	运行的时候变量名进内存，静态调用。
	Class.forName("java.util.Date")
	九个预定义Class实例对象：八个基本数据类型和void。
	Class.isPrimitive方法
	int.class==Integer.TYPE
	数组类型的Class 实例对象
	Class.isArray();
	总之，只要是在源程序中出现的类型，都有各自的实例对象。例如：int[],void
	
	反射：
		 把java类中的各种成分 映射成相对应的java类。
	
	Constructor：代表某个类的所有构造方法：
		Constructor[] constructor=
				Class.forName("java.lang.String").getConstructors();
		得到某一个构造方法：获得方法时要用的类型
			Constructor constructor1=String.class.getConstructor(StringBuffer.class);//编译时
		创建实例对象通过反射:调用方法时，用到上面相同类型的实例对象.通常做法，new String(new StirngBuffere("abc"));
			String string=(String)constructor1.newInstance(new StringBuffer("abc"));//运行时。
		Class.newInstance()方法。该方法内部先得到默认的构造方法，用该构造方法创建实例对象。
			String obj=(String)Class.forName("java.lang.String").newInstance();
	
	Filed类 代表某个类中的一个成员变量
		得到的Field对象时对应到类上面的成员变量，还是对象上面的成员变量？
		类只有一个，而该类的实例对象有多个，如果和对象关联，关联到那个对象？
		所以fieldX代表的是 x 的定义，而不是具体的x的变量。
		ReflectPoint pt1=new ReflectPoint(3,5);
			Field fieldY=pt1.getClass().getField("y");//public
			//fieldY=?? 不代表某个对象的变量。而是类上，要用它去取对某个对象对应的值。
			sop(fieldY.get(pt1));
					
	Method类 代表某个类上中的成员方法：
		得到类中的某个方法：
			Method methodCharAt=
				String.class.getMethod("charAt",int.class);
			调用方法：
				通常方式：sop(str.charAt(1));
				反射方式：Character ch=(Character)methodCharAt.invoke(str1, 1);
			Character ch=(Character)methodCharAt.invoke(null, 1);
			如果Method对象的invoke()方法，第一个参数为null，
				说明该Method 对象对应的是一个静态的方法。
			jdk1.4 与 jdk1.5 区别：
			public Object invoke(Object obj,new Object... obj);
			public Object invoke(Object obj,new Object[] args);
	-------------------------------------------------------------------------------------		
			
	泛型：
		存放在集合时，限定集合输入的类型。由运行时异常，改为编译时异常。省略强制类型转换。
		编译完了以后, 会去掉”类型“信息。可以通过反射，获取集合添加信息。
			
			ArrayList<Integer> collection1=new ArrayList<Integer>();
			ArrayList<String>  collenction2=new ArrayList<String>();
			collection1.getClass=collection2.class		//true
			//例子：collection1.add("abc");
			collection1.getClass().getMethod("add",Object.class).invoke(collection1,"abc");
		
		泛型术语：	
		ArrayList<E> 类定义和ArrayList<Integer>类引用中
		整个称为ArrayList<E>泛型类型。
		E：类变量或者类型参数。
		ArrayList<Integer>参数化的类型
		Integer			实际类型参数。
		
		参数化类型 不考虑 类型 参数的继承关系
		Vector<Object> v=new Vector<String>	//错误
		Vector<String> v=new Vector<Object>	//错误
		
		在创建数组实例时，数组的元素不能使用参数化的类型。下面错误
		例如：Vector<Integer>[] vector=new Vector<Integer>[23];	
			
		泛型中的 ？通配符
		public static void printCollection(Colletion<?> cols){
			Sop(obj.size());//与参数无关
			for(Object obj:cols){
				sop(obj);
			}
			cols=new HashSet<Date>(); 可以
		}
		总结：
			使用? 通配符可以 引用与其他各种参数化的类型，？通配符定义的变量主要用作引用，
			可以调用与参数化无关的方法，不能调用与参数化有关的方法。	
		限定通配符 的上边界：
			Vector<? extends Number> v=new Vector<Integer>();可以
			Vector<? extends Number> v=new Vector<String>(); 不可以
		限定通配符 的下边界：
			Vector<? super Integer> v=new Vector<Integer>();可以
			Vector<? super Integer> v=new Vector<Byte>(); 不可以
			限定符总是包括自己
			
		泛型的实际类型只能是 引用类型，不能使基本数据类型。
		static<T> void swap(T t,int i,int j){
			T temp=t[i];
			t[i]=t[j];
			t[j]=temp;
		}
		基本数据类型数组本身就是一个对象；所以不能用。
		也可以用类型变量表示异常。称为参数化 异常。
	--------------------------------------------------------------------------------------
	
	类加载器：
		java 虚拟机中可以安装多个类加载器，系统默认三个主要的类加载器：
			BootStrap，ExtClassLoader，AppClassLoader
	作用：将.class 文件加载到硬盘中进行处理。
	
	类加载器也是Java类，其他类加载器也要被加载。第一个类加载器不是java类，
		就是BootStrap(不是java类，在java虚拟机内核中，启动jvm时，已经存在，
		由C++语言编写的二进制代码，可以加载别的类加载器)。
	类加载器之间的父子关系：
		BootStrap							JRE/lib/rt.jar
		ExtClassLoader						JRE/lib/ext/*.jar
		AppClassLoader						CLASSPATH指定的所有jar或者目录
	MyClassLoader	ItcastClassLoader		自定义指定的特殊目录
	
	
	类加载器的委托机制：
		每个类加载器	加载类时，又先委托给其上级类加载器。若都加载不到 ，返回发起者类加载器。
		若还还加载不了，则抛出ClassNotFoundException，不去找儿子。
		如果 A 用到 B ，就要由A 的加载器去加载。例如：WebAppClassLoader->MyServlet->HttpServlet
	举例：
	将ClassLoaderTest 输出成jre/lib/ext/*.jar下的itcast.jar包，运行测试，得到结果为：ExtClassLoader
	
	编写自己的类加载器：
	 	自定义的类加载器 必须继承 ClassLoader(类加载器是负责加载类的对象。ClassLoader 类是一个抽象类)
		loadClass 方法 与 findClass方法。
		defineClass 方法 
	通过 模板设计模式去实现：(paramtered)总体流程已经控制好，局部细节子类去实现。
	 	父类-> loadClass/findClass/得到class 文件的转换成字节码-->defineClass
	---------------------------------------------------------------------------------- 
	
	代理的概念 与 作用：
		通过  接口(doSomeThing()) 从代理类(Proxy) 中 获取 目标类(Target);
	如果采用工厂模式 和配置文件的方式进行管理，则不需要修改客户端程序，在文件配置中是使用
	目标类、还是代理类；修改功能。
	
	AOP(Aspect oriented program):交叉业务的编程问题，即为面向 切面的编程。
	因安全，事务，日志等功能 要贯穿到好多个 模块中，所以，他们就要交叉业务。
	
	动态代理类:
	Jvm 可以在运行期 动态生成出类的字节码，动态生成的类往往被用作代理类，即动态代理类。
	Jvm 生成的动态类 必须实现一个或者 多个接口，
		所以，Jvm生成的动态类只能用作具有相同接口的目标类的代理。
	CGLIB库： 可以为一个没有实现接口的类生成动态代理类。
	怎样生成动态代理类？
		1, 通过Proxy.newProxyInstance()生成一个指定接口的代理类实例。传递参数(代理类加载器，代理类实现的接口列表，指派方法调用的调用处理程序 )
		2，创建一个InvocationHandler对象。传递目标代理类。 method.invoke(目标代理类，处理的参数)
		3，返回一个代理类运行后的的结果。					return returnValue;
	
	代理类的各个方法中 通常除了要调用目标的相应方法和对外返回目标的结果外，
		可以在下面四个位置加上系统功能代码：
		1，在调用目标方法之前
		2，在调用目标方法之后
		3，在调用目标方法前后
		4，在处理目标方法异常的catch块中
		
	----------------------------------------------------------
	工厂类 BeanFactory 负责创建目标类或者代理类的实例对象，并通过配置文件实现切换。
		其getBean方法根据根据参数字符串返回一个相应的实例对象；如果参数字符串在配置文件中
		对应的类名不是ProxyFactoryBean，则直接返回该类的实例对象，否则，返回该类实例对象的
		getProxy方法返回的对象。
	
	BeanFactory的构造方法接受 代表配置文件的输入流对象，配置文件格式如下：
		#xxx=java.util.ArrayList
		xxx=cn.itcast.ProxyFactoryBean
		xxx.target=java.util.ArrayList
		xxx.advice=cn.itcast.MyAdice
	
	ProxyFactoryBean 充当封装生成动态代理的工厂，需要给工厂提供配置参数信息：
		目标
		通知()
	
	--------------------------------------------------------------------------------
	面向对象分析与设计：
		谁拥有数据，谁就对外提供这些操作数据的方法。
	案例：人在黑板上画圆、列车司机紧急刹车、售票员在统计售票小票上的金额、人关门。