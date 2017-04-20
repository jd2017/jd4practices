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
		
		long startTime = System.currentTimeMillis();
			selectSort();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		outArray();
		*/
		int[] arrayA = {3,6,9,200};
		int[] arrayB = {4,10,12,29,50};
		int[] arrayC = new int[arrayA.length+arrayB.length];
		merge(arrayA, arrayA.length, arrayB, arrayB.length, arrayC);
		outArray(arrayC);
		
	}
	/*
	 * 插入排序：复制的次数大致等于比较的次数，一次复制与一次交换的时间不同，复制是交换的3倍；
	 * 		对于数据有序的情况下，插入排序比较快（因为while总是假），逆序排序的数据效率低；比较和移动都会执行；
	 * 		取出一个标示对象；跟其他对象比较；将目标值插入指定位置；
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
	  * 选择排序：比冒泡排序比较，交换次数比较少，当N较小时，交换的时间级比比较的时间级大的多时，选择排序比较快；
	  *		从左边开始(固定角标)，比较剩下的所有的对象, 找出最小的坐标值后，交换一次值，依次类推；
	  * 比较次数：(N-1)+(N-2)+...=N*(N-1)/2
	  */
	 public void selectSort(){
		 int min, out;
		for( out= 0; out<nElems; out++){
			min = out;
			for(int in=out+1;in<nElems;in++){
				if(array[out]>array[in])
					min = in;
			}
			swap(min,out);
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
	 //print nElements;
	 public  void outArray(){
		 for(int i = 0 ;i<nElems;i++){
			 System.out.print(array[i]+",");
		 }
	 }
	 public void outArray(int[] array){
		 for(int element:array){
			 System.out.println(element);
		 }
	 }
	 /*
	  * 归并排序：
	  * 	归并两个已经有序的数组A、B，生成第三个数组C；
	  * 	如果有一数组排完以后是空，就不需要再去比较了；
	  * 	只要另一个数组中所有剩余的数据项复制到数组C中即可。
	  * 排序比较：
	  * 冒泡、插入和选择排序要用O(N^2)时间，而归并排序只要O(N*logN).
	  * 如果N数目是10000，N^2 就是 100000000，而N*logN只是40000.
	  * 归并排序的缺点：它需要存储器中有另一个大小等于被排序的数据项目数目的数组。
	  * 所以，如果有足够的空间，归并排序会是一个很好的选择；
	  * 
	  */
	 public void merge(int[] arrayA,int sizeA,int[] arrayB,int sizeB,int[] arrayC){//merge A and B into C;
		 int aDex =0; int bDex =0; int cDex=0;
		 while(aDex<sizeA && bDex<sizeB)		//neither array empty;
			 if(arrayA[aDex] < arrayB[bDex])
				 arrayC[cDex++] = arrayA[aDex++];
			 else
				 arrayC[cDex++]=arrayB[bDex++];
		 while(aDex < sizeA)			//arrayB is empty;
			 arrayC[cDex++]=arrayA[aDex++];
		 while(bDex < sizeB)			//arrayA is empty;
			 arrayC[cDex++] = arrayB[bDex++];
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
