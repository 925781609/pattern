package pattern.template;

public class ConcreteTemplate extends AbstractTemplate {

  @Override
  public void abstractMethod() {
    System.out.println("ConcreteTemplate abstractMethod");
  }

  /**
   * 不使用父类默认实现的hookMethod，自己重新定义hookMethod
   */
  @Override
  public void hookMethod() {
    System.out.println("ConcreteTemplate hookMethod");
  }

}
