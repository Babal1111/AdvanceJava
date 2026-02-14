package org.example;

public class PaymentService {

    public boolean processPayment(double amount){
        System.out.println("Process payment of $"+amount);
        return true;
    }

    public  String getTransactionId(double amount){
        return "TXN" + System.currentTimeMillis();
    }

}
