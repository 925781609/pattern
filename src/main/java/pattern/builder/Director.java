package pattern.builder;

// 负责控制产品对象的生产工程
public class Director {

  public void construct(Builder builder) {
    builder.BuildMainBoard();
    builder.BuildCPU();
    builder.BuildHD();
  }
}