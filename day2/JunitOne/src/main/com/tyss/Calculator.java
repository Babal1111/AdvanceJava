package main.com.tyss;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }
    public int multiply(int a, int b) {
        return a * b;
    }
    public boolean isEven(int a) {
        return a % 2 == 0;
    }

    //edge case testing
    public int divide(int a, int b) {
        return a / b;
    }
}