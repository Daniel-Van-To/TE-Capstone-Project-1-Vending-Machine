package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalesReport {
    //properties
    Map<String, Integer> soldInventory;
    private File salesReport;
    private LocalDateTime currentTime;
    private DateTimeFormatter formattedCurrentTime;
    public double salesTotal;

    //constructor
    public SalesReport(){
        soldInventory = new HashMap<>();
        currentTime = LocalDateTime.now();
        formattedCurrentTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");
        salesTotal = 0;
    }

    //methods
    public boolean saveToSalesReport( String itemName, double price) {
        int count = 0;
        if(soldInventory.containsKey(itemName)) {
            count = soldInventory.get(itemName) + 1;
            soldInventory.put(itemName, count);
        } else {
            soldInventory.put(itemName, 1);
        }
        salesTotal += price;
        return true;
    }


    public boolean writeToSalesReport() {
        currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(formattedCurrentTime);
        String fileName = "SALES REPORT - " + formattedTime;
        salesReport = new File(fileName);

        if(!salesReport.exists()) {
            try{
                salesReport.createNewFile();
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        try(PrintWriter output = new PrintWriter(salesReport)) {

            for(Map.Entry<String, Integer> entry : soldInventory.entrySet()) {
                output.println(entry.getKey() + ", " + entry.getValue());
            }
            System.out.println();
            System.out.println("TOTAL SALES " + salesTotal);

        } catch(IOException e){
            return false;
        }
        return true;
    }

//    public boolean saveToSalesReport( String itemName) {
//        int count = 0;
//        if(soldInventory.containsKey(itemName)) {
//            count = soldInventory.get(itemName) + 1;
//            soldInventory.put(itemName, count);
//        } else {
//            soldInventory.put(itemName, 1);
//        }
//        return true;
//    }
//    public boolean writeToSalesReport(Map<String, StuffedAnimal> inventory) {
//
//        for(Map.Entry<String, Integer> entry : soldInventory.entrySet()) {
//            for(Map.Entry<String, StuffedAnimal> innerEntry : inventory.entrySet()) {
//                if (entry.getKey().equals(innerEntry.getValue().getName())){
//                    salesTotal += (entry.getValue() * innerEntry.getValue().getPrice());
//                }
//            }
//        }
//        return true;
//    }
}
