package animalfeaturetest;

public class animalfeature {
    private String name;
    private int age;
    public animalfeature()
    {
        System.out.println("I have no Feature");
    }
    public animalfeature(String myname){
        name=myname;
        System.out.println("I have one Feature");
    }
    public animalfeature(String myname,int myage){
        name=myname;
        age=myage;
        System.out.println("I have tow Feature");
    }

    public void tell (){
        System.out.println("The animal's name is "+name+" and I am "+age+" years old");
    }
}
