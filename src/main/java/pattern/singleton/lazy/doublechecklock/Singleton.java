package pattern.singleton.lazy.doublechecklock;


public class Singleton {

  // 对singleton对象使用volatile关键字进行限制，保证其对所有线程的可见性，并且禁止对其进行指令重排序优化。
  private static volatile Singleton singleton = null;

  private Singleton() {
  }

  public static Singleton getSingleton() {
    if (singleton == null) {
      synchronized (Singleton.class) {
        if (singleton == null) {
          singleton = new Singleton();
        }
      }
    }
    return singleton;
  }
}