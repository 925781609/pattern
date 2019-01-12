package pattern.strategy;

public class StrategyB implements Strategy {

  @Override
  public void doSomething() {
    System.out.println("具体策略B");
  }
}
