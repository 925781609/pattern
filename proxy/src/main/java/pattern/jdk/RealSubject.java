package pattern.jdk;

public class RealSubject implements Subjcet {

  @Override
  public void request() {
    System.out.println("request");
  }
}
