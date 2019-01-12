package pattern.builder;


// 建造者使用示例
public class Client {

  public static void main(String[] args) {
    Director director = new Director();
    Builder builder = new ConcreteBuilder();

    // 开始建造对象
    director.construct(builder);

    // 获得建造的对象
    Computer computer = builder.getComputer();

    computer.show();
  }
}