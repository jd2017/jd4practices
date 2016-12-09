package cn.com.jd.alog2struc.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * @author jd.bai
 * @date 2016年12月2日
 * @time 下午4:43:30
 * 	简单排序：冒泡、选择、插入排序
 */
public class SortDemo {
	
//	String filePath = (SortDemo.class.getResource(".").getPath()+"sortfile.txt");
	long[] array= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
	int nElems = array.length;
	
	@Test
	public void test(){
//		StringBuilder sb = new StringBuilder();
//		for(int i=1000;i>0;i--){
//			sb.append(i+",");
//		}
//		System.out.println(sb.toString());
	
		/**
		 *  insertSort(array,nElems);	//39021
		 	bubbleSort(array,nElems);	//44896
			selectSort(array,nElems);  //74932
		*/
		long startTime = System.currentTimeMillis();
			bubbleSort();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		outArray();
	}
	/*
	 * 插入排序：对于基本有序的数据来说，插入排序比较快（因为while总是假），逆序排序的数据效率低；比较和移动都会执行；
	 * 		复制是交换的3倍；
	 * 		取出一个标示对象；跟其他对象比较；将大的插入取出对象的位置；
	 * 	比较次数：(N-1)+(N-2)+...=N*(N-1)/2
	 */
	public void insertSort(){
		int in ,out;
		for(out = 1; out < nElems; out++){
			long temp = array[out];
			in = out;
			while(in>0 && array[in-1] >= temp ){
				array[in] = array[in-1];
				in--;
			}
			array[in] = temp;
		}
	}
	 /*
	  * 选择排序： 交换次数比较少，当N较小时，交换的时间比计较的时间级大的多时，选择排序比较快；
	  *		从左边开始(固定角标)，比较剩下的所有的对象；较小的关键字被重复发现；若左边大，则交换位置，依次类推；
	  * 比较次数：(N-1)+(N-2)+...=N*(N-1)/2
	  */
	 public void selectSort(){
		for(int out = 0; out<nElems; out++){
			for(int in=out+1;in<nElems;in++){
				if(array[out]>array[in])
					swap(out,in);
			}
		}
	 }
	/*
	 *	冒泡排序： 交换比较多；
	 *		从左边开始(角标右移)，比较相邻两个的值，若左边大，则交换位置，继续比较下两个值；
	 * 	比较次数：(N-1)+(N-2)+...=N*(N-1)/2
	 */
	public  void bubbleSort(){
		for(int out = nElems-1; out>0; out--){
			for(int in =0; in<out; in++){
				if(array[in]>array[in+1]){
					swap(in,in+1);
				}
			}
		}
	 }
	 public void swap(int one, int two){
		 long temp = array[one];
		 array[one] = array[two];
		 array[two] = temp;
	 }
	 public  void outArray(){
		 for(int i = 0 ;i<nElems;i++){
			 System.out.print(array[i]+",");
		 }
	 }
	 /**
	     * 功能：Java读取txt文件的内容
	     * 步骤：1：先获得文件句柄
	     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	     * 3：读取到输入流后，需要读取生成字节流
	     * 4：一行一行的输出。readline()。
	     * 备注：需要考虑的是异常情况
	     * @param filePath
	     */
	    public  String readTxtFile(String filePath){
	        try {
	                String encoding="UTF-8";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    StringBuilder sb = new StringBuilder();
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                        sb.append(lineTxt);
	                    }
	                    read.close();
	                    return sb.toString();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
			return null;
	    }
	    //将字符串按照指定规则转化为数组；
	    public long[] reverArray(String str){
	    	String[] strArrary = str.split(",");
            long[] array = new long[strArrary.length];
            for(int i=0;i<strArrary.length;i++){	
            	array[i]=Integer.parseInt(strArrary[i]);
            }
	    	return array;
	    }
}
