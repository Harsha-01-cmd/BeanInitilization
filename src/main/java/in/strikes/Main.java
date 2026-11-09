package in.strikes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main — demonstrates lazy bean initialization and circular dependency resolution.
 *
 * When the ApplicationContext starts, Spring eagerly creates OrderService.
 * PaymentService is NOT created yet because it is marked @Lazy.
 *
 * Only when order.placeOrder() is called does the lazy proxy resolve,
 * triggering the creation of the real PaymentService.
 *
 * Expected output:
 *   Payment successful
 *   Order Details
 *   Order placed
 *
 * If you uncomment the PaymentService getBean line, it forces the lazy
 * initialization of PaymentService at that point instead of waiting
 * for the method call.
 */
public class Main {
    public static void main(String[] args) {
        // Container startup: OrderService is created eagerly.
        // PaymentService is NOT created yet (@Lazy defers it).
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the eagerly created OrderService singleton.
        OrderService order = context.getBean(OrderService.class);

        // Uncommenting this line would force PaymentService initialization here.
        // Without it, PaymentService stays lazy until placeOrder() triggers it.
        //System.out.println("Payment Service not started yet");

        // This call triggers the lazy proxy: the real PaymentService is
        // created now for the first time, injected with OrderService,
        // and then pay() executes.
        order.placeOrder();

        // Uncommenting this would also trigger lazy initialization of
        // PaymentService if it hasn't been created yet.
        //PaymentService paymentService = context.getBean(PaymentService.class)
    }
}