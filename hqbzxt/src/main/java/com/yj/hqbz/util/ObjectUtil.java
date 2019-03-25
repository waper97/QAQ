package com.yj.hqbz.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**  
 * @Title: ObjectUtil.java
 * @Package com.yj.hqbz.util
 * @Description: TODO
 * @author xx
 * @date 2019-3-6
 */
public class ObjectUtil {

	 public static  void fatherToChild (Object father,Object child) throws Exception{
		 if(!(child.getClass().getSuperclass()==father.getClass())){
			 throw new Exception("child不是father的子类");
		 }
		 Class fatherClass= father.getClass();
		 Field ff[]= fatherClass.getDeclaredFields();
		 for(int i=0;i<ff.length;i++){
			 Field f=ff[i];//取出每一个属性，如deleteDate
			 Class type=f.getType();
			 Method m=fatherClass.getMethod("get"+upperHeadChar(f.getName()));//方法getDeleteDate
			 Object obj=m.invoke(father);//取出属性值			 
			 f.set(child,obj);
		 }
	 }
	 /**
	  * 首字母大写，in:deleteDate，out:DeleteDate
	  */
	 private static String upperHeadChar(String in){
		 String head=in.substring(0,1);
		 String out=head.toUpperCase()+in.substring(1,in.length());
		 return out;
	 }

}
