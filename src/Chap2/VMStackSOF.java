package Chap2;

/**
 * -Xss128k
 */
public class VMStackSOF {
    private int stackLength=-1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        VMStackSOF oom=new VMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("Stack length:"+oom.stackLength);
            throw e;
        }
    }
}