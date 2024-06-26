package com.example.earth.Building;

/**
 * 学校类，一个神奇的建筑，孕育着无数的怀揣梦想的人
 * @author 程序代号
 * @version 1.0
 */
public class School
{
    //没有继承任何的类，所以默认继承Object类
    //成员变量
    private int year;
    private String name;
    private int size;

    //默认构造器
    public School(){}

    public School(int year, String name, int size)
    {
        this.year = year;
        this.name = name;
        this.size = size;
    }

    //一些方法
    /**
     * 开启方法，学校总是会在早上开门
     */
    public void open()
    {
        System.out.println(name+"学校向你敞开了大门");
    }
    /**
     * 关门方法，学校总是会在晚上关门
     */
    public void close()
    {
        System.out.println("夜深了，"+name+"关闭了大门");
    }



    //set和get方法
    /**
     * year的set方法
     * @param year 学校的建成年份
     */
    public void setYear(int year)
    {
        this.year =year;
    }

    /**
     * year的get方法
     * @return 学习建成年份
     */
    public int getYear()
    {
        return year;
    }

    /**
     * name的set方法
     * @param name 学校的名称
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * name的get方法
     * @return 学校名称
     */
    public String getName()
    {
        return name;
    }
    /**
     * size的set方法
     * @param size 学校的规模，也就是总人数
     */
    public void setSize(int size)
    {
        this.size = size;
    }

    /**
     * size的get方法
     * @return 学校的人数
     */
    public int getSize()
    {
        return size;
    }
}

