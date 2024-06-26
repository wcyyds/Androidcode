package com.example.earth;

import com.example.earth.Building.School;
import com.example.earth.Person.HeadMaster;
import com.example.earth.Person.OrdinaryPerson;
import com.example.earth.Person.Student;
import com.example.earth.Person.Teacher;

/**
 * 程序运行用的主类，也可以叫做入口类，
 * 一些类在这里被创建，一些方法，在这里被调用
 * @author 程序代号
 * @version 1.0
 */
public class Main
{

    public static void main(String[] args)
    {

        //创建一个普通人，并调用他从人类那里继承的方法和构造器
        OrdinaryPerson op = new OrdinaryPerson("小王",300,"男");

        op.play("小猪佩奇");
        op.run();
        op.eat("面包");
        op.drink("牛奶");
        System.out.println(op.toString());

        //再创建一个老师类
        Teacher teacher = new Teacher("老李",2000,"女","美术");
        teacher.teach();
        teacher.run();
        teacher.laugh();
        teacher.eat("红薯");
        System.out.println(teacher.toString());

        //创建一个班主任类
        HeadMaster hm = new HeadMaster("林柳", 3000,"男", "语文","3年2班");
        hm.eat("炸鸡");
        hm.check();
        hm.run();
        System.out.println(hm.toString());

        //创建一个学生类
        Student student = new Student("王二麻子", 20000,"女","1234567");
        student.run();
        student.read("九阴真经");
        System.out.println(student.toString());

        //创建一个学校类
        School school = new School(1888,"修仙研究学院",820);
        school.open();
        school.close();
        System.out.println(school.toString());

        //比较两个ordinaryperson是否相等
        OrdinaryPerson op1 = new OrdinaryPerson("小王",200,"男");
        System.out.println(op.equals(op1));

        OrdinaryPerson op2 = new OrdinaryPerson("小李",299,"女");
        System.out.println("小王和小李是否是同一个人："+op1.equals(op2));
        System.out.println("小王和小王是否是同一个人："+op1.equals(op1));

        //比较两个Teacher
        Teacher t1 = new Teacher("刘彤",100,"女", "数学");
        Teacher t2 = new Teacher("溜溜", 200, "男", "英语");
        System.out.println("刘彤是否和溜溜相等"+t1.equals(t2));
        System.out.println("溜溜是否和溜溜相等"+t2.equals(t2));
       //比较两个班主任
        HeadMaster h1 = new HeadMaster("往往",100,"男","语文","5年7班");
        HeadMaster h2 = new HeadMaster("牛牛",200,"女", "考古", "8年9班");
        System.out.println("往往是否和牛牛是同一个人："+h1.equals(h2));
        System.out.println("牛牛是否和牛牛是同一个人："+h2.equals(h2));

    }
}
