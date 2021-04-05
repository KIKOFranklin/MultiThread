package SychronizedReview;

public class Review01 {
    public static Integer tickets = 100;

    public static void main(String[] args) {

        Review01 review01 = new Review01();


        new Thread(() -> review01.getTickets(tickets)).start();
        new Thread(() -> review01.getTickets(tickets)).start();

    }

    public void getTickets(Integer tickets){
        int consumeCount = 1;
        synchronized (this){
            for(int i = 0; i < 10 ; i++){
                tickets = tickets - consumeCount;
                System.out.println("当前票数为："+tickets);
                System.out.println("购买者："+Thread.currentThread().getName());
            }
        }

    }
}
