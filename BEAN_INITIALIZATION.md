# Bean Initialization in Spring

## Eager vs Lazy Initialization

| Aspect | Eager (default) | Lazy (`@Lazy`) |
|--------|-----------------|----------------|
| When created | Container startup | First use |
| Memory | Used immediately | Deferred |
| Use case | Core services | Heavy/optional beans |

## Circular Dependency Resolution

When two beans depend on each other:
```
OrderService → PaymentService → OrderService (cycle!)
```

`@Lazy` breaks the cycle by injecting a proxy instead of the real bean. The actual bean is created only when a method is called on the proxy.

## Two Ways to Use @Lazy

1. **On a class** — defers the entire bean initialization
2. **On a constructor parameter** — defers only that specific dependency
