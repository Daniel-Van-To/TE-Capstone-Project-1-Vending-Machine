package com.techelevator;

public class Duck extends StuffedAnimal {
    //constructor
    public Duck(String itemCode, String name, double price){
        super(itemCode, name, price);
    }
    //method
    public String makeSound(){
        return ("Quack, Quack, Splash!");
    }
}
