package org.elena.hw14;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class MultiplyParameterizedTest {

    private Fraction fraction = new Fraction(11, 13);
    private Double number;
    private int expectedNumerator;
    private int expectedDenominator;

    public MultiplyParameterizedTest(Double number, int expectedNumerator, int expectedDenominator) {
        this.number = number;
        this.expectedNumerator = expectedNumerator;
        this.expectedDenominator = expectedDenominator;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getNumbers() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{-1d, -11, 13});
        params.add(new Object[]{-0.66, -363, 650});
        params.add(new Object[]{0d, 0, 13});
        params.add(new Object[]{2d, 22, 13});
        params.add(new Object[]{5.3, 583, 130});
        params.add(new Object[]{11379d, 125169, 13});
        return params;
    }

    @Test
    public void testMultiplyFractions() {
        System.out.println("Verify that Numerator " + fraction.multiply(number).getNumerator() + " is " + expectedNumerator);
        Assert.assertEquals(fraction.multiply(number).getNumerator() + " is not " + expectedNumerator,
                expectedNumerator, fraction.multiply(number).getNumerator());
        System.out.println("Verify that Denominator " + fraction.multiply(number).getDenominator() + " is " + expectedDenominator);
        Assert.assertEquals(fraction.multiply(number).getDenominator() + " is not " + expectedDenominator,
                expectedDenominator, fraction.multiply(number).getDenominator());
    }
}
