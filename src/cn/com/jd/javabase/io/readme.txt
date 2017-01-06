Reader:
	用于读取字符流的抽象类。
	直接已知子类：
		BufferedReader, CharArrayReader, FilterReader, InputStreamReader, PipedReader, StringReader 
 Writer：
 	写入字符流的抽象类。
	直接已知子类： 
		BufferedWriter, CharArrayWriter, FilterWriter, OutputStreamWriter, PipedWriter, PrintWriter, StringWriter 
InputSteam：
	此抽象类是表示字节输入流的所有类的超类。	
	需要定义 InputStream 子类的应用程序必须总是提供返回下一个输入字节的方法。
	直接已知子类：	
		AudioInputStream, ByteArrayInputStream, FileInputStream, FilterInputStream, InputStream, ObjectInputStream, 
		PipedInputStream, SequenceInputStream, StringBufferInputStream
OutputStream:
	此抽象类是表示输出字节流的所有类的超类。输出流接受输出字节并将这些字节发送到某个接收器。
	需要定义 OutputStream 子类的应用程序必须始终提供至少一种可写入一个输出字节的方法。
	直接已知子类： 
 		ByteArrayOutputStream, FileOutputStream, FilterOutputStream, ObjectOutputStream, OutputStream, PipedOutputStream

1，明确源和目的。
	源：输入流。 InputStream Reader
	目的：输出流。OutputStream Writer
2，操作的数目是否是纯文本。
	是：字符流。FileReader	FileWriter
	否：字节流  FileIntputStream  FileOutputStream
3，当体系明确后，再明确要使用哪个具体的对象。
	通过设备来区分：
	源设备：内存，硬盘，键盘。
	目的设备：内存，硬盘，控制台。
	
	//获取键盘录入对象。
	InputStream in=System.in;
	//将字节流对象转成字符流对象，使用转换流。InputStreamReader
	InputStreamReader is=new InputStreamReader(in);
	//为了提高效率，将字符串进行缓冲区技术高效操作。使用BufferedReader
	BufferedReader br=new BufferedReader(is);*/