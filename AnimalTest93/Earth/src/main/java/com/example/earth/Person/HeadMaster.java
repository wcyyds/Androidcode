package com.example.earth.Person;

/**
 * 班主任，他虽然是班主任，但同时也会是一名老师
 * @author 程序代号
 * @version 1.0
 */
public class HeadMaster extends Teacher
{

    public String classroom; // 是哪个班级的

    //提供一个默认构造器
    public HeadMaster(){}
    public HeadMaster(String name , int age, String gender,String subject, String classroom)
    {
        super(name,age, gender, subject);
        this.classroom = classroom;
    }

    /**
     * 班主任嘛，总有一个监督的方法
     */
    public void check()
    {
        System.out.println(getName()+"班主任正在监督"+classroom);
    }
    /**
     * classroom的set方法
     * @param classroom 班主任监督的是哪个班级
     */
    public void setClassRoom(String classroom)
    {
        this.classroom = classroom;
    }
    /**
     * classroom的get方法
     * @return 班级的名称
     */
    public String getClassRoom()
    {
        return classroom;
    }


}

