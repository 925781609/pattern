package pattern.observer;

public class Client {

  public static void main(String[] args) {
    //创建主题对象
    ConcreteSubject subject = new ConcreteSubject();
    //创建观察者对象
    Observer observer = new ConcreteObserver();
    //将观察者对象登记到主题对象上
    subject.attach(observer);
    //改变主题对象的状态
    subject.change("new state");
  }

}