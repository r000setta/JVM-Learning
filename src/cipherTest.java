import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;

public class cipherTest {
    public static String dec(String enc){
        try {
            SecretKeySpec specs=new SecretKeySpec(MessageDigest.getInstance("MD5").digest("com.pore.mylibrary.PoRELab".getBytes("utf-8")),"AES");
            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,specs);
            byte[] original=cipher.doFinal(enc.getBytes());
            return new String(original);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws Exception{
        System.out.println(dec("86c62e54e937472b0d407406c5c6e3c3955384c0b154d41d151c8b4f9d220ef6559ec1816d084d335067f2ea432ceead1a9d2dcac31999164eec506c4416e3c3"));
    }

}