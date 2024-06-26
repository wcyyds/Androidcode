package com.example.animaltest;

import java.util.Scanner;

public class Dog extends Animal {
    private String breed;
    public void dog() {
        this.breed=breed;
    }

    @Override
    public void set_breed() {
        System.out.println("Please enter animal's breed ;");
        Scanner scanner=new Scanner(System.in);
        breed=scanner.nextLine();
    }
    public void show_breed(){
        System.out.println("Animal's breed you have enter is;"+breed);
    }
}
