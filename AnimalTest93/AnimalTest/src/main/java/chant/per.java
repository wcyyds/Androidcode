package chant;

public class per implements Runnable{
    int ticket=10;
    final String a = "";
    @Override
    public void run() {
        while (true)
        {
            synchronized (a){
                if (ticket>0)
                {
                    System.out.println(Thread.currentThread().
                            getName()+"is Selling ticket no."+ticket);
                    ticket--;
                }
                else {
                    break;
                }
            }
        }
    }
}
