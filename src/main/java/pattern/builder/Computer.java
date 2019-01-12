package pattern.builder;

import java.util.ArrayList;
import java.util.List;

// 具体产品，建造后对象，即为Product
public class Computer {

  private List<String> parts = new ArrayList<>();

  public void Add(String part) {
    parts.add(part);
  }

  public void show() {
    for (String e : parts) {
      System.out.println(e);
    }
  }

}
