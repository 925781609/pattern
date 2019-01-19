package pattern.responsibilitychain;

public class Leader extends Handler {

  public Leader() {
    super(1);
  }

  @Override
  public void report(Request request) {
    System.out.println("Leader 处理：" + request.getDetail());
  }
}

