package pattern.decorator;

public class Client {

  public static void main(String[] args) {
    Component component = new ConcreteComponent();
    ConcreteDecorator concreteDecorator = new ConcreteDecorator(component);
    concreteDecorator.sampleOperation();
  }

}
