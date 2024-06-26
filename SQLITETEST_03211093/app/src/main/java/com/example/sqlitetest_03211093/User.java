package com.example.sqlitetest_03211093;

public class User {
    String name;
    String psw;
    String phone;
    public User(String name,String psw,String phone){
        this.name=name;
        this.psw=psw;
        this.phone=phone;
    }
    @Override
    public String toString(){return name + ',' + psw + ',' + phone;}
}
