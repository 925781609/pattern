# 设计模式学习笔记

#### 前言：

设计模式是用来解决复杂问题的，借助前人经验，把复杂问题变得简单，而不要生搬硬套，为了使用设计模式而使用设计模式

经典的设计模式一共有23种，可分为创建型、结构型和行为型三大类。一些设计模式之间看起来很相似，但是目的有所不同，可以借此来区分。下文主要对一些常见的设计模式进行总结。

#### 创建型：

1. ##### 简单工厂模式

   特点： 一个工厂里所有子类都可以创建，根据传入参数选择创建哪个

   UML图：

![image](https://github.com/925781609/pattern/blob/master/doc/Simple%20factory.jpg)

​	代码示例：

```java
//根据type，选择创建HpMouse还是DellMouse， HpMouse和DellMouse时Mouse的子类
public class MouseFactory{
	public Mouse createMouse(String type){
		switch(type){
			case "Hp":
				return new HpMouse();
			case "Dell":
				return new DellMouse();
			default:
				return null;
		}
	}
} 
```

##### 2. 工厂方法模式

​	特点：每个子类，由对应的工厂创建创建

​	UML图：

![image](https://github.com/925781609/pattern/blob/master/doc/Factory%20Method.png)

代码示例：

```java
//  HpMouse由HpMouseFactory创建， DellMouse由DellMouseFactory创建
public class HpMouseFactory implements MouseFactoy{
	
	@Override
	public Mouse createMouse(){
		return new HpMouse();
	}
}
	
public class DellMouseFactory implements MouseFactory{
	
	@Override
	public Mouse createMouse(){
		return new DellMouse();
	}
}
```

3. ##### 抽象工厂模式

   当工厂模式创建多个对象时， 工厂模式即变为抽象工厂模式， 原来只创建mouse， 现在创建mouse和keyboard

   在增加子类对象数量和种类时，只需要增加即可，不需要修改原有的逻辑

   UML图：

   ![image](https://github.com/925781609/pattern/blob/master/doc/Abstract%20Factory.png)

   ##### 4.建造者模式

   ​	建造者模式按照顺序创建复杂对象(把内部的创建过程和细节隐藏起来)

   ​	UML图：

   ![image](https://github.com/925781609/pattern/blob/master/doc/Builder.jpg)

   ​	代码示例： 详细的参考https://github.com/925781609/pattern/tree/master/src/main/java/pattern/builder

   ```java
   // 具体产品，建造后对象，即为Product
   public class Computer{
   	
   	private List<String> parts=new ArrayList<>();
   	
   	public void Add(String part){
   		parts.add(part);
   	}
   	

     	public void show() {
       	for (String e : parts) {
         		System.out.println(e);
       	}
    	}
   	
   }
   	
   // 声明具体建造者的公共接口
   public interface Builder{
   	
   	public void BuildCPU();
   	
   	public void BuildMainBoard();
   	
   	public void BuildHD();
   	
   	public Computer getComputer();
   	
   }
   	
   // 具体的建造者
   public class ConcreteBuilder implements Builder{
   	
   	Computer computer=new Computer();
   	
   	@Override
   	public void BuildCPU(){
   		computer.Add("组装CPU");
       }
   	
   	@Override
   	public void BuildMainBoard(){
   		computer.Add("组装主板");
       }
   	
   	@Override
   	public void BuildHD(){
   		computer.Add("组装硬盘");
       }
   	
   	@Override
   	public Computer getComputer(){
   		return computer;
       }
   }
   	
   // 负责控制产品对象的生产工程
   public class Director{
   	
   	public void construct(Builder builder){
   		builder.BuildMainBoard();
   		builder.BuildCPU();
   		builder.BuildHD();
   	}
   }

   // 建造者使用示例
   public class Client {

     public static void main(String[] args) {
       Director director = new Director();
       Builder builder = new ConcreteBuilder();

       // 开始建造对象
       director.construct(builder);

       // 获得建造的对象
       Computer computer = builder.getComputer();

       computer.show();
     }
   }
   ```


#####    5.单例模式

​	保证一个类仅有一个实例， 并提供一个访问它的全局访问点

​	应用场景： Listener本身单例， 日历Calander， IoC容器， 配置信息Config

​	技术方案：保证运行过程中只有一份 

​	注意事项： 单例模式注意构造函数 定义为private，防止外部调用 ，此外极端情况下还需要考虑克隆、反序列化、反射对单例的破坏，可以参考：https://zhuanlan.zhihu.com/p/28491630

​	技术实现方案包括: (此外还有枚举法)

  1.  饿汉式：

      在类加载的时候立即初始化， 并且创建单例对象

      优点： 没有任何的锁， 执行效率高， 绝对线程安全（在线程还没有出现之前就已经实例化了）

      缺点： 类架子的时候就初始化， 不管是否使用都占用空间

      代码示例：

      ```java
      public class Singleton {

        private static Singleton singleton = new Singleton();

        // 私有构造方法，防止被实例化
        private Singleton() {
        }

        public static Singleton getInstance() {
          return singleton;
        }
      }

      ```

  2.  懒汉式： 默认不实例化， 只有使用的时候才实例化， 又可以分为静态内部类、双重锁检查、登记式

      静态内部类：  内部类只有在外部类被调用时，才会被加载，内部类在方法 调用前被初始化


     ```java
     public class Singleton {
    
       // 私有构造方法，防止被实例化
       private Singleton() {
       }
    
       private static class SingletonHolder {
         private static final Singleton INSTANCE = new Singleton();
       }
    
       private static final Singleton getInstance() {
         return SingletonHolder.INSTANCE;
       }
     }
     ```

 双重锁检查：

 ```java
 public class Singleton {

   // 对singleton对象使用volatile关键字进行限制，保证其对所有线程的可见性，并且禁止对其进行指令重排序优化。
   private static volatile Singleton singleton = null;

   private Singleton() {
   }

   public static Singleton getSingleton() {
     if (singleton == null) {
       synchronized (Singleton.class) {
         if (singleton == null) {
           singleton = new Singleton();
         }
       }
     }
     return singleton;
   }
 }
 ```

 注册登记式（spring种常用，待补充）

6. ##### 原型模式

#### 结构型模式：

   ##### 1.代理模式

   技术手段： jdk proxy，cglib（字节码重组框架），AspectJ（spring）， asm

   代理角色通常会持有被代理角色对象的引用，以便代理角色完成工作之前或之后找到被代理对象，能够通知被代理对象

   代理分静态代理和动态代理：

   1. 静态代理：

      有目标类的引用，但是类型固定（为目标类）， 否则无法使用目标类的方法

      需要定义接口或者父类，代理对象与被代理对象一起实现相同的接口或是继承相同的父类

   2. 动态代理:（通过字节码重组）

      JDK代理： 目标类要实现某一接口

      ​	生成一个新的类，新的类实现了被代理类的所有实现。在新生成类的方法中调用目标类原来的方法

      CGLIB代理： 不需要实现相同的接口

      ​	 通过字节码重组生成新的类，继承目标类

   UML：

   	![image](https://github.com/925781609/pattern/blob/master/doc/Proxy.jpg)

   代码实现:  关于动态代理的实现详见https://github.com/925781609/pattern/tree/master/src/main/java/pattern/proxy

   ```java
   interface Subject {

     void request();
   }

   class RealSubject implements Subject {

     public void request() {
       System.out.println("request");
     }
   }

   class Proxy implements Subject {

     private Subject subject;

     public Proxy(Subject subject) {
       this.subject = subject;
     }

     @Override
     public void request() {
       System.out.println("PreProcess");
       subject.request();
       System.out.println("PostProcess");
     }
   }

   // client
   public class Client {

     public static void main(String[] args) {
       RealSubject subject = new RealSubject();
       Proxy p = new Proxy(subject);
       p.request();
     }
   }
   ```

 ##### 2.适配器模式
定义一个包装类， 把适配的类(Adaptee)的api转换成目标类(Target)的api

强调的是兼容， 不改变原来的代码也能实现新的需求， 类似于VGA-HDMI

不想去修改老的比较稳定的系统，但是为了兼容新的需求和标准， 不得不在系统上再去做一些文章，向下兼容，

原来的类如果不再使用， 需要打上Deprecated注解，

1. UML图：

1) 类适配器：继承原来的类，并且不覆写原来的方法，增加新的方法

![img](https://github.com/925781609/pattern/blob/master/doc/Class%20Adapter.png)

2) 对象适配器：将原来的类注入进来，调用原来的方法

![img](https://github.com/925781609/pattern/blob/master/doc/Object%20Adapter.png)

2. 代码示例：

   ```java
   public interface Target {

     //这是源类Adapteee没有的方法 
     public void Request();
   }

   // 源类
   public class Adaptee {

     public void SpecificRequest() {
       System.out.println("SpecificRequest in Adaptee");
     }
   }

   // 适配器类
   //适配器Adapter继承自Adaptee，同时又实现了目标(Target)接口。 
   public class Adapter extends Adaptee implements Target {

     //所以适配器只是将SpecificRequest()方法作了一层封装，封装成Target可以调用的Request()而已 
     @Override
     public void Request() {
       this.SpecificRequest();
     }
   }
   ```

 ##### 3.装饰器模式

​委派+适配器 为了扩展和增强，要满足is-a关系（同源同宗，也不一定有is-a关系，只要是增强就算）

为了某个实现类在不修改原始类的基础上，进行动态地覆盖或增加方法原来的功能依旧对外开放， 依旧保留，新的功能同样也可以使用
​	

1. 应用场景： IO流，DataSource是connection的装饰器，Spring中Detector结尾， Wrapper结尾

2. UML 图： （原始版本比较复杂，实际都是用其简化版

   ![img](https://github.com/925781609/pattern/blob/master/doc/Decorator.png)

3. 代码示例：

```java
public interface Component {
  void sampleOperation();
}

public class ConcreteComponent implements Component {
  @Override
  public void sampleOperation() {
    System.out.println("sampleOperation method in ConcreteComponent");
  }
}

public class ConcreteDecorator implements Component {

  private Component component;

  public ConcreteDecorator(Component component) {
    this.component = component;
  }

  @Override
  public void sampleOperation() {
    component.sampleOperation();
    // 其他业务逻辑代码
    System.out.println("为了扩展增加的代码");
  }
}
```

装饰器和适配器模式的区别

| 装饰器模式 | 是一种非常特别的适配器模式 | 装饰者 is-a 被装饰者             | 注重的是覆盖和扩展 |
| ----- | ------------- | ------------------------- | --------- |
| 适配器模式 |               | 可以使用代理(has-a)或者继承(is-a)实现 | 注重的是兼容和转换 |

#### 结构型模式：
##### 1.策略模式

准备一组算法，将每一个算法封装起来，让外部按需调用 ，     算法彼此之间可以互换（比如打折），用户对算法实现无需了解，只需要去选择

1. 应用场景：

   BeanFactory 根据用户配置去选择不同的beanFactory， 通常和抽象工厂模式结合使用

   比如支付方式、登录方法， 比较器(sort 方法传comparator)

2. UML图

   ![image](https://github.com/925781609/pattern/blob/master/doc/Strategy.png)

3. 代码示例

   基于枚举的策略模式请参考： https://github.com/925781609/pattern/tree/master/src/main/java/pattern/strategy/enumbased

   ```java
   public interface Strategy {

     double calcPrice(double booksPrice);

   }

   public class StrategyA implements Strategy {

     @Override
     public double calcPrice(double booksPrice) {
       return booksPrice;
     }

   }

   public class StrategB implements Strategy {

     @Override
     public double calcPrice(double booksPrice) {
       return booksPrice * 0.9;
     }

   }

   // context
   public class Price {

     //持有一个具体的策略对象
     private Strategy strategy;

     public Price(Strategy strategy) {
       this.strategy = strategy;
     }

     //计算价格
     public double quote(double booksPrice) {
       return this.strategy.calcPrice(booksPrice);
     }
   }

   // StrategyClinet
   public class StrategyClient {

     Strategy strategy = new StrategyA();
     //创建环境
     Price price = new Price(strategy);
     //计算价格
     double quote = price.quote(300);
   }
   ```

##### 2.模板方法模式

##### 3.委派模式
##### 4.观察者模式
