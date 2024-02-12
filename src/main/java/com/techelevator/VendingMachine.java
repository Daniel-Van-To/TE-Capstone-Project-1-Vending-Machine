package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {
    //properties
    private Scanner userInput;
//    private Map<String, List<StuffedAnimal>> vendingInventory;
    private Map<String, StuffedAnimal> vendingInventory;
    private boolean finishedTransaction;
    private InternalRecord record;
    private SalesReport report;


    //constructor
    public VendingMachine() {
        userInput = new Scanner(System.in);
        vendingInventory = new HashMap<>();
        finishedTransaction = false;
        record = new InternalRecord();
        report = new SalesReport();
    }


    //method
    public void run() {
        //TODO make readInputFile method
        readInputFile();
        System.out.println("Welcome to Daniel and Quynh's vending machine");
        System.out.println("******************************************************");


        while (!finishedTransaction) {
            System.out.println();
            System.out.println("Main Menu");
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println();

            try {
                System.out.print("Please make your selection: ");
                int selection = userInput.nextInt();
                userInput.nextLine();

                if (selection == 1) {
                    //TODO make the displayItem method
                    displayItem();
                } else if (selection == 2) {
                    //TODO make the purchaseItem method
                    purchaseItem();
                } else if (selection == 3) {
                    finishedTransaction = true;
                } else if (selection == 4) {
                    //TODO make the createSalesReport method
                    report.writeToSalesReport();
                } else {
                    System.out.println("Invalid number!");
                    System.out.println();
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Sorry, that isn't even a number!"); // Quynh's suggestion
                userInput.nextLine();
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
                }
                else if (line[line.length -1].equals("Penguin")) {
                    newAnimal = new Penguin(line[0], line[1], Double.parseDouble(line[2]));
                }
                else if (line[line.length -1].equals("Cat")) {
                    newAnimal = new Cat(line[0], line[1], Double.parseDouble(line[2]));
                }
                else if (line[line.length -1].equals("Pony")) {
                    newAnimal = new Pony(line[0], line[1], Double.parseDouble(line[2]));
                }


                vendingInventory.put(newAnimal.getItemCode(), newAnimal);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("404 - Input file not found");
        }
    }


    public boolean displayItem(){

        for (Map.Entry<String, StuffedAnimal> entry : vendingInventory.entrySet()) {

            System.out.print(entry.getKey() + " - " + entry.getValue().getName() );
            System.out.printf(": $ %.2f", entry.getValue().getPrice());
            System.out.println(" Quantity remaining: " + (entry.getValue().getQuantity() == 0 ? "SOLD OUT" : entry.getValue().getQuantity()));

        }

        System.out.println();
        return true;
    }


    public boolean purchaseItem(){
        Transaction userTransaction = new Transaction();
        boolean stillPurchase = true;

        while(stillPurchase) {

            System.out.println();
            System.out.printf("Current money provided: %.2f \n", userTransaction.getBalance());
            System.out.println();

            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println();

            try {

                System.out.print("Please select an option: ");
                int choice = userInput.nextInt();
                userInput.nextLine();

                if (choice == 1) {
                    try {
                        System.out.println();
                        System.out.print("Please input a whole dollar amount to add to your balance: ");
                        double addMoney = userInput.nextDouble();
                        userInput.nextLine();

                        if (addMoney % 1 != 0) {
                            System.out.println("Sorry, please add whole dollar amount.");
                        }
                        else {
                            double previousBalance = userTransaction.getBalance();
                            double changedBalance = userTransaction.feedMoney(addMoney);

                            if (previousBalance < changedBalance) {
                                record.writeToRecord("FEED MONEY", addMoney, userTransaction.getBalance());
                            }
                            else {
                                System.out.println("Sorry, you can't feed negative money!");
                            }
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Sorry, please enter a valid whole dollar amount.");
                        userInput.nextLine();
                    }
                }
                else if (choice == 2) {

                    displayItem();
                    System.out.println();
                    System.out.print("Please select an item code to purchase: ");
                    String inputCode = userInput.nextLine();
                    StuffedAnimal selectedAnimal = vendingInventory.get(inputCode);

                    if (vendingInventory.containsKey(inputCode)) {
                        if (selectedAnimal.getQuantity() > 0) {
                            if (userTransaction.getBalance() >= selectedAnimal.getPrice()) {

                                userTransaction.subtractBalance(selectedAnimal.getPrice());
                                selectedAnimal.dispense();

                                System.out.printf("Name: %s,  Price: %.2f \n", selectedAnimal.getName() , selectedAnimal.getPrice());

                                System.out.println(selectedAnimal.makeSound());
                                System.out.printf("Remaining Balance: %.2f \n", userTransaction.getBalance());

                                record.writeToRecord(selectedAnimal, userTransaction.getBalance());
                                report.saveToSalesReport(selectedAnimal.getName(), selectedAnimal.getPrice());

                            }
                            else {
                                System.out.println("Sorry, not enough funds! Please add more money!");
                            }
                        }
                        else {
                            System.out.println("Sorry, that item is SOLD OUT!");
                        }
                    }
                    else {
                        System.out.println("Sorry, that code is NOT valid!");
                    }

                }
                else if (choice == 3) {

                    record.writeToRecord("GIVE CHANGE", userTransaction.getBalance(), 0.00);

                    for (Map.Entry<String, Integer> entry : userTransaction.returnChange().entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }

                    stillPurchase = false;
                }
                else {
                    System.out.println("Sorry, that's not a valid choice!");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Sorry, that's not even a number!");
                userInput.nextLine();
            }

        }
        return true;
    }

}

