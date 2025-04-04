package org.elena.hw14;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ShowParameterizedTest {

    private Fraction fraction;
    private String expectedResult;

    public ShowParameterizedTest(Fraction fraction, String expectedResult) {
        this.expectedResult = expectedResult;
        this.fraction = fraction;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getFractions() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new Fraction(13, 11), "1 2/11"});
        params.add(new Object[]{new Fraction(-15, 13), "-1 2/13"});
        params.add(new Object[]{new Fraction(11379, 4871), "2 1637/4871"});
        params.add(new Object[]{new Fraction(25, 5), "5"});
        params.add(new Object[]{new Fraction(30, -6), "-5"});
        params.add(new Object[]{new Fraction(3829, 547), "7"});
        params.add(new Object[]{new Fraction(7, 7), "1"});
        params.add(new Object[]{new Fraction(-5, 5), "-1"});
        params.add(new Object[]{new Fraction(11373, 11373), "1"});
        params.add(new Object[]{new Fraction(0, 3), "0/3"});
        params.add(new Object[]{new Fraction(3, 16), "3/16"});
        params.add(new Object[]{new Fraction(-1, -7), "1/7"});
        params.add(new Object[]{new Fraction(13597, 13598), "13597/13598"});
        return params;
    }

    @Test
    public void testShowFraction() {
        System.out.println("Verify that " + fraction.show() + " is " + expectedResult);
        Assert.assertEquals(fraction.show() + " is not " + expectedResult, expectedResult, fraction.show());
    }
}
