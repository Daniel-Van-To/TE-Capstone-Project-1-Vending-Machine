package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
public class InternalRecord {
    //properties
    private File destinationFile;
    private LocalDateTime currentTime;
    private DateTimeFormatter formattedCurrentTime;
    //constructor
    public InternalRecord(){
        destinationFile = new File("Vending.log");
        currentTime = LocalDateTime.now();
        formattedCurrentTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");
    }

    //methods
    public boolean checkedRecords() {
        if(!destinationFile.exists()) {
            try{
                destinationFile.createNewFile();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public boolean writeToRecord(String action, double transactionMoney, double balance ) {
        try(PrintWriter output = new PrintWriter(new FileOutputStream(destinationFile, true))) {

            currentTime = LocalDateTime.now();
            String formattedTime = currentTime.format(formattedCurrentTime);
            output.println(formattedTime + " " + action + ": " + transactionMoney + " " + balance);

        } catch(IOException e){
            return false;
        }
        return true;
    }


    public boolean writeToRecord(StuffedAnimal chosenAnimal, double balance) {
        try(PrintWriter output = new PrintWriter(new FileOutputStream(destinationFile, true))) {

            currentTime = LocalDateTime.now();
            String formattedTime = currentTime.format(formattedCurrentTime);
            output.println(formattedTime + " " + chosenAnimal.getName() + " " + chosenAnimal.getItemCode() + ": "
                    + chosenAnimal.getPrice() + " " + balance);

        } catch(IOException e){
            return false;
        }

        return true;
    }
}
