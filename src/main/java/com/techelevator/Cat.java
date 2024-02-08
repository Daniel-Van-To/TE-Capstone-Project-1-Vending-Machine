package com.techelevator;

public class Cat extends StuffedAnimal {
    //constructor
    public Cat (String itemCode, String name, double price){
        super(itemCode, name, price);
    }
    //method
    public String makeSound(){
        return ("Meow, Meow, Meow!");
    }
}
