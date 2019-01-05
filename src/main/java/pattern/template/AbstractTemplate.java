package pattern.template;

public abstract class AbstractTemplate {

  // 模板方法, 定义成final， 防止子类overwrite
  final public void templateMethod() {
    // 调用基本方法(由子类实现)
    hookMethod();
    // 调用基本方法(由子类实现)
    abstractMethod();
    // 调用基本方法(已经实现)
    concreteMethod();
  }

  // 基本方法的声明（由子类实现，但抽象模板给出了默认实现）
  public void hookMethod() {
    System.out.println("Default hookMethod");
  }

  // 基本方法的声明（由子类实现）
  public abstract void abstractMethod();

  // 基本方法（已经实现）
  public final void concreteMethod() {
    System.out.println("AbstrateTemplate concreteMethod");
  }
}
