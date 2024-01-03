package factorypattern.factorymethodpattern;

import factorypattern.common.Pizza;

public class NYOrderPizza extends OrderPizza {

    protected Pizza createPizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new NYCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new NYPepperPizza();
        }
        return pizza;

    }

}
