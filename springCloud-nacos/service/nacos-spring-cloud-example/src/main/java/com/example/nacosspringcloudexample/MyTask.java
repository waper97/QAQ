package com.example.nacosspringcloudexample;

/**
 * @ClassName MyTask
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/3 21:39
 */
public class MyTask implements Runnable{

    private int i;

    public MyTask(int i) {
        this.i = i;
    }
    public MyTask() {

    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+"---"+i);
    }
}
