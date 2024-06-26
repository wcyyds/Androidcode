package animalfeaturetest;

public class test {
    public static void main(String[] args) {
        animalfeature firstanimal=new animalfeature();
        firstanimal.tell();
        animalfeature secondanimal=new animalfeature("dog");
        secondanimal.tell();
        animalfeature thiranimal=new animalfeature("dog",5);
        thiranimal.tell();
    }
}
