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


   ​