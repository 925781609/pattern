package pattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

  /**
   * 用来保存注册的观察者对象
   */
  private List<Observer> observers = new ArrayList<Observer>();

  /**
   * 注册观察者对象
   *
   * @param observer 观察者对象
   */
  public void attach(Observer observer) {

    observers.add(observer);
    System.out.println("Attached an observer");
  }

  /**
   * 删除观察者对象
   *
   * @param observer 观察者对象
   */
  public void detach(Observer observer) {

    observers.remove(observer);
  }

  /**
   * 通知所有注册的观察者对象
   */
  public void nodifyObservers(String newState) {

    for (Observer observer : observers) {
      observer.update(newState);
    }
  }
}
