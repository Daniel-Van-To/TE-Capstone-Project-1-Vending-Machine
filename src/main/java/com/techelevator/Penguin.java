package com.techelevator;

public class Penguin extends StuffedAnimal{
    //constructor
    public Penguin (String itemCode, String name, double price){
        super(itemCode, name, price);
    }
    //method
    public String makeSound(){
        return ("Squawk, Squawk, Whee!");
    }
}
