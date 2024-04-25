package io.github.fizzyizzy05.hotel;

public class Service {
    protected int price;
    protected int id;
    protected String name;

    public Service(int id, String name, int price) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getID() {
        return this.id;
    }
}
