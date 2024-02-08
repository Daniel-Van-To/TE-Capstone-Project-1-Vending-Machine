package com.techelevator;

public class Pony extends StuffedAnimal {
    //constructor
    public Pony (String itemCode, String name, double price){
        super(itemCode, name, price);
    }
    //method
    public String makeSound(){
        return ("Neigh, Neigh, Yay!");
    }
}