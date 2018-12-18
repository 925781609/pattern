package pattern.staticed;


public class Proxy implements Subjcet {

  private Subjcet subjcet;

  public Proxy(Subjcet subjcet) {
    this.subjcet = subjcet;
  }

  @Override
  public void request() {
    System.out.println("PreRequest");
    subjcet.request();
    System.out.println("PostRequest");
  }
}
