package aut.isp.ppt2026;

import java.io.IOException;
import java.util.*;

/**
 * MINIMAL Demo: Error and Exception Handling in Java
 * Topics covered:
 * 1. Exception hierarchy (Throwable, Error, Exception, RuntimeException)
 * 2. Checked vs Unchecked exceptions
 * 3. try-catch-finally
 * 4. Multiple catch blocks (order matters)
 * 5. Multi-catch (Java 7+)
 * 6. throw and throws
 * 7. try-with-resources (Java 7+) with AutoCloseable
 * 8. Custom exceptions (checked and unchecked)
 * 9. Re-throwing and wrapping
 * 10. Best practices
 */
public class GestionareEroriDemo {

    public static void main(String[] args) {
        System.out.println("=== EXCEPTION HANDLING DEMO ===\n");

        // 1. Exception Hierarchy
        demo1ExceptionHierarchy();

        // 2. Checked vs Unchecked
        demo2CheckedVsUnchecked();

        // 3. try-catch-finally
        demo3TryCatchFinally();

        // 4. Multiple catch blocks
        demo4MultipleCatchBlocks();

        // 5. Multi-catch (Java 7+)
        demo5MultiCatch();

        // 6. throw and throws
        demo6ThrowAndThrows();

        // 7. try-with-resources
        demo7TryWithResources();

        // 8. Custom exceptions
        demo8CustomExceptions();

        // 9. Re-throwing and wrapping
        demo9ReThrowingAndWrapping();

        // 10. Best practices
        demo10BestPractices();
    }

    // ========== DEMO 1: Exception Hierarchy ==========
    private static void demo1ExceptionHierarchy() {
        System.out.println("1. EXCEPTION HIERARCHY");
        System.out.println("Throwable");
        System.out.println("  ├─ Error (serious problems, don't catch)");
        System.out.println("  │   ├─ OutOfMemoryError");
        System.out.println("  │   └─ StackOverflowError");
        System.out.println("  └─ Exception (normal problems, can be caught)");
        System.out.println("      ├─ RuntimeException (unchecked)");
        System.out.println("      │   ├─ NullPointerException");
        System.out.println("      │   ├─ ArrayIndexOutOfBoundsException");
        System.out.println("      │   └─ ArithmeticException");
        System.out.println("      └─ Checked Exceptions");
        System.out.println("          ├─ IOException");
        System.out.println("          └─ SQLException\n");
    }

