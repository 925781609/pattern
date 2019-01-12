package pattern.builder;

// 具体的建造者
public class ConcreteBuilder implements Builder {

  Computer computer = new Computer();

  @Override
  public void BuildCPU() {
    computer.Add("组装CPU");
  }

  @Override
  public void BuildMainBoard() {
    computer.Add("组装主板");
  }

  @Override
  public void BuildHD() {
    computer.Add("组装硬盘");
  }

  @Override
  public Computer getComputer() {
    return computer;
  }
}