package pattern.Strategy;

public class StrategyA implements Strategy {

  @Override
  public void doSomething() {
    System.out.println("具体策略A");
  }
}
