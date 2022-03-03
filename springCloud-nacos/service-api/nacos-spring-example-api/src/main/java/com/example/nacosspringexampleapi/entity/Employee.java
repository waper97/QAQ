package com.example.nacosspringexampleapi.entity;

/**
 * @ClassName Employee
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/2 0:44
 */
public class Employee {

//    private static int nextId;

    private int id;

    private String name;

    private double salary;

    // 初始化代码块
//    {
//        id = nextId;
//        nextId++;
//    }
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }
}
