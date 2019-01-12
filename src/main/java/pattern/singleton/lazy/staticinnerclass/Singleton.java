package pattern.singleton.lazy.staticinnerclass;

public class Singleton {

  // 私有构造方法，防止被实例化
  private Singleton() {
  }

  private static class SingletonHolder {
    private static final Singleton INSTANCE = new Singleton();
  }

  private static final Singleton getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
