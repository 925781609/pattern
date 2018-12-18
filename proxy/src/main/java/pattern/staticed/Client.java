package pattern.staticed;

public class Client {

  public static void main(String args[]) {
    RealSubject subject = new RealSubject();
    Proxy p = new Proxy(subject);
    p.request();
  }
}
