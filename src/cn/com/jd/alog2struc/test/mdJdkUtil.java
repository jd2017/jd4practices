package cn.com.jd.alog2struc.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import org.junit.Test;

import sun.misc.BASE64Encoder;
/**
 * @author jd.bai
 * @date 2016年12月2日
 * @time 下午5:22:50
 */
public class mdJdkUtil {
	 private static Logger logger = Logger.getLogger(mdJdkUtil.class.getName());      
	@Test
	public void test() throws Exception{
		logger.info(md5Password("123456")); //e10adc3949ba59abbe56e057f20f883e
		logger.info(getMD5("123456"));
	}
 /** 
  * md5加密方法 
  * @param password 
  * @return 
  */  
	public  String getMD5(String str) throws Exception {
		 try {
		  // 生成一个MD5加密计算摘要
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  // 计算md5函数
		  md.update(str.getBytes());
		  // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		  // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		  return new BigInteger(1, md.digest()).toString(16);
		 } catch (Exception e) {
			 throw new Exception("MD5加密出现错误");
		 }
	}
    public  String md5Password(String password) {  
        try {  
            // 得到一个信息摘要器  
            MessageDigest digest = MessageDigest.getInstance("md5");  
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();  
            // 把没一个byte 做一个与运算 0xff;  
            for (byte b : result) {
                // & 运算  
                int number = b & 0xff; 
                String str = Integer.toHexString(number);
            	System.out.println(str.length()+"=str:"+str);
                if (str.length() == 1) {  
                    buffer.append("0");  
                }  
                buffer.append(str);  
            }  
            password = buffer.toString();  
            // 标准的md5加密后的结果  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();    
        } 
        return password;
    }  
	/*
	 * BASE64Encoder&MessageDigest 加密
	 */
    public String md5Jdk(String message){
    		try {
    			MessageDigest md5 = MessageDigest.getInstance("MD5");
    			byte[] encodeMd5Digest = md5.digest(message.getBytes());
    			 BASE64Encoder base64en = new BASE64Encoder();
    		     //加密后的字符串
    			 message=base64en.encode(encodeMd5Digest);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return message;
    	}
}  

