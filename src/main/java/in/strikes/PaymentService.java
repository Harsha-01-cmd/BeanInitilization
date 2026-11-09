package in.strikes;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * PaymentService — lazily initialized bean that depends on OrderService.
 *
 * @Lazy at the class level means: "don't create this bean when the container
 * starts — wait until something actually requests it."
 *
 * This creates a circular dependency with OrderService:
 *   OrderService → needs PaymentService
 *   PaymentService → needs OrderService
 *
 * The cycle is resolved because @Lazy on the OrderService constructor parameter
 * injects a proxy for PaymentService. The real PaymentService is only created
 * when placeOrder() calls paymentService.pay() for the first time.
 *
 * Initialization order:
 *   1. Container starts → creates OrderService (eager)
 *   2. OrderService constructor receives a proxy for PaymentService (not the real bean)
 *   3. OrderService is fully initialized
 *   4. When placeOrder() is called → proxy triggers PaymentService creation
 *   5. PaymentService is created and injected with the already-existing OrderService
 */
@Component
@Lazy
public class PaymentService {

    private final OrderService orderService;

    /**
     * Constructor injection — receives the real OrderService since it's
     * already created by the time PaymentService is initialized.
     *
     * @param orderService the eagerly created OrderService singleton
     */
    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Processes payment and retrieves order details.
     * This method is only reachable after the lazy proxy resolves
     * and the real PaymentService is created.
     */
    public void pay() {
        System.out.println("Payment successful");
        orderService.getOrderDetails();
    }
}
