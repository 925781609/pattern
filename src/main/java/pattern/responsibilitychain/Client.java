package pattern.responsibilitychain;

public class Client {

  public static void main(String[] args) {
    Request requestLevel2 = new Request(2, "Level 2的请求"); // 请求等级高

    Boss boss = new Boss();
    Leader leader = new Leader();
    // 设置下一级
    leader.setNextHandler(boss);

    System.out.println("==============开始处理请求=========");
    leader.handleMessage(requestLevel2);
  }
}
