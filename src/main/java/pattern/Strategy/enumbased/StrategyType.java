package pattern.Strategy.enumbased;

import pattern.Strategy.Strategy;
import pattern.Strategy.StrategyA;
import pattern.Strategy.StrategyB;
import pattern.Strategy.StrategyC;

public enum StrategyType {
  STRATEGYA(new StrategyA()),
  STRATEGYB(new StrategyB()),
  STRATEGYC(new StrategyC());

  private Strategy strategy;

  StrategyType(Strategy strategy) {
    this.strategy = strategy;
  }

  public Strategy get() {
    return strategy;
  }
}
