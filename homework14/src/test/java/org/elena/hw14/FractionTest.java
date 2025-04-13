package org.elena.hw14;

import org.junit.*;

public class FractionTest {

    @BeforeClass
    public static void beforeExecution() {
        System.out.println("Tests execution start...");
    }

    @AfterClass
    public static void afterExecution() {
        System.out.println("Tests execution finish.");
    }

    @Before
    public void testInit() {
        System.out.println("The below test is running:");
    }

    @After
    public void tearDown() {
        System.out.println("The above test is completed.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDenominator() {
        System.out.println("Invalid Denominator test is running...");
        Fraction fraction = new Fraction(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDivider() {
        System.out.println("Invalid Divider test is running...");
        Fraction fraction = new Fraction(1, 5);
        fraction.division(0);
    }
}
