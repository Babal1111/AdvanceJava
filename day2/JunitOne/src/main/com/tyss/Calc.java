package main.com.tyss;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Calc {
    public static void main(String[] args) {

        System.out.println("Program Started");

        Calculator cal = new Calculator();
        int a = 6;
        int b = 3;

        int sum = cal.add(a,b);
        int diff = cal.add(a,b);
        int product = cal.multiply(a,b);
        int divide = cal.divide(a,b);

        System.out.println(
                "Sum: " + sum +
                        "\nDifference: " + diff +
                        "\nProduct: " + product +
                        "\nDivide: " + divide
        );

        System.out.println("Program Ended");

    }
}