package pattern.strategy.enumbased;

public class Client {

  public static void main(String[] args) {
    Context context = new Context();
    context.execute(StrategyType.STRATEGYA);
  }

}
