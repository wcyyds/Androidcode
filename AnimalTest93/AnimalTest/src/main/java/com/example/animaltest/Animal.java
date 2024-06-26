package com.example.animaltest;

import java.util.Scanner;

public abstract class Animal {
    public Animal(){
        System.out.println("This is Animal class constructer");
    }
    String name;
    String eat;

    public void set_Animal() {
        System.out.println("Please enter animal's name");
        Scanner scanner = new Scanner(System.in);
        name=scanner.nextLine();
        System.out.println("Please enter animal's food");
        eat=scanner.nextLine();
    }
    public void show_Animal(){
        System.out.println("Animal's Information you have enter is;"+name+" eats "+eat);
    }

    public abstract void set_breed();
}
