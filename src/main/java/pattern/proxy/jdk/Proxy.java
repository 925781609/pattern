package pattern.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {

  private Subjcet subjcet;

  public Object getInstance(Subjcet subjcet) throws Exception {
    this.subjcet = subjcet;

    Class<?> clazz = subjcet.getClass();

    //用来生成一个新的对象（字节码重组来实现）
    return java.lang.reflect.Proxy
        .newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    System.out.println("PreRequest");
    method.invoke(this.subjcet, args);
    System.out.println("PostRequest");

    return null;
  }

}
