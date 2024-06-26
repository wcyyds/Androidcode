package com.example.earth.Person;


/**
 * 学生类，因为学生也是普通人，所以就继承了普通人类
 * @author 程序代号
 * @version 1.0
 */
public class Student extends OrdinaryPerson
{
    private String number;//学生嘛，肯定会有学号的
    //默认构造器
    public Student(){}
    //
    public Student(String name, int age , String gender, String number)
    {
        super(name, age, gender);
        this.number = number;
    }
    /**
     * 学生嘛，肯定要有读书的方法
     * @param title
     */
    public void read(String title)
    {
        System.out.println(getName()+"正在读"+title);
    }
    /**
     * number 学号的set方法
     * @param number 学生的学号
     */
    public void setNumber(String number)
    {
        this.number = number;
    }
    /**
     * number学号的get方法
     * @return 学号
     */
    public String getNumber()
    {
        return number;
    }

}
