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
        formattedCurrentTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm-ss a");
        salesTotal = 0;
    }

    //methods
    public Map<String, Integer> saveToSalesReport( String itemName, double price) {
        int count = 0;

        if(itemName != null) {
            if (soldInventory.containsKey(itemName)) {
                count = soldInventory.get(itemName) + 1;
                soldInventory.put(itemName, count);
            } else {
                soldInventory.put(itemName, 1);
            }
            salesTotal += price;
        }

        return soldInventory;

    }


    public boolean writeToSalesReport() {
        currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(formattedCurrentTime);
        String fileName = "SALES REPORT - " + formattedTime + ".log";
        salesReport = new File(fileName);

            try {
                salesReport.createNewFile();
            }
            catch(IOException e) {

            }


        try(PrintWriter output = new PrintWriter(salesReport)) {

            for(Map.Entry<String, Integer> entry : soldInventory.entrySet()) {
                output.println(entry.getKey() + ", " + entry.getValue());
            }
            output.println();

            output.printf("TOTAL SALES %.2f", salesTotal );

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
