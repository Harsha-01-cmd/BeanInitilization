package in.strikes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * OrderService — eagerly initialized bean that depends on PaymentService.
 *
 * By default, Spring creates singleton beans eagerly when the container starts.
 * OrderService is created first, and its constructor needs PaymentService.
 *
 * Without @Lazy, this would cause a circular dependency error:
 *   OrderService needs PaymentService → PaymentService needs OrderService → cycle detected.
 *
 * @Lazy on the constructor parameter tells Spring to inject a proxy instead of
 * the real PaymentService. The actual PaymentService is only created when its
 * methods are first called at runtime — breaking the initialization cycle.
 */
@Component
public class OrderService {

    private final PaymentService paymentService;

    /**
     * Constructor injection with @Lazy to break circular dependency.
     *
     * @Lazy here means: "don't create PaymentService right now — give me a
     * lightweight proxy instead. Create the real PaymentService later when
     * I actually call a method on it."
     *
     * This is the key technique for resolving circular dependencies in Spring.
     *
     * @param paymentService a proxy that defers PaymentService initialization
     */
    public OrderService(@Lazy PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Places an order by calling PaymentService.pay().
     * This is where the lazy proxy is first triggered — the real
     * PaymentService is created on the first method call.
     */
    public void placeOrder() {
        paymentService.pay();
        System.out.println("Order placed");
    }

    /**
     * Returns order details — a simple business method.
     */
    public void getOrderDetails() {
        System.out.println("Order Details");
    }
}
