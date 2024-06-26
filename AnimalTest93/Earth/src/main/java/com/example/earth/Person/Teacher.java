package com.example.earth.Person;


/**
 * 老师，因为老师其实也是一个普通人，所以就
 * 继承了普通人类，
 * @author 程序代号
 * @version 1.0
 */
public class Teacher extends OrdinaryPerson
{
    //成员变量
    private String subject; // 教授的科目

    //因为子类并不会继承父类的构造器，所以需要重写一下
    //不知道能不能直接用爷爷类的，不能
    //再提供一个默认的构造器

    public Teacher(){}


    public Teacher(String name, int age, String gender,String subject)
    {
        super(name,age, gender);
        this.subject = subject;
    }

    /**
     * 上课方法
     */
    public void teach()
    {
        System.out.println(getName() + "正在教学生"+subject);
    }

    /**
     * subject的set方法
     * @param subject 老师教学的科目
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    /**
     * subject的get方法
     */
    public String getSubject()
    {
        return subject;
    }
    public String toString()
    {
        return "老师姓名："+getName()+
                "\n老师年龄："+getAge()+
                "\n老师性别："+getGender()+
                "\n老师教授的科目："+getSubject();
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        if(obj != null && obj.getClass() == Teacher.class)//如果他们的name相同就认为是同一个对象
        {
            Teacher teacher = (Teacher) obj;
            if(this.getName().equals(teacher.getName()))
            {
                return true;
            }
        }
        return false;
    }




}
