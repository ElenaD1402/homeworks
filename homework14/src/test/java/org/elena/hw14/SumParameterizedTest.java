package org.elena.hw14;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SumParameterizedTest {

    private Fraction fraction1;
    private Fraction fraction2;
    private Fraction expectedResult;

    public SumParameterizedTest(Fraction fraction1, Fraction fraction2, Fraction expectedResult) {
        this.fraction1 = fraction1;
        this.fraction2 = fraction2;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getFractions() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new Fraction(1, 13), new Fraction(7, 13), new Fraction(8, 13)});
        params.add(new Object[]{new Fraction(6, 13), new Fraction(7, 13), new Fraction(1, 1)});
        params.add(new Object[]{new Fraction(-125, 3), new Fraction(4, 3), new Fraction(-121, 3)});
        params.add(new Object[]{new Fraction(11, 17), new Fraction(-11, 17), new Fraction(0, 1)});
        params.add(new Object[]{new Fraction(-1111, 4), new Fraction(-2222, 4), new Fraction(-3333, 4)});
        params.add(new Object[]{new Fraction(1, 2), new Fraction(2, 4), new Fraction(1, 1)});
        params.add(new Object[]{new Fraction(100, 131), new Fraction(-200, 262), new Fraction(0, 1)});
        params.add(new Object[]{new Fraction(1371, 21017), new Fraction(10879, 11111), new Fraction(243877124, 233519887)});
        params.add(new Object[]{new Fraction(-122, 151), new Fraction(-11, 31), new Fraction(-5443, 4681)});
        params.add(new Object[]{new Fraction(4, 7), new Fraction(-3, 8), new Fraction(11, 56)});
        return params;
    }

    @Test
    public void testSumFractions() {
        System.out.println("Verify that " + fraction1.sum(fraction2).getNumerator() + " is " + expectedResult.getNumerator());
        Assert.assertEquals(fraction1.sum(fraction2).getNumerator() + " is not " + expectedResult.getNumerator(),
                expectedResult.getNumerator(), fraction1.sum(fraction2).getNumerator());
        System.out.println("Verify that " + fraction1.sum(fraction2).getDenominator() + " is " + expectedResult.getDenominator());
        Assert.assertEquals(fraction1.sum(fraction2).getDenominator() + " is not " + expectedResult.getDenominator(),
                expectedResult.getDenominator(), fraction1.sum(fraction2).getDenominator());
    }
}
