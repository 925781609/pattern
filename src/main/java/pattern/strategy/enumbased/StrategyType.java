package pattern.strategy.enumbased;

import pattern.strategy.Strategy;
import pattern.strategy.StrategyA;
import pattern.strategy.StrategyB;
import pattern.strategy.StrategyC;

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
