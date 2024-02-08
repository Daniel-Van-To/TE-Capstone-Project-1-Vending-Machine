package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    //properties
    private Scanner userInput;
//    private Map<String, List<StuffedAnimal>> vendingInventory;
    private Map<String, StuffedAnimal> vendingInventory;
    private boolean finishedTransaction;
    //constructor
    public VendingMachine() {
        userInput = new Scanner(System.in);
        vendingInventory = new HashMap<>();
        finishedTransaction = false;
    }
    //method
//    public String readFile(){
//
//    }
    public void run() {
        //TODO make readInputFile method
        readInputFile();
        System.out.println("Welcome to Daniel and Quynh's vending machine");
        System.out.println("******************************************************");


        while (!finishedTransaction) {

            System.out.println("Main Menu");
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println();
            System.out.print("Please make your selection: ");
            int selection = userInput.nextInt();

            if( selection == 1) {
                //TODO make the displayItem method
                displayItem();
            } else if (selection == 2) {
                //TODO make the purchaseItem method
                purchaseItem();
            } else if (selection == 3) {
                finishedTransaction = true;
            } else if (selection == 4) {
                //TODO make the createSalesReport method
                createSalesReport();
            } else {
                System.out.println("Invalid number!");
                System.out.println();
            }
        }

    }
    public void readInputFile(){
        //TODO consider this method as boolean for testing purpose
        File inputFile = new File("vendingmachine.csv");
        String[] line;
        StuffedAnimal newAnimal = null;

        try(Scanner fileScanner = new Scanner(inputFile)){
            while(fileScanner.hasNextLine()) {
                line = fileScanner.nextLine().split(",");
                if(line[line.length -1].equals("Duck")) {
                   newAnimal = new Duck(line[0], line[1], Double.parseDouble(line[2]));
                } else if (line[line.length -1].equals("Penguin")) {
                    newAnimal = new Penguin(line[0], line[1], Double.parseDouble(line[2]));
                } else if (line[line.length -1].equals("Cat")) {
                    newAnimal = new Cat(line[0], line[1], Double.parseDouble(line[2]));
                } else if (line[line.length -1].equals("Pony")) {
                    newAnimal = new Pony(line[0], line[1], Double.parseDouble(line[2]));
                }

                vendingInventory.put(newAnimal.getItemCode(), newAnimal);
            }
        }  catch (FileNotFoundException e) {
            System.out.println("404 - Input file not found");
        }
    }
    public boolean displayItem(){
        for (Map.Entry<String, StuffedAnimal> entry : vendingInventory.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue().getName()
                    + ": $" + entry.getValue().getPrice() + " Quantity remaining: "
                    + (entry.getValue().getQuantity() == 0 ? "SOLD OUT" : entry.getValue().getQuantity()));
        }
        return true;
    }
    public String purchaseItem(){
        return null;
    }
    public String createSalesReport(){
        return null;
    }
}

