package com.yj.hqbz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.hp.hpl.sparta.ParseException;

public class CommUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static int null2Int(Object s) {
        int v = 0;
        if (s != null)
            try {
                v = Integer.parseInt(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    public static float null2Float(Object s) {
        float v = 0.0F;
        if (s != null)
            try {
                v = Float.parseFloat(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    public static double null2Double(Object s) {
        double v = 0.0D;
        if (s != null)
            try {
                v = Double.parseDouble(null2String(s));
            } catch (Exception localException) {
            }
        return v;
    }

    public static boolean null2Boolean(Object s) {
        boolean v = false;
        if (s != null)
            try {
                v = Boolean.parseBoolean(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    public static String null2String(Object s) {
        String res = "";
        if(s !=null){
            try{
                res = s.toString().trim();
            }
            catch(Exception ex){
                
            }
        }
        return res;
    }

    public static Long null2Long(Object s) {
        Long v = Long.valueOf(-1L);
        if (s != null)
            try {
                v = Long.valueOf(Long.parseLong(s.toString()));
            } catch (Exception localException) {
            }
        return v;
    }
    public static Date formatDate(String s) {
        Date d = null;
        try {
            d = dateFormat.parse(s);
        } catch (Exception localException) {
        }
        return d;
    }

    public static Date formatDate(String s, String format) {
        Date d = null;
        try {
            SimpleDateFormat dFormat = new SimpleDateFormat(format);
            d = dFormat.parse(s);
        } catch (Exception localException) {
        }
        return d;
    }

    public static String formatTime(String format, Object v) {
        if (v == null)
            return null;
        if (v.equals(""))
            return "";
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(v);
    }

    public static String formatLongDate(Object v) {
        if ((v == null) || (v.equals("")))
            return "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(v);
    }
    public static final String randomString(int length) {
        char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                .toCharArray();
        if (length < 1) {
            return "";
        }
        Random randGen = new Random();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    public static final String randomInt(int length) {
        if (length < 1) {
            return null;
        }
        Random randGen = new Random();
        char[] numbersAndLetters = "0123456789".toCharArray();

        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
        }
        return new String(randBuffer);
    }
    
    public static boolean isNumeric(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);

    }
    
    public static int getAgeByBirth(Date birthDay) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }
}
