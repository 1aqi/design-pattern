A、创建模式（5种）
工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。

1 工厂模式
1.1 简单工厂模式
定义：定义了一个创建对象的类，由这个类来封装实例化对象的行为。

举例：（我们举一个pizza工厂的例子）

pizza工厂一共生产三种类型的pizza：chesse,pepper,greak。通过工厂类（SimplePizzaFactory）实例化这三种类型的对象。类图如下：





工厂类的代码：

public class SimplePizzaFactory {
public Pizza CreatePizza(String ordertype) {
Pizza pizza = null;
if (ordertype.equals("cheese")) {
pizza = new CheesePizza();
} else if (ordertype.equals("greek")) {
pizza = new GreekPizza();
} else if (ordertype.equals("pepper")) {
pizza = new PepperPizza();
}
return pizza;
}
}
简单工厂存在的问题与解决方法： 简单工厂模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了开闭原则，所以，从设计角度考虑，有一定的问题，如何解决？我们可以定义一个创建对象的抽象方法并创建多个不同的工厂类实现该抽象方法，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。这种方法也就是我们接下来要说的工厂方法模式。

1.2 工厂方法模式
定义：定义了一个创建对象的抽象方法，由子类决定要实例化的类。工厂方法模式将对象的实例化推迟到子类。

举例：（我们依然举pizza工厂的例子，不过这个例子中，pizza产地有两个：伦敦和纽约）。添加了一个新的产地，如果用简单工厂模式的的话，我们要去修改工厂代码，并且会增加一堆的if else语句。而工厂方法模式克服了简单工厂要修改代码的缺点，它会直接创建两个工厂，纽约工厂和伦敦工厂。类图如下：





OrderPizza中有个抽象的方法：

abstract Pizza createPizza();
两个工厂类继承OrderPizza并实现抽象方法：

public class LDOrderPizza extends OrderPizza {
Pizza createPizza(String ordertype) {
Pizza pizza = null;
if (ordertype.equals("cheese")) {
pizza = new LDCheesePizza();
} else if (ordertype.equals("pepper")) {
pizza = new LDPepperPizza();
}
return pizza;
}
}
public class NYOrderPizza extends OrderPizza {

	Pizza createPizza(String ordertype) {
		Pizza pizza = null;
 
		if (ordertype.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new NYPepperPizza();
		}
		return pizza;
 
	}

}

、通过不同的工厂会得到不同的实例化的对象，PizzaStroe的代码如下：

public class PizzaStroe {
public static void main(String[] args) {
OrderPizza mOrderPizza;
mOrderPizza = new NYOrderPizza();
}
}
解决了简单工厂模式的问题：增加一个新的pizza产地（北京），只要增加一个BJOrderPizza类：

public class BJOrderPizza extends OrderPizza {
Pizza createPizza(String ordertype) {
Pizza pizza = null;
if (ordertype.equals("cheese")) {
pizza = new LDCheesePizza();
} else if (ordertype.equals("pepper")) {
pizza = new LDPepperPizza();
}
return pizza;
}
}
其实这个模式的好处就是，如果你现在想增加一个功能，只需做一个实现类就OK了，无需去改动现成的代码。这样做，拓展性较好！

工厂方法存在的问题与解决方法：客户端需要创建类的具体的实例。简单来说就是用户要订纽约工厂的披萨，他必须去纽约工厂，想订伦敦工厂的披萨，必须去伦敦工厂。 当伦敦工厂和纽约工厂发生变化了，用户也要跟着变化，这无疑就增加了用户的操作复杂性。为了解决这一问题，我们可以把工厂类抽象为接口，用户只需要去找默认的工厂提出自己的需求（传入参数），便能得到自己想要产品，而不用根据产品去寻找不同的工厂，方便用户操作。这也就是我们接下来要说的抽象工厂模式。

1.3 抽象工厂模式
定义：定义了一个接口用于创建相关或有依赖关系的对象族，而无需明确指定具体类。

举例：（我们依然举pizza工厂的例子，pizza工厂有两个：纽约工厂和伦敦工厂）。类图如下：



工厂的接口：

public interface AbsFactory {
Pizza CreatePizza(String ordertype) ;
}
工厂的实现：

public class LDFactory implements AbsFactory {
@Override
public Pizza CreatePizza(String ordertype) {
Pizza pizza = null;
if ("cheese".equals(ordertype)) {
pizza = new LDCheesePizza();
} else if ("pepper".equals(ordertype)) {
pizza = new LDPepperPizza();
}
return pizza;
}
}
PizzaStroe的代码如下：

public class PizzaStroe {
public static void main(String[] args) {
OrderPizza mOrderPizza;
mOrderPizza = new OrderPizza("London");
}
}
解决了工厂方法模式的问题：在抽象工厂中PizzaStroe中只需要传入参数就可以实例化对象。

1.4 工厂模式适用的场合
大量的产品需要创建，并且这些产品具有共同的接口 。

1.5  三种工厂模式的使用选择
简单工厂 ： 用来生产同一等级结构中的任意产品。（不支持拓展增加产品）

工厂方法 ：用来生产同一等级结构中的固定产品。（支持拓展增加产品）

抽象工厂 ：用来生产不同产品族的全部产品。（支持拓展增加产品；支持增加产品族）

简单工厂的适用场合：只有伦敦工厂（只有这一个等级），并且这个工厂只生产三种类型的pizza：chesse,pepper,greak（固定产品）。

工厂方法的适用场合：现在不光有伦敦工厂，还增设了纽约工厂（仍然是同一等级结构，但是支持了产品的拓展），这两个工厂依然只生产三种类型的pizza：chesse,pepper,greak（固定产品）。

抽象工厂的适用场合：不光增设了纽约工厂（仍然是同一等级结构，但是支持了产品的拓展），这两个工厂还增加了一种新的类型的pizza：chinese pizza（增加产品族）。

所以说抽象工厂就像工厂，而工厂方法则像是工厂的一种产品生产线。因此，我们可以用抽象工厂模式创建工厂，而用工厂方法模式创建生产线。比如，我们可以使用抽象工厂模式创建伦敦工厂和纽约工厂，使用工厂方法实现cheese pizza和greak pizza的生产。类图如下：



总结一下三种模式：

简单工厂模式就是建立一个实例化对象的类，在该类中对多个对象实例化。工厂方法模式是定义了一个创建对象的抽象方法，由子类决定要实例化的类。这样做的好处是再有新的类型的对象需要实例化只要增加子类即可。抽象工厂模式定义了一个接口用于创建对象族，而无需明确指定具体类。抽象工厂也是把对象的实例化交给了子类，即支持拓展。同时提供给客户端接口，避免了用户直接操作子类工厂。


2 单例模式
定义：确保一个类最多只有一个实例，并提供一个全局访问点

单例模式可以分为两种：预加载和懒加载

2.1 预加载
顾名思义，就是预先加载。再进一步解释就是还没有使用该单例对象，但是，该单例对象就已经被加载到内存了。

public class PreloadSingleton {

       public static PreloadSingleton instance = new PreloadSingleton();
   
       //其他的类无法实例化单例类的对象
       private PreloadSingleton() {
       };
       
       public static PreloadSingleton getInstance() {
              return instance;
       }
}
很明显，没有使用该单例对象，该对象就被加载到了内存，会造成内存的浪费。

2.2 懒加载
为了避免内存的浪费，我们可以采用懒加载，即用到该单例对象的时候再创建。

public class Singleton {

       private static Singleton instance=null;
       
       private Singleton(){
       };
       
       public static Singleton getInstance()
       {
              if(instance==null)
              {
                     instance=new Singleton();
              }
              return instance;
              
       }
}

2.3 单例模式和线程安全
（1）预加载只有一条语句return instance,这显然可以保证线程安全。但是，我们知道预加载会造成内存的浪费。

（2）懒加载不浪费内存，但是无法保证线程的安全。首先，if判断以及其内存执行代码是非原子性的。其次，new Singleton()无法保证执行的顺序性。

不满足原子性或者顺序性，线程肯定是不安全的，这是基本的常识，不再赘述。我主要讲一下为什么new Singleton()无法保证顺序性。我们知道创建一个对象分三步:

memory=allocate();//1:初始化内存空间

ctorInstance(memory);//2:初始化对象

instance=memory();//3:设置instance指向刚分配的内存地址
jvm为了提高程序执行性能，会对没有依赖关系的代码进行重排序，上面2和3行代码可能被重新排序。我们用两个线程来说明线程是不安全的。线程A和线程B都创建对象。其中，A2和A3的重排序，将导致线程B在B1处判断出instance不为空，线程B接下来将访问instance引用的对象。此时，线程B将会访问到一个还未初始化的对象（线程不安全）。



2.4 保证懒加载的线程安全
我们首先想到的就是使用synchronized关键字。synchronized加载getInstace()函数上确实保证了线程的安全。但是，如果要经常的调用getInstance()方法，不管有没有初始化实例，都会唤醒和阻塞线程。为了避免线程的上下文切换消耗大量时间，如果对象已经实例化了，我们没有必要再使用synchronized加锁，直接返回对象。

public class Singleton {
private static Singleton instance = null;
private Singleton() {
};
public static synchronized Singleton getInstance() {
if (instance == null) {
instance = new Singleton();
}
return instance;
}
}
我们把sychronized加在if(instance==null)判断语句里面，保证instance未实例化的时候才加锁

public class Singleton {
private static Singleton instance = null;
private Singleton() {
};
public static synchronized Singleton getInstance() {
if (instance == null) {
synchronized (Singleton.class) {
if (instance == null) {
instance = new Singleton();
}
}
}
return instance;
}
}
我们经过2.3的讨论知道new一个对象的代码是无法保证顺序性的，因此，我们需要使用另一个关键字volatile保证对象实例化过程的顺序性。

public class Singleton {
private static volatile Singleton instance = null;
private Singleton() {
};
public static synchronized Singleton getInstance() {
if (instance == null) {
synchronized (instance) {
if (instance == null) {
instance = new Singleton();
}
}
}
return instance;
}
}
到此，我们就保证了懒加载的线程安全。



3 生成器模式
定义：封装一个复杂对象构造过程，并允许按步骤构造。

定义解释： 我们可以将生成器模式理解为，假设我们有一个对象需要建立，这个对象是由多个组件（Component）组合而成，每个组件的建立都比较复杂，但运用组件来建立所需的对象非常简单，所以我们就可以将构建复杂组件的步骤与运用组件构建对象分离，使用builder模式可以建立。

3.1 模式的结构和代码示例
生成器模式结构中包括四种角色：

（1）产品(Product)：具体生产器要构造的复杂对象；

（2）抽象生成器(Bulider)：抽象生成器是一个接口，该接口除了为创建一个Product对象的各个组件定义了若干个方法之外，还要定义返回Product对象的方法（定义构造步骤）；

（3）具体生产器(ConcreteBuilder)：实现Builder接口的类，具体生成器将实现Builder接口所定义的方法（生产各个组件）；

（4）指挥者(Director)：指挥者是一个类，该类需要含有Builder接口声明的变量。指挥者的职责是负责向用户提供具体生成器，即指挥者将请求具体生成器类来构造用户所需要的Product对象，如果所请求的具体生成器成功地构造出Product对象，指挥者就可以让该具体生产器返回所构造的Product对象。（按照步骤组装部件，并返回Product）





举例（我们如果构建生成一台电脑，那么我们可能需要这么几个步骤（1）需要一个主机（2）需要一个显示器（3）需要一个键盘（4）需要一个鼠标）

虽然我们具体在构建一台主机的时候，每个对象的实际步骤是不一样的，比如，有的对象构建了i7cpu的主机，有的对象构建了i5cpu的主机，有的对象构建了普通键盘，有的对象构建了机械键盘等。但不管怎样，你总是需要经过一个步骤就是构建一台主机，一台键盘。对于这个例子，我们就可以使用生成器模式来生成一台电脑，他需要通过多个步骤来生成。类图如下：



ComputerBuilder类定义构造步骤：

public abstract class ComputerBuilder {

    protected Computer computer;
   
    public Computer getComputer() {
        return computer;
    }
   
    public void buildComputer() {
        computer = new Computer();
        System.out.println("生成了一台电脑！！！");
    }
    public abstract void buildMaster();
    public abstract void buildScreen();
    public abstract void buildKeyboard();
    public abstract void buildMouse();
    public abstract void buildAudio();
}

HPComputerBuilder定义各个组件：

public class HPComputerBuilder extends ComputerBuilder {
@Override
public void buildMaster() {
// TODO Auto-generated method stub
computer.setMaster("i7,16g,512SSD,1060");
System.out.println("(i7,16g,512SSD,1060)的惠普主机");
}
@Override
public void buildScreen() {
// TODO Auto-generated method stub
computer.setScreen("1080p");
System.out.println("(1080p)的惠普显示屏");
}
@Override
public void buildKeyboard() {
// TODO Auto-generated method stub
computer.setKeyboard("cherry 青轴机械键盘");
System.out.println("(cherry 青轴机械键盘)的键盘");
}
@Override
public void buildMouse() {
// TODO Auto-generated method stub
computer.setMouse("MI 鼠标");
System.out.println("(MI 鼠标)的鼠标");
}
@Override
public void buildAudio() {
// TODO Auto-generated method stub
computer.setAudio("飞利浦 音响");
System.out.println("(飞利浦 音响)的音响");
}
}

Director类对组件进行组装并生成产品

public class Director {

    private ComputerBuilder computerBuilder;
    public void setComputerBuilder(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }
   
    public Computer getComputer() {
        return computerBuilder.getComputer();
    }
   
    public void constructComputer() {
        computerBuilder.buildComputer();
        computerBuilder.buildMaster();
        computerBuilder.buildScreen();
        computerBuilder.buildKeyboard();
        computerBuilder.buildMouse();
        computerBuilder.buildAudio();
    }
}

3.2 生成器模式的优缺点
优点
将一个对象分解为各个组件

将对象组件的构造封装起来

可以控制整个对象的生成过程

缺点
对不同类型的对象需要实现不同的具体构造器的类，这可能回答大大增加类的数量

3.3 生成器模式与工厂模式的不同
生成器模式构建对象的时候，对象通常构建的过程中需要多个步骤，就像我们例子中的先有主机，再有显示屏，再有鼠标等等，生成器模式的作用就是将这些复杂的构建过程封装起来。工厂模式构建对象的时候通常就只有一个步骤，调用一个工厂方法就可以生成一个对象。



4 原型模式
定义：通过复制现有实例来创建新的实例，无需知道相应类的信息。

简单地理解，其实就是当需要创建一个指定的对象时，我们刚好有一个这样的对象，但是又不能直接使用，我会clone一个一毛一样的新对象来使用；基本上这就是原型模式。关键字：Clone。

4.1 深拷贝和浅拷贝
浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。

深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。clone明显是深复制，clone出来的对象是是不能去影响原型对象的

4.2 原型模式的结构和代码示例


Client：使用者

Prototype：接口（抽象类），声明具备clone能力，例如java中得Cloneable接口

ConcretePrototype：具体的原型类

可以看出设计模式还是比较简单的，重点在于Prototype接口和Prototype接口的实现类ConcretePrototype。原型模式的具体实现：一个原型类，只需要实现Cloneable接口，覆写clone方法，此处clone方法可以改成任意的名称，因为Cloneable接口是个空接口，你可以任意定义实现类的方法名，如cloneA或者cloneB，因为此处的重点是super.clone()这句话，super.clone()调用的是Object的clone()方法。

public class Prototype implements Cloneable {  
public Object clone() throws CloneNotSupportedException {  
Prototype proto = (Prototype) super.clone();  
return proto;  
}  
}  
举例（银行发送大量邮件，使用clone和不使用clone的时间对比）：我们模拟创建一个对象需要耗费比较长的时间，因此，在构造函数中我们让当前线程sleep一会

public Mail(EventTemplate et) {
this.tail = et.geteventContent();
this.subject = et.geteventSubject();
try {
Thread.sleep(1000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
不使用clone,发送十个邮件

public static void main(String[] args) {
int i = 0;
int MAX_COUNT = 10;
EventTemplate et = new EventTemplate("9月份信用卡账单", "国庆抽奖活动...");
long start = System.currentTimeMillis();
while (i < MAX_COUNT) {
// 以下是每封邮件不同的地方
Mail mail = new Mail(et);
mail.setContent(getRandString(5) + ",先生（女士）:你的信用卡账单..." + mail.getTail());
mail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
// 然后发送邮件
sendMail(mail);
i++;
}
long end = System.currentTimeMillis();
System.out.println("用时:" + (end - start));
}



用时：10001

使用clone,发送十个邮件

    public static void main(String[] args) {
              int i = 0;
              int MAX_COUNT = 10;
              EventTemplate et = new EventTemplate("9月份信用卡账单", "国庆抽奖活动...");
              long start=System.currentTimeMillis();
              Mail mail = new Mail(et);         
              while (i < MAX_COUNT) {
                     Mail cloneMail = mail.clone();
                     mail.setContent(getRandString(5) + ",先生（女士）:你的信用卡账单..."
                                  + mail.getTail());
                     mail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
                     sendMail(cloneMail);
                     i++;
              }
              long end=System.currentTimeMillis();
              System.out.println("用时:"+(end-start));
       }

用时：1001

4.3 总结
原型模式的本质就是clone，可以解决构建复杂对象的资源消耗问题，能再某些场景中提升构建对象的效率；还有一个重要的用途就是保护性拷贝，可以通过返回一个拷贝对象的形式，实现只读的限制。