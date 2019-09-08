package chap8;

public class SlotTest {
    public static void main(String[] args) {
        {
            byte[] a=new byte[64*1024*1024];
        }
        System.gc();
    }
}