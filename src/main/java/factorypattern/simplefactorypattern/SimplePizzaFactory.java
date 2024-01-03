package factorypattern.simplefactorypattern;

import factorypattern.common.Pizza;

/**
 * @Title: SimplePizzaFactory
 * @Author Xu Jing
 * @Package factorypattern.simplefactorypattern
 * @Date 2024/1/3 14:34
 * @description:
 */
public class SimplePizzaFactory {
    Pizza CreatePizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (orderType.equals("greek")) {
            pizza = new GreekPizza();
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
        }
        return pizza;
    }
}
