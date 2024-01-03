package factorypattern.abstractfactorypattern;

import factorypattern.common.Pizza;

public interface AbsFactory {
       Pizza CreatePizza(String orderType) ;
}