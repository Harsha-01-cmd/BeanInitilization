# BeanInitializationDemo

Demonstrates how Spring initializes beans and how `@Lazy` controls the timing of bean creation.

## Key Concepts

- **Eager initialization**: Spring creates singleton beans by default when the container starts.
- **Lazy initialization**: `@Lazy` defers bean creation until first use.
- **Circular dependency resolution**: `@Lazy` breaks circular dependency cycles by injecting a proxy.

## Project Structure

- `OrderService` — eagerly created; depends on PaymentService via `@Lazy` constructor parameter
- `PaymentService` — lazily created (`@Lazy` on class); depends on OrderService
- `AppConfig` — enables component scanning
- `Main` — entry point demonstrating the initialization flow

## Running

```bash
mvn compile exec:java -Dexec.mainClass="in.strikes.Main"
```

## Expected Output

```
Payment successful
Order Details
Order placed
```
