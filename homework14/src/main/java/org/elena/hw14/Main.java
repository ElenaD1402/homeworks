package org.elena.hw14;

import java.util.Random;

//Создайте класс с именем Fraction, содержащий два поля типа int - числитель и знаменатель обыкновенной дроби.
//○	Конструктор класса должен инициализировать их заданным набором значений.
//Fraction fraction = new Fraction(1,2)
//Fraction fraction2 = new Fraction(3,4)
//○	Создайте метод класса, который будет выводить дробь в текстовую строку в формате числитель / знаменатель;
//fraction.show()
//1/2
//○	метод, добавляющий (сложение) к текущей дроби дробь, переданную ему в параметре и возвращающий дробь - результат сложения;
//fraction.sum(fraction2)
//5/4
//○	метод, умножающий (произведение) текущую дробь на число типа double, переданное ему в параметре и возвращающий дробь - результат умножения;
//fraction.multiply(2.2d)
//../..
// ○	метод, делящий (деление) текущую дробь на число типа double, переданное ему в параметре и возвращающий дробь - результат деления.
// fraction.divide(2.1)
//../..

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        Random random = new Random();
        int numerator = random.nextInt(1,10);
        int denominator = random.nextInt(1,10);

        Fraction fraction1 = new Fraction(numerator, denominator);
        System.out.println("Первая дробь: " + fraction1.show());

        numerator = random.nextInt(1,10);
        denominator = random.nextInt(1,10);
        Fraction fraction2 = new Fraction(numerator, denominator);
        System.out.println("Вторая дробь: " + fraction2.show());

        Fraction fraction3 = fraction1.sum(fraction2);
        System.out.println(" Сумма сложения дробей " + fraction3.show());

        Fraction fraction4 = fraction1.multiply(2.5);
        System.out.println(" Умножение дроби на число " + fraction4.show());

        Fraction fraction5 = fraction1.division(2.5);
        System.out.println(" Деление дроби на число " + fraction5.show());
    }
}
