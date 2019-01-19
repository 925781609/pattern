package pattern.responsibilitychain;

public class Boss extends Handler {

  public Boss() {
    super(2);
  }

  @Override
  public void report(Request request) {
    System.out.println("Boss 处理：" + request.getDetail());
  }
}

