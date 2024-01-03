package factorypattern.abstractfactorypattern;

import factorypattern.common.Pizza;
import factorypattern.factorymethodpattern.LDCheesePizza;
import factorypattern.factorymethodpattern.LDPepperPizza;

/**
 * @author QWQ
 */
public class LDFactory implements AbsFactory {
    @Override
    public Pizza CreatePizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LDCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}