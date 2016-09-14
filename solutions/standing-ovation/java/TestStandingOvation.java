package com.company;

import org.junit.Assert;
import org.junit.Test;

public class TestStandingOvation {

    @Test
    public void inputShouldHaveOneOrMoreTestCases() {
        StandingOvation standingOvation = new StandingOvation();
        String output = standingOvation.getResults("1\n" +
                "4 11111");

        Assert.assertNotEquals("Error", output);
    }

    @Test
    public void inputShouldNotHaveLessThanOneTestCases() {
        StandingOvation standingOvation = new StandingOvation();
        String output = standingOvation.getResults("0\n");
        String output2 = standingOvation.getResults("-1");

        Assert.assertEquals("Error", output);
        Assert.assertEquals("Error", output2);
    }

    @Test
    public void inputShouldHaveLessOrEqualThanAHundredTestCases() {
        StandingOvation standingOvation = new StandingOvation();
        String output = standingOvation.getResults("3\n" +
                "0 1\n" +
                "0 1\n" +
                "0 1\n");

        Assert.assertNotEquals("Error", output);
    }

    @Test
    public void inputShouldNotHaveMoreThanAHundredTestCases() {
        StandingOvation standingOvation = new StandingOvation();
        String output = standingOvation.getResults("101");

        Assert.assertEquals("Error", output);
    }

    @Test
    public void numberOfLinesShouldBeEqualToNumberOfFirstLine() {
        StandingOvation standingOvation = new StandingOvation();
        String output = standingOvation.getResults("1\n" +
                "4 11111");

        Assert.assertNotEquals("Error", output);
    }

    @Test
    public void numberOfLinesShouldNotBeEqualToNumberOfFirstLine() {
        StandingOvation standingOvation = new StandingOvation();
        String output = standingOvation.getResults("2\n" +
                "4 1111");

        Assert.assertEquals("Error", output);
    }
}
