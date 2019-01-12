package pattern.singleton.hungry;

public class Singleton {

  private static Singleton singleton = new Singleton();

  // 私有构造方法，防止被实例化
  private Singleton() {
  }

  public static Singleton getInstance() {
    return singleton;
  }
}