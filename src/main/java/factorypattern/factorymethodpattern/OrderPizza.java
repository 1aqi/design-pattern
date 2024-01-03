package factorypattern.factorymethodpattern;

import factorypattern.common.Pizza;

/**
 * @Title: OrderPizza
 * @Author Xu Jing
 * @Package factorypattern.common
 * @Date 2024/1/3 14:36
 * @description:
 */
public abstract class OrderPizza {
    protected abstract Pizza createPizza(String Type);
}
