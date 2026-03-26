package utcluj.isp.curs2.oop.bank;

public class AccountsManager {
    
    private BankAccount[] accounts;
    private int numAccounts;

    public AccountsManager(int maxAccounts) {
        accounts = new BankAccount[maxAccounts];
        numAccounts = 0;
    }

    public void addAccount(BankAccount account) {
        if (numAccounts < accounts.length) {
            accounts[numAccounts] = account;
            numAccounts++;
        } else {
            System.out.println("Cannot add account - maximum number of accounts reached.");
        }
    }

    public void removeAccount(BankAccount account) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].equals(account)) {
                for (int j = i; j < numAccounts - 1; j++) {
                    accounts[j] = accounts[j+1];
                }
                accounts[numAccounts - 1] = null;
                numAccounts--;
                return;
            }
        }
        System.out.println("Account not found - cannot remove.");
    }

    public BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    public double getAverageBalance() {
        double total = 0;
        for (int i = 0; i < numAccounts; i++) {
            total += accounts[i].getBalance();
        }
        return total / numAccounts;
    }

    public double getAccountBalance(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }
    }

    public BankAccount[] getAccounts() {
        return accounts;
    }

    public int getNumAccounts() {
        return numAccounts;
    }

    public static void main(String[] args) {
        AccountsManager manager = new AccountsManager(10);
        BankAccount account1 = new BankAccount("12345", "John Doe", 1000.0);
        BankAccount account2 = new BankAccount("67890", "Jane Smith", 500.0);
        manager.addAccount(account1);
        manager.addAccount(account2);

        BankAccount account3 = manager.findAccount("12345666");
        if(account3!=null)
            System.out.println(account3.getAccountNumber() + " " + account3.getAccountHolderName() + " " + account3.getBalance());
        else
            System.out.println("Account not found!");

        System.out.println("...............................................");
        System.out.println("Account balance: " + manager.getAccountBalance("12345"));
        System.out.println("Account balance: " + manager.getAccountBalance("67890"));

        manager.transfer("12345", "67890", 100.0);

        System.out.println("Account balance: " + manager.getAccountBalance("12345"));
        System.out.println("Account balance: " + manager.getAccountBalance("67890"));


    }
}
