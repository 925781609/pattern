package pattern.delegate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet {

  private List<Handler> handlerMapping = new LinkedList<>();

  public DispatcherServlet() {
    try {
      // 实际spring项目会在启动之初，扫描所有带RequestMapping注解的类，放到handlerMapping中
      Class<?> userControllerClass = UserController.class;
      handlerMapping.add(new Handler()
          .setController(userControllerClass.newInstance())
          .setMethod(userControllerClass.getMethod("getUserById", new Class[]{String.class}))
          .setUrl("/api/user"));
    } catch (Exception e) {

    }
  }


  public void doService(HttpServletRequest request, HttpServletResponse response) {
    doDispatch(request, response);
  }


  private void doDispatch(HttpServletRequest request, HttpServletResponse response) {

    // 1、获取用户请求的URL
    String uri = request.getRequestURI();

    //   根据用户请求的URL，去找到这个url对应的某一个java类的方法

    // 2、Servlet拿到URL以后，要做权衡（要做判断，要做选择）
    // 3、通过拿到的URL去handlerMapping（可以认为是策略常量）
    Handler handle = null;
    for (Handler h : handlerMapping) {
      if (uri.equals(h.getUrl())) {
        handle = h;
        break;
      }
    }

    // 4、将具体的任务分发给Method（通过反射去调用其对应的方法）
    Object object = null;
    try {
      object = handle.getMethod().invoke(handle.getController(), request.getParameter("mid"));
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    // 5、获取到Method执行的结果，通过Response返回出去
    // response.getWriter().write();

  }


  class Handler {

    private Object controller;
    private Method method;
    private String url;

    public Object getController() {
      return controller;
    }

    public Handler setController(Object controller) {
      this.controller = controller;
      return this;
    }

    public Method getMethod() {
      return method;
    }

    public Handler setMethod(Method method) {
      this.method = method;
      return this;
    }

    public String getUrl() {
      return url;
    }

    public Handler setUrl(String url) {
      this.url = url;
      return this;
    }
  }

}
