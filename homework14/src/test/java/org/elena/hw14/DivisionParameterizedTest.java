package org.elena.hw14;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class DivisionParameterizedTest {

    private Fraction fraction = new Fraction(11, 13);
    private Double number;
    private int expectedNumerator;
    private int expectedDenominator;

    public DivisionParameterizedTest(Double number, int expectedNumerator, int expectedDenominator) {
        this.number = number;
        this.expectedNumerator = expectedNumerator;
        this.expectedDenominator = expectedDenominator;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getNumbers() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{-1d, 11, -13});
        params.add(new Object[]{-0.5, 22, -13});
        params.add(new Object[]{11d, 11, 143});
        params.add(new Object[]{15.2, 110, 1976});
        params.add(new Object[]{11897d, 11, 154661});
        return params;
    }

    @Test
    public void testDivisionFractions() {
        System.out.println("Verify that Numerator " + fraction.division(number).getNumerator() + " is " + expectedNumerator);
        Assert.assertEquals(fraction.division(number).getNumerator() + " is not " + expectedNumerator,
                expectedNumerator, fraction.division(number).getNumerator());
        System.out.println("Verify that Denominator " + fraction.division(number).getDenominator() + " is " + expectedDenominator);
        Assert.assertEquals(fraction.division(number).getDenominator() + " is not " + expectedDenominator,
                expectedDenominator, fraction.division(number).getDenominator());
    }
}
