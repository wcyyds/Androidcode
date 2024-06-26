package chant;

public class maipiao {
    public static void main(String[] args) {
        per per=new per();
        Thread t1=new Thread(per);
        Thread t2=new Thread(per);
        Thread t3=new Thread(per);
        t1.start();
        t2.start();
        t3.start();
    }
}
