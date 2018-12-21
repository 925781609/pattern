package pattern.proxy.jdk;

import java.io.FileOutputStream;
import sun.misc.ProxyGenerator;

public class Client {

  public static void main(String args[]) throws Exception {
    Subjcet subjcet = (Subjcet) new Proxy().getInstance(new RealSubject());
    subjcet.request();

    /**
     * 通过反编译工具可以查看源代码
     */
    // 输出: class com.sun.proxy.$Proxy0
    System.out.println(subjcet.getClass());

    // $Proxy1 只是生成的class名称，以$开头一般时生成的内部类
    byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy1", new Class[]{Subjcet.class});
    // 请先确保路径存在
    FileOutputStream os = new FileOutputStream("C://test//$Proxy1.class");
    os.write(bytes);
    os.close();
  }
}


/*  生成的$Proxy1.class内容如下
public final class $Proxy1 extends Proxy implements Subjcet {
  private static Method m1;
  private static Method m2;
  private static Method m3;
  private static Method m0;

  public $Proxy1(InvocationHandler var1) throws  {
    super(var1);
  }

  public final boolean equals(Object var1) throws  {
    try {
      return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
    } catch (RuntimeException | Error var3) {
      throw var3;
    } catch (Throwable var4) {
      throw new UndeclaredThrowableException(var4);
    }
  }

  public final String toString() throws  {
    try {
      return (String)super.h.invoke(this, m2, (Object[])null);
    } catch (RuntimeException | Error var2) {
      throw var2;
    } catch (Throwable var3) {
      throw new UndeclaredThrowableException(var3);
    }
  }

  public final void request() throws  {
    try {
      super.h.invoke(this, m3, (Object[])null);
    } catch (RuntimeException | Error var2) {
      throw var2;
    } catch (Throwable var3) {
      throw new UndeclaredThrowableException(var3);
    }
  }

  public final int hashCode() throws  {
    try {
      return (Integer)super.h.invoke(this, m0, (Object[])null);
    } catch (RuntimeException | Error var2) {
      throw var2;
    } catch (Throwable var3) {
      throw new UndeclaredThrowableException(var3);
    }
  }

  static {
    try {
      m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
      m2 = Class.forName("java.lang.Object").getMethod("toString");
      m3 = Class.forName("pattern.proxy.jdk.Subjcet").getMethod("request");
      m0 = Class.forName("java.lang.Object").getMethod("hashCode");
    } catch (NoSuchMethodException var2) {
      throw new NoSuchMethodError(var2.getMessage());
    } catch (ClassNotFoundException var3) {
      throw new NoClassDefFoundError(var3.getMessage());
    }
  }
}

 */