package cn.com.jd.alog2struc.algorithms;

/**
 * 
 * @author jd.bai
 * @date 2016年12月2日
 * @time 下午4:43:30
 */
public class SortDemo {
	 /*
	  * 简单排序：冒泡、选择
	  */
	 private  long[] array= {11,9,13,1,19,13,100,89,0};
	 int nElem = array.length;
	 public  void bubbleSort(){
		for(int in = nElem-1;in > 0; in--){
			for(int j =0; j<in; j++){
				if(array[j]>array[j+1]){
					swap(j,j+1);
				}
			}
		}
	 }
	 public void selectSort(){
		for(int min = 0;min<nElem-1; min++){
			for(int max=min+1;max<nElem;max++){
				if(array[min]>array[max])
					swap(min,max);
			}
		}
	 }
	 public void swap(int one, int two){
		 long temp = array[one];
		 array[one] = array[two];
		 array[two] = temp;
	 }
	 public  void outArray(){
		 for(int i=0; i<nElem; i++){
				System.out.println(array[i]);
			}
	 }
}
