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