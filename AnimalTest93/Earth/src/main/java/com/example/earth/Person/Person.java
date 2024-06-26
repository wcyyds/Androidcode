package com.example.earth.Person;


/**
 * 定义成抽象方法，只能继承不能创建实例
 * 这是一个人类，是一个抽象的类，因为不存在人类，它只是一种概念
 * 所以不能创建实例，所以就弄成了抽象类，又由于其子类并不一定要继承其他的类
 * 所以就不定义成接口了，也没有一定要子类重写的方法，所以就不提供抽象方法了。
 * @author 程序代号
 * @version 1.0
 */
public abstract class Person
{
    //类变量
    private static int eyes_num = 2;//每个人只有两个眼睛，不会变

    //成员变量， 我不希望除了本类以外内部以外，还有其他的类能够访问，
    //即使是通过调用本类的实例直接访问，只能通过我提供的方法来访问
    //只能用提供的方法访问的目的是，能够在方法里面进行一些检查，防止不合理的
    // 值被设置，比如一个人有30000岁，很明显，不可能，
    private String name,gender;
    private int age;

    //默认构造器
    public Person(){}

    //构造器1
    public Person(String name)
    {
        this.name = name;
    }
    //重载构造器
    public Person(String name, int age)
    {
        this(name);
        this.age = age;

    }
    //再次重载构造器
    public Person(String name,int age, String gender)
    {
        this(name, age);
        this.gender = gender;
    }

    /**
     * 干饭方法
     * @param thing 吃的东西的名称
     */
    public void eat(String thing)
    {
        System.out.println(name+"正在吃"+thing);
    }
    /**
     * 喝水方法
     * @param thing 喝的饮料名称
     */
    public void drink(String thing)
    {
        System.out.println(name+"正在喝"+thing);
    }
    /**
     * 玩的方法
     * @param thing 玩的东西的名称
     */
    public void play(String thing)
    {
        System.out.println(name + "正在玩" + thing);
    }
    /**
     * 睡觉方法
     */
    protected void sleep()
    {
        System.out.println(name +"正在睡觉");
    }

    //setter和getter方法
    /**
     * name的set方法
     * @param name 人的名字
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * name的get方法
     * @return 姓名
     */
    public String getName()
    {
        return name;
    }
    /**
     * age的set方法
     * @param age 人的年龄
     */
    public void setAge(int age)
    {
        this.age = age;
    }
    /**
     * age的get方法
     * @return 年龄
     */
    public int getAge()
    {
        return age;
    }
    /**
     * gender的set方法
     * @param gender 人的性别
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    /**
     * gender的get方法
     * @return 性别
     */
    public String getGender()
    {
        return gender;
    }



}



