package com.yjh.utils;

import static java.lang.Math.random;

import java.util.Calendar;

public class NumberUtils {
    // @description 随机生成的随机数，取值范围在1~2147483647 （int最大正值）。
    public static int getNumber() {
        return (int) (Integer.MAX_VALUE * random()) + 1;
    }
    
    //生成教师工号
    public static String  getTeacherNumber() {
    	String str=getNumber()+"";
    	String next=str.substring(5, str.length());
    	Calendar now = Calendar.getInstance();  
        String year=now.get(Calendar.YEAR)+"";  
    	String teacherNumber=year+"1"+next;
    	return teacherNumber;
    }
    
    //生成学生学号
    public static String  getStudentNumber() {
    	String str=getNumber()+"";
    	String next=str.substring(5, str.length());
    	Calendar now = Calendar.getInstance();  
        String year=now.get(Calendar.YEAR)+"";  
    	String studentNumber=year+"2"+next;
    	return studentNumber;
    }

    public static void main(String [] args) {
    	System.out.println(getTeacherNumber());
    	
    }


}