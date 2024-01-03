package factorypattern.factorymethodpattern;

import factorypattern.common.Pizza;

public class LDOrderPizza extends OrderPizza {
       public Pizza createPizza(String ordertype) {
              Pizza pizza = null;
              if (ordertype.equals("cheese")) {
                     pizza = new LDCheesePizza();
              } else if (ordertype.equals("pepper")) {
                     pizza = new LDPepperPizza();
              }
              return pizza;
       }
}
