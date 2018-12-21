package pattern.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyInterceptor implements MethodInterceptor {


  @Override
  public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    Object result = null;
    System.out.println("PreRequest");
    result = methodProxy.invokeSuper(object, args);
    System.out.println("PostRequest");
    return result;
  }
}
