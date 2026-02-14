package org.example;

public class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public String placeOrder(double amount){
        System.out.println("[OrderService] Placing order...");

        boolean paymentSuccess = paymentService.processPayment(amount);
        if(paymentSuccess){
            return "Order Placed";
        }
        return "Payment Failed";
    }
    //

    public boolean validationAndPlaceOrder(double amount){
        if(amount <=0){
            return false;
        }
       return paymentService.processPayment(amount);
//        return paymentSuccess;
    }



}
