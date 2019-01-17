package pattern.decorator;

public class ConcreteDecorator implements Component {

  private Component component;

  public ConcreteDecorator(Component component) {
    this.component = component;
  }

  @Override
  public void sampleOperation() {
    component.sampleOperation();
    // 其他业务逻辑代码
    System.out.println("为了扩展增加的代码");
  }
}
