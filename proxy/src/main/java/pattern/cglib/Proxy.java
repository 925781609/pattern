package pattern.cglib;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class Proxy implements InvocationHandler {

  private Subjcet subjcet;

  public Object getInstance(Subjcet subjcet) throws Exception {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(subjcet.getClass());
    // 0和1与ProxyFilter返回的0和1对应
    Callback[] callbacks = {NoOp.INSTANCE, new ProxyInterceptor()};
    enhancer.setCallbacks(callbacks);
    // 过滤掉Object中定义的方法如toString、hashCode等
    enhancer.setCallbackFilter(new ProxyFilter());
    return enhancer.create();
    // 对于没有默认构造函数的方法，可以使用如下方式创建代理
    // Class[] argumentTypes = {Set.class, int.class};
    // Object[] arguments = {Set, 1};
    // return enhancer.create(argumentTypes, arguments);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    System.out.println("PreRequest");
    method.invoke(this.subjcet, args);
    System.out.println("PostRequest");

    return null;
  }

}
