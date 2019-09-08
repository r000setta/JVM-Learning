package chap12;

import java.util.concurrent.CountDownLatch;

public class VolatileTest {
    public static volatile int race=0;

    public static void inc(){
        race++;
    }

    private static final int COUNT=10;

    public static void main(String[] args) throws Exception{
        final CountDownLatch latch=new CountDownLatch(COUNT);
        for (int i=0;i<COUNT;i++){
            new Thread(() -> {
                for (int j=0;j<1000;j++){
                    inc();
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println(race);
    }
}