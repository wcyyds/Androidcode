package com.example.earth.Person;


/**
 * 普通人，这个类是一个普通人的一个类，
 * 没有什么特殊的地方，
 * 有一个普通的跑步的方法
 * 有一个普通的笑的方法
 * @author 程序代号
 * @version 1.0
 */
public class OrdinaryPerson extends Person
{

    public OrdinaryPerson(){}//默认构造器
    //居然不能直接用Person的构造器
    public OrdinaryPerson(String name,int age,String gender)
    {
        super(name, age, gender);
    }

    /**
     * 普通的跑步
     */
    public void run()
    {
        System.out.println(getName() + "普通的跑步");

    }
    /**
     * 普通的笑
     */
    public void laugh()
    {
        System.out.println(getName()+"普通的笑");
    }

    public String toString()
    {
        return "普通人姓名："+getName()+
                "\n普通人年龄："+getAge()+
                "\n普通人性别："+getGender();
    }

    //重写equals方法
    public boolean equals(Object obj)
    {
        //先看看是不是和指向同一个对象
        if(this == obj)
            return true;
        //如果不为null ,并且是同一个类的实例
        if( obj != null && obj.getClass() == OrdinaryPerson.class)
        {
            //并且name相同
            //先强制类型转换
            var ordinaryPerson = (OrdinaryPerson) obj;
            //并且姓名相同
            if(this.getName().equals(ordinaryPerson.getName()))
            {
                return true;
            }

        }
        return false;

    }


}
