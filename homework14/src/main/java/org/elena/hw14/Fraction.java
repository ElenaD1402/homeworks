package org.elena.hw14;

public class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private int getNumerator() {
        return numerator;
    }

    private int getDenominator() {
        return denominator;
    }

    public String show() {
        if (numerator >= denominator) {
            if (numerator % denominator == 0) {
                return String.valueOf(numerator / denominator);
            } else
                return numerator / denominator + " " + (numerator % denominator) + "/" + denominator;
        } else
            return numerator + "/" + denominator;
    }

    public Fraction sum(Fraction fraction) {
        int a, b, nod;
        if (this.getDenominator() == fraction.getDenominator()) {
            a = this.getNumerator() + fraction.getNumerator();
            b = this.getDenominator();
        } else {
            a = fraction.getDenominator() * this.getNumerator() + this.getDenominator() * fraction.getNumerator();
            b = this.getDenominator() * fraction.getDenominator();
        }
        nod = nod(a, b);
        return new Fraction(a / nod, b / nod);
    }

    private int nod(int a, int b) {
        while (a != 0 && b != 0) {
            if (a > b)
                a = a % b;
            else
                b = b % a;
        }
        return a + b;
    }

    public Fraction multiply(double number) {
        Fraction doubleNumber = fromDouble(number);
        return new Fraction(this.getNumerator() * doubleNumber.getNumerator(),
                this.getDenominator() * doubleNumber.getDenominator());
    }

    public Fraction division(double number) {
        Fraction doubleNumber = fromDouble(number);
        return new Fraction(this.getNumerator() * doubleNumber.getDenominator(),
                this.getDenominator() * doubleNumber.getNumerator());
    }

    private Fraction fromDouble(double number) {
        // Определяем знак числа
        int sign = number>=0 ? 1 : -1;
        // res = (a>b) ? ( a - b) : (b - a)
//        условие ? значение если истинно : значение если ложно
//        int sign = number < 0 ? -1 : 1;
        number = Math.abs(number);
//        int sign;
//        if(number>=0){
//            sign = 1;
//        } else {
//            sign = -1;
//        }

        // Получаем целую часть числа
        int wholePart = (int) number;

        // Получаем дробную часть числа
        double fractionalPart = number - wholePart;

        // Переводим дробную часть в числитель
        int numerator = (int) (fractionalPart * 1000000); // Умножаем на 1000000 для достаточной точности
        int denominator = 1000000; // Знаменатель

        // Сокращаем дробь, находим НОД числителя и знаменателя
        int gcd = nod(numerator, denominator);

        // Делим числитель и знаменатель на НОД
        numerator /= gcd;
        denominator /= gcd;

        // Если есть целая часть, выводим ее
        if (wholePart != 0) {
            return new Fraction(sign * wholePart * denominator + numerator, denominator);
        } else {
            return new Fraction(sign * numerator, denominator);
        }
    }
}
