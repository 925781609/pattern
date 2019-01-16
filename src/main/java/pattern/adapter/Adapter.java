package pattern.adapter;

// 适配器类
//适配器Adapter继承自Adaptee，同时又实现了目标(Target)接口。
public class Adapter extends Adaptee implements Target {

  //所以适配器只是将SpecificRequest()方法作了一层封装，封装成Target可以调用的Request()而已
  @Override
  public void Request() {
    this.SpecificRequest();
  }
}