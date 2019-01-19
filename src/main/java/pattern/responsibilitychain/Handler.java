package pattern.responsibilitychain;

public abstract class Handler {

  private Handler nextHandler;
  private int level;

  public Handler(int level) {
    this.level = level;
  }

  // 处理请求传递，注意final，子类不可重写
  public final void handleMessage(Request request) {
    if (level == request.getLevel()) {
      this.report(request);
    } else {
      if (this.nextHandler != null) {
        System.out.println("自己无法处理，传递给下一级");
        this.nextHandler.handleMessage(request);
      } else {
        System.out.println("处理链到达尽头，无法处理请求");
      }
    }
  }

  public void setNextHandler(Handler handler) {
    this.nextHandler = handler;
  }

  // 抽象方法，子类实现
  public abstract void report(Request request);

}
