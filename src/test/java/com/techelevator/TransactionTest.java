package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class TransactionTest {

    @Test
    public void check_if_feedMoney_returns_unchanged_balance_if_moneyToAdd_is_less_than_0() {

        //Arrange
        Transaction checkFeedMoney = new Transaction();

        //Act
        double actual = checkFeedMoney.feedMoney(-10.0);

        //Assert
        Assert.assertEquals(0.0,actual,0.001);
    }

    @Test
    public void check_if_subtractBalance_returns_unchanged_balance_if_moneyToSubtract_is_more_than_balance() {

        //Arrage
        Transaction checkSubtractBalance = new Transaction();

        //Act
        double actual = checkSubtractBalance.subtractBalance(10);

        //Assert
        Assert.assertEquals(0, actual, 0.001);
    }

    @Test
    public void check_if_returnChange_returns_the_correct_amount_of_change() {

        //Arrange
        Transaction checkReturnChange = new Transaction();
        checkReturnChange.feedMoney(8.00);
        checkReturnChange.subtractBalance(2.85); //5.35

        //Act
        Map<String, Integer> actual = checkReturnChange.returnChange();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Quarter", 20);
        expected.put("Dime", 1);
        expected.put("Nickel", 1);

        //Assert
        Assert.assertEquals(expected,actual);


    }
    
}
