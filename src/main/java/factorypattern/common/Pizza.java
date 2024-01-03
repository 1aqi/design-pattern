package factorypattern.common;

/**
 * @Title: Pizza
 * @Author Xu Jing
 * @Package factorypattern
 * @Date 2024/1/3 14:27
 * @description:
 */
public abstract class Pizza {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void bake() {

    }

    protected void box() {

    }

    protected void cut() {

    }

    protected void prepare() {

    }


}
