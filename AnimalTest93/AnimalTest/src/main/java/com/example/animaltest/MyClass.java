package com.example.animaltest;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Animals are good friends of humans");
        Dog mydog=new Dog();
        while (true)
        {
            mydog.set_Animal();
            mydog.show_Animal();
            mydog.set_breed();
            mydog.show_breed();
        }
    }
}
