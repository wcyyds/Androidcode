package animalcrytest;

public class animaltest {
    public static void main(String[] args) {
        cattest cattest=new cattest();
        cattest.cry();
        dogtest dogtest=new dogtest();
        dogtest.cry();
        System.out.println("------------");
        animalcry an=new cattest();
        an.cry();
        an =new dogtest();
        an.cry();
    }
}
