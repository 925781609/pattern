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
	public static Mouse createMouse(String type) throws IllegalArgumentException{
		switch(type){
			case "Hp":
				return new HpMouse();
			case "Dell":
				return new DellMouse();
		}
         throws new IllegalArgumentException("the type not found");
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

![img](https://github.com/925781609/pattern/blob/master/doc/Proxy.jpg)

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

   1)  类适配器：继承原来的类，并且不覆写原来的方法，增加新的方法

![img](https://github.com/925781609/pattern/blob/master/doc/Class%20Adapter.png)

​	2) 对象适配器：将原来的类注入进来，调用原来的方法

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

##### 4. 门面模式

1. 应用场景
2. UML图
3. 代码示例



#### 结构型模式：

##### 1.策略模式

准备一组算法，将每一个算法封装起来，让外部按需调用 ，     算法彼此之间可以互换（比如打折），用户对算法实现无需了解，只需要去选择

1. 应用场景：

   BeanFactory 根据用户配置去选择不同的beanFactory， 通常和抽象工厂模式结合使用

   比如支付方式、登录方法， 比较器(sort 方法传comparator)

2. UML图

   ![image](https://github.com/925781609/pattern/blob/master/doc/Strategy.png)

3. 代码示例

   通过在Context种传入具体的策略，避免在内部使用if-else

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

基于枚举的策略模式请参考： https://github.com/925781609/pattern/tree/master/src/main/java/pattern/strategy/enumbased

##### 2.模板方法模式

流程固定，某一环节有差异，将相同部分的代码放在抽象的父类中，而将不同的代码放入不同的子类中。

父类中定义模板方法，定义了子类方法的执行过程， 并且父类定义的模板方法不能被修改（final）

1. 应用场景：jdbcTemplate，工作流，redisTemplate， Spring Template

2. UML图

   ![img](https://github.com/925781609/pattern/blob/master/doc/Template%20Method.jpg)

3. 代码示例：

   ```java
   public abstract class AbstractTemplate {

     // 模板方法, 定义成final， 防止子类overwrite
     final public void templateMethod() {
       // 调用基本方法(由子类实现)
       hookMethod();
       // 调用基本方法(由子类实现)
       abstractMethod();
       // 调用基本方法(已经实现)
       concreteMethod();
     }

     // 基本方法的声明（由子类实现，但抽象模板给出了默认实现）
     public void hookMethod() {
       System.out.println("Default hookMethod");
     }

     // 基本方法的声明（由子类实现）
     public abstract void abstractMethod();

     // 基本方法（已经实现）
     public final void concreteMethod() {
       System.out.println("AbstrateTemplate concreteMethod");
     }
   }

   public class ConcreteTemplate extends AbstractTemplate {

     @Override
     public void abstractMethod() {
       System.out.println("ConcreteTemplate abstractMethod");
     }

     /**
      * 不使用父类默认实现的hookMethod，自己重新定义hookMethod
      */
     @Override
     public void hookMethod() {
       System.out.println("ConcreteTemplate hookMethod");
     }

   }

   ```

   **策略模式和模板方法模式的区别：**

   策略模式只有选择权， 由用户自己选择已有的固定算法

   模板模式： 可以参与某一部分的自定义，但无法改变流程

##### 3.委派模式

不是23种设计模式的一种，但是spring种比较常用： 客户请求时Boss，DispatcherServlet是委派者， Controller是被委派者

代理+ 策略：委派模式是代理模式特殊情况(委派者要持有被委派者的引用)，全权代理，通常有策略模式作干预。 

代理模式注重过程，而委派模式注重结果

策略模式注重的是可扩展（外部扩展）， 委派模式注重内部的灵活和复用

1. 应用场景：以Delegate/Dispatcher结尾的，NIO的selector就是委派模式

2. UML图：（这里以Spring的DispatcherServlet为例）

   ![img](https://github.com/925781609/pattern/blob/master/doc/Delegate.png)

3. 代码示例：

   ```java
   // 真正干活的员工
   // @RestController
   public class UserController {

     // @RequestMapping(.....)
     public Object getUserById(String id) {
       return "Something";
     }
   }

   // 类似于项目经历分配任务
   public class DispatcherServlet {

     private List<Handler> handlerMapping = new LinkedList<>();

     public DispatcherServlet() {
       try {
         // 实际spring项目会在启动之初，扫描所有带RequestMapping注解的类，放到handlerMapping中
         Class<?> userControllerClass = UserController.class;
         handlerMapping.add(new Handler()
             .setController(userControllerClass.newInstance())
             .setMethod(userControllerClass.getMethod("getUserById", new Class[]{String.class}))
             .setUrl("/api/user"));
       } catch (Exception e) {

       }
     }
   ```


     public void doService(HttpServletRequest request, HttpServletResponse response) {
       doDispatch(request, response);
     }


     private void doDispatch(HttpServletRequest request, HttpServletResponse response) {

       // 1、获取用户请求的URL
       String uri = request.getRequestURI();
    
       //   根据用户请求的URL，去找到这个url对应的某一个java类的方法
    
       // 2、Servlet拿到URL以后，要做权衡（要做判断，要做选择）
       // 3、通过拿到的URL去handlerMapping（可以认为是策略常量）
       Handler handle = null;
       for (Handler h : handlerMapping) {
         if (uri.equals(h.getUrl())) {
           handle = h;
           break;
         }
       }
    
       // 4、将具体的任务分发给Method（通过反射去调用其对应的方法）
       Object object = null;
       try {
         object = handle.getMethod().invoke(handle.getController(), request.getParameter("mid"));
       } catch (IllegalAccessException e) {
         e.printStackTrace();
       } catch (InvocationTargetException e) {
         e.printStackTrace();
       }
    
       // 5、获取到Method执行的结果，通过Response返回出去
       // response.getWriter().write();
    
     }


     class Handler {

       private Object controller;
       private Method method;
       private String url;
    
       public Object getController() {
         return controller;
       }
    
       public Handler setController(Object controller) {
         this.controller = controller;
         return this;
       }
    
       public Method getMethod() {
         return method;
       }
    
       public Handler setMethod(Method method) {
         this.method = method;
         return this;
       }
    
       public String getUrl() {
         return url;
       }
    
       public Handler setUrl(String url) {
         this.url = url;
         return this;
       }
     }

   }

##### 4.观察者模式

针对目标对象的一举一动， 要得到一个反馈，通常会和代理模式配合使用, 观察者和被观察者之间没有必然的联系， 

1. 应用场景：

事件监听， 日志监听， Observer，Springlistener(通常会结合动态代理), 短信通知， 邮件通知

Event是事件，EventListener， 事件的注册和监听

Mouse观察者， Callback被观察者

2. UML图

![img](https://github.com/925781609/pattern/blob/master/doc/Observer.png)

3. 代码示例

   ```java
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

   public class ConcreteSubject extends Subject {

     private String state;

     public String getState() {
       return state;
     }

     public void change(String newState) {
       state = newState;
       System.out.println("主题状态为：" + state);
       //状态发生改变，通知各个观察者
       this.nodifyObservers(state);
     }
   }

   public interface Observer {

     /**
      * 更新接口
      *
      * @param state 更新的状态
      */
     public void update(String state);
   }

   public class ConcreteObserver implements Observer {

     //观察者的状态
     private String observerState;

     @Override
     public void update(String state) {
       /**
        * 更新观察者的状态，使其与目标的状态保持一致
        */
       observerState = state;
       System.out.println("状态为：" + observerState);
     }

   }

   public class Client {

     public static void main(String[] args) {
       //创建主题对象
       ConcreteSubject subject = new ConcreteSubject();
       //创建观察者对象
       Observer observer = new ConcreteObserver();
       //将观察者对象登记到主题对象上
       subject.attach(observer);
       //改变主题对象的状态
       subject.change("new state");
     }

   }
   ```

##### 5. 责任链模式

创建多个对象，使这些对象形成一条链，并沿着这条链传递请求，直到链上的某一个对象决定处理此请求。

纯责任链模式： 如果一个类要么承担责任处理请求要么将请求踢给下一个皮球

非纯责任链模式：如果一个类承担了一部分责任，还将请求踢给下一个皮球

1. 应用场景： 

会员等级系统，会员等级之间构成一条链，用户发起一个请求，直到传递到与用户会员匹配的等级

请假或报销，自己能处理则处理，处理不了则往上报

2. UML图（图片有误，子类覆写的是report方法handleMessage方法）

   ![img](https://github.com/925781609/pattern/blob/master/doc/Responsibility%20Chain.png)

3. 代码示例

   ```java
   public abstract class Handler {

     private Handler nextHandler;
     private int level;

     public Handler(int level) {
       this.level = level;
     }

     // 处理请求传递，注意final，子类不可重写
     public final void handleMessage(Request request) {
       if (level == request.getLevel()) {
         this.report(request);
       } else {
         if (this.nextHandler != null) {
           System.out.println("自己无法处理，传递给下一级");
           this.nextHandler.handleMessage(request);
         } else {
           System.out.println("处理链到达尽头，无法处理请求");
         }
       }
     }

     public void setNextHandler(Handler handler) {
       this.nextHandler = handler;
     }

     // 抽象方法，子类实现
     public abstract void report(Request request);

   }

   public class Leader extends Handler {

     public Leader() {
       super(1);
     }

     @Override
     public void report(Request request) {
       System.out.println("Leader 处理：" + request.getDetail());
     }
   }

   public class Boss extends Handler {

     public Boss() {
       super(2);
     }

     @Override
     public void report(Request request) {
       System.out.println("Boss 处理：" + request.getDetail());
     }
   }

   public class Client {

     public static void main(String[] args) {
       Request requestLevel2 = new Request(2, "Level 2的请求"); // 请求等级高

       Boss boss = new Boss();
       Leader leader = new Leader();
       // 设置下一级， 建立职责链
       leader.setNextHandler(boss);

       System.out.println("==============开始处理请求=========");
       leader.handleMessage(requestLevel2);
     }
   }
   ```

   ​