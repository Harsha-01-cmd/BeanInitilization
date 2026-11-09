package in.strikes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig — Spring configuration class that enables component scanning.
 *
 * @Configuration tells Spring to treat this class as a source of bean definitions.
 * @ComponentScan discovers @Component classes (OrderService, PaymentService) in
 * this package and registers them as beans in the container.
 *
 * Without @ComponentScan, Spring would not find the service classes and they
 * would never be initialized.
 */
@Configuration
@ComponentScan
public class AppConfig {
}
