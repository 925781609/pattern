package pattern.proxy.cglib;

public class RealSubject implements Subjcet {

  @Override
  public void request() {
    System.out.println("request");
  }
}
