package pattern.builder;

// 声明具体建造者的公共接口
public interface Builder {

  public void BuildCPU();

  public void BuildMainBoard();

  public void BuildHD();

  public Computer getComputer();

}
