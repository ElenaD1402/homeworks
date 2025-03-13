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

    @Test
    public void testShow() {
        System.out.println("Show fraction test is running...");
        Fraction fraction1 = new Fraction(1,10);
        Assert.assertEquals("Failed","1/10",fraction1.show());
        Fraction fraction2 = new Fraction(20,10);
        Assert.assertEquals("Failed","2",fraction2.show());
        Fraction fraction3 = new Fraction(25,10);
        Assert.assertEquals("Failed","2 5/10",fraction3.show());
    }

    @Test
    public void testSum() {
        System.out.println("Sum fraction test is running...");
        Fraction fraction1 = new Fraction(1,10);
        Fraction fraction2 = new Fraction(20,10);
        Assert.assertEquals("Failed","2 1/10",fraction1.sum(fraction2).show());
    }

    @Test
    public void testMultiply() {
        System.out.println("Multiply fraction test is running...");
        Fraction fraction1 = new Fraction(1,10);
        Assert.assertEquals("Failed","5/20",fraction1.multiply(2.5).show());
    }

    @Test
    public void testDivision() {
        System.out.println("Division fraction test is running...");
        Fraction fraction1 = new Fraction(1,10);
        Assert.assertEquals("Failed","2/50",fraction1.division(2.5).show());
    }
}
