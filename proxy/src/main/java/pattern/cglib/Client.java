package pattern.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class Client {

  public static void main(String args[]) throws Exception {
    // 通过以下方式可以打印生成的class
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\\\test");
    Subjcet subjcet = (Subjcet) new Proxy().getInstance(new RealSubject());
    subjcet.request();
  }
}

