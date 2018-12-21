package pattern.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.CallbackFilter;

public class ProxyFilter implements CallbackFilter {

  @Override
  public int accept(Method method) {
    // 对于Object定义的方法，如toString、hashCode不进行埋点
    if (method.getDeclaringClass().equals(Object.class)) {
      return 0;

    } else {
      return 1;
    }

  }
}
