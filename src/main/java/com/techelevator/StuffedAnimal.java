package com.techelevator;

public abstract class StuffedAnimal {

    //properties
    private String itemCode;
    private String name;
    private double price;
    private int quantity;
    //constructors
    public StuffedAnimal (String itemCode, String name, double price){
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        quantity = 5;
    }
    //methods
    public String getItemCode() {
        return itemCode;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void dispense() {
        quantity--;
    }

    public abstract String makeSound();
}
