package com.example.login_test03;

public class User {


    //使用懒汉单例模式，即在类加载时创建好对象
    private static final User user = new User();

    private User(){}

    public static User getInstance() {
        return user;
    }


    private String uesername;
    private String password;
    private String verification;

    public String getUesername() {
        return uesername;
    }

    public void setUesername(String uesername) {
        this.uesername = uesername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