    // ========== DEMO 2: Checked vs Unchecked ==========
    private static void demo2CheckedVsUnchecked() {
        System.out.println("2. CHECKED vs UNCHECKED");

        // Unchecked: RuntimeException (don't need to declare or catch)
        try {
            int[] arr = {1, 2, 3};
            System.out.println("Array element: " + arr[10]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught unchecked exception: " + e.getClass().getSimpleName());
        }

        // Checked: IOException (must declare or catch)
        try {
            simulateFileRead("test.txt");
        } catch (IOException e) {
            System.out.println("Caught checked exception: " + e.getClass().getSimpleName() + "\n");
        }
    }

    private static void simulateFileRead(String filename) throws IOException {
        // Simulating a checked exception that must be declared
        throw new IOException("File not found: " + filename);
    }

    // ========== DEMO 3: try-catch-finally ==========
    private static void demo3TryCatchFinally() {
        System.out.println("3. TRY-CATCH-FINALLY");

        try {
            System.out.println("In try block: attempting division");
            int result = 10 / 2; // No exception
            System.out.println("Division result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException");
        } finally {
            System.out.println("Finally block always executes (cleanup, resource closing, etc.)\n");
        }
    }

    // ========== DEMO 4: Multiple catch blocks (order matters!) ==========
    private static void demo4MultipleCatchBlocks() {
        System.out.println("4. MULTIPLE CATCH BLOCKS (specific before general)");

        try {
            String text = "abc";
            int number = Integer.parseInt(text); // NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Specific catch: NumberFormatException");
        } catch (IllegalArgumentException e) {
            // Never reached if NumberFormatException is thrown (it's more specific)
            System.out.println("General catch: IllegalArgumentException");
        } catch (Exception e) {
            // Catches anything else
            System.out.println("Catch-all: Exception");
        }
        System.out.println("(Always put specific exceptions BEFORE general ones)\n");
    }

    // ========== DEMO 5: Multi-catch (Java 7+) ==========
    private static void demo5MultiCatch() {
        System.out.println("5. MULTI-CATCH (Java 7+)");

        try {
            List<ContBancar> accounts = new ArrayList<>();
            ContBancar acc = accounts.get(0); // IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
            System.out.println("Multi-catch handles multiple exception types with same code\n");
        }
    }

    // ========== DEMO 6: throw and throws ==========
    private static void demo6ThrowAndThrows() {
        System.out.println("6. THROW and THROWS");

        try {
            withdrawWithValidation(null, 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught thrown exception: " + e.getMessage());
        }

        try {
            processUserData("unknown");
        } catch (UserNotFoundException e) {
            System.out.println("Caught custom unchecked exception: " + e.getMessage());
        }
        System.out.println("throw: explicitly throw an exception\n" +
                "throws: declare exception in method signature\n");
    }

    private static void withdrawWithValidation(ContBancar account, double amount)
            throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
    }

    private static void processUserData(String userId) {
        if ("unknown".equals(userId)) {
            throw new UserNotFoundException("aut.isp.ppt2026.User " + userId + " not found");
        }
    }

    // ========== DEMO 7: try-with-resources ==========
    private static void demo7TryWithResources() {
        System.out.println("7. TRY-WITH-RESOURCES (Java 7+)");

        // try-with-resources automatically closes AutoCloseable resources
        try (SimpleResource resource = new SimpleResource("MyResource")) {
            System.out.println("Using resource in try block");
            resource.doSomething();
            // Resource closes automatically, even if exception occurs
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        System.out.println("(AutoCloseable.close() called automatically)\n");
    }

    // ========== DEMO 8: Custom Exceptions ==========
    private static void demo8CustomExceptions() {
        System.out.println("8. CUSTOM EXCEPTIONS");

        ContBancar account = new ContBancar("ACC001", 500.0);
        System.out.println("Account balance: " + account.getBalance());

        try {
            account.withdraw(200);
            System.out.println("After withdrawal: " + account.getBalance());
            account.withdraw(400); // Will throw aut.isp.ppt2026.InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Caught custom checked exception:");
            System.out.println("  Message: " + e.getMessage());
            System.out.println("  Required: " + e.getRequiredAmount());
            System.out.println("  Available: " + e.getAvailableAmount());
        }
        System.out.println("(Custom exceptions add context-specific information)\n");
    }

    // ========== DEMO 9: Re-throwing and Wrapping ==========
    private static void demo9ReThrowingAndWrapping() {
        System.out.println("9. RE-THROWING and WRAPPING");

        try {
            processPayment("invalid");
        } catch (PaymentException e) {
            System.out.println("Caught wrapped exception: " + e.getMessage());
            System.out.println("Original cause: " + e.getCause().getClass().getSimpleName());
        }
        System.out.println("(Use getCause() to access original exception)\n");
    }

    private static void processPayment(String amount) throws PaymentException {
        try {
            double value = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            // Wrap the original exception for better context
            throw new PaymentException("Invalid payment amount: " + amount, e);
        }
    }

    // ========== DEMO 10: Best Practices ==========
    private static void demo10BestPractices() {
        System.out.println("10. BEST PRACTICES");
        System.out.println("✓ Don't ignore exceptions (don't use empty catch blocks)");
        System.out.println("✓ Don't catch Throwable or Error (too broad)");
        System.out.println("✓ Use try-with-resources for AutoCloseable resources");
        System.out.println("✓ Specific exceptions before general ones");
        System.out.println("✓ Include context in exception messages");
        System.out.println("✓ Use finally for cleanup or use try-with-resources");
        System.out.println("✓ Create custom exceptions for domain-specific errors");
        System.out.println("✓ Wrap exceptions with cause to preserve stack trace\n");
    }
}

// ========== CUSTOM EXCEPTION: Checked ==========
/**
 * Custom checked exception for insufficient funds
 * Checked because it's a business logic error that callers must handle
 */
class InsufficientFundsException extends Exception {
    private double requiredAmount;
    private double availableAmount;

    public InsufficientFundsException(String message, double required, double available) {
        super(message);
        this.requiredAmount = required;
        this.availableAmount = available;
    }

    public double getRequiredAmount() {
        return requiredAmount;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }
}

// ========== CUSTOM EXCEPTION: Unchecked ==========
/**
 * Custom unchecked exception for user not found
 * Unchecked because it's a programming error that doesn't require declaration
 */
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

// ========== CUSTOM EXCEPTION: Checked ==========
/**
 * Payment processing exception
 * Demonstrates exception wrapping to preserve original cause
 */
class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}

// ========== CUSTOM CLASS: BankAccount ==========
/**
 * Represents a bank account with withdrawal functionality
 * Demonstrates throwing checked exceptions with context
 */
class ContBancar {
    private String accountId;
    private double balance;

    public ContBancar(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Withdraws money from the account
     * Throws checked exception if insufficient funds
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds for withdrawal of " + amount,
                    amount,
                    balance
            );
        }
        balance -= amount;
    }

    /**
     * Deposits money into the account
     * No exception thrown (always succeeds)
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        balance += amount;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.ContBancar{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                '}';
    }
}

// ========== CUSTOM CLASS: AutoCloseable Resource ==========
/**
 * Simple resource that implements AutoCloseable
 * Demonstrates automatic resource closing with try-with-resources
 */
class SimpleResource implements AutoCloseable {
    private String name;
    private boolean closed = false;

    public SimpleResource(String name) {
        this.name = name;
        System.out.println("Resource opened: " + name);
    }

    public void doSomething() {
        if (closed) {
            throw new IllegalStateException("Resource is already closed");
        }
        System.out.println("Resource performing work...");
    }

    @Override
    public void close() throws Exception {
        if (!closed) {
            System.out.println("Resource closing: " + name);
            closed = true;
        }
    }

    public boolean isClosed() {
        return closed;
    }
}
