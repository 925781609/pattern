package pattern.strategy.enumbased;

public class Context {

  public Context() {
  }

  public void execute(StrategyType strategyType) {
    strategyType.get().doSomething();
  }
}
