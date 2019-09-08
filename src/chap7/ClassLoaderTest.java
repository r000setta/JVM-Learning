package chap7;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器演示
 * JVM中存在两个ClassLoaderTest类，一个是系统应用程序类加载器加载的，一个是由自己定义的加载器加载的。
 * 属于不同的类。
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        ClassLoader myLoader=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName=name.substring(name.indexOf(".")+1)+".class";
                    InputStream is=getClass().getResourceAsStream(fileName);
                    if (is==null)
                        return super.loadClass(name);
                    byte[] b=new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj=myLoader.loadClass("chap7.ClassLoaderTest").newInstance();
        obj.getClass().getMethod("hello").invoke(obj);
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }

    public void hello(){
        System.out.println("Hello");
    }
}