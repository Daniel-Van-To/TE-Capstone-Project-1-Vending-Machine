package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class SalesReportTest {

    @Test
    public void check_if_saveToSalesReport_returns_an_unchanged_map_of_sold_inventory_when_itemName_is_null() {
        //Arrange
        SalesReport report = new SalesReport();


        //Act
        Map<String, Integer> actual = report.saveToSalesReport(null,0.00);
        actual.put("Tabby Cat", 1);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Tabby Cat", 1);

        //Assert
        Assert.assertEquals(expected, actual);

    }
}
