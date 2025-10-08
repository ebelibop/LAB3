import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private String accountType;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountType() {
        return accountType;
    }

    public void deposit(double amount) {
        double limit = getDepositLimit();
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        if (amount > limit) {
            System.out.println("Deposit exceeds the limit for " + accountType + " accounts.");
            return;
        }
        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("New Balance: $" + balance);
    }

    public void withdraw(double amount) {
        double limit = getWithdrawLimit();
        if (amount <= 0) {
            System.out.println("Invalid withdraw amount.");
            return;
        }
        if (amount > limit) {
            System.out.println("Withdraw exceeds the limit for " + accountType + " accounts.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrew: $" + amount);
        System.out.println("Remaining Balance: $" + balance);
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public void closeAccount() {
        if (balance == 0) {
            System.out.println("Account closed successfully.");
        } else {
            System.out.println("Cannot close account. Balance must be 0.");
        }
    }

    private double getDepositLimit() {
        switch (accountType.toLowerCase()) {
            case "checking": return 20000;
            case "savings": return 8000;
            case "current": return 30000;
            default: return 0;
        }
    }

    private double getWithdrawLimit() {
        switch (accountType.toLowerCase()) {
            case "checking": return 15000;
            case "savings": return 5000;
            case "current": return 20000;
            default: return 0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined Accounts
        BankAccount account1 = new BankAccount("201514026", "Josie Rizal", "Savings", 5000);
        BankAccount account2 = new BankAccount("202015472", "John Dela Cruz", "Checking", 50000);
        BankAccount account3 = new BankAccount("202451635", "Gabriella Silang", "Current", 1000000);

        BankAccount[] accounts = {account1, account2, account3};

        System.out.println("Welcome to ABC Bank ATM!");
        System.out.print("Please Enter your Account Number (or type Cancel): ");
        String accNum = sc.nextLine();

        if (accNum.equalsIgnoreCase("Cancel")) {
            System.out.println("Process cancelled.");
            return;
        }

        BankAccount currentAccount = null;
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNum)) {
                currentAccount = acc;
                break;
            }
        }

        if (currentAccount == null) {
            System.out.println("Account not found. Exiting...");
            return;
        }

        System.out.println("Welcome, " + currentAccount.getAccountHolder());

        while (true) {
            System.out.println("\n-----------------------");
            System.out.println("(1) Deposit");
            System.out.println("(2) Withdraw");
            System.out.println("(3) Check Balance");
            System.out.println("(4) Close Account");
            System.out.println("(5) Exit");
            System.out.print("Enter a task: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to Deposit: ");
                    double dep = sc.nextDouble();
                    currentAccount.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to Withdraw: ");
                    double wd = sc.nextDouble();
                    currentAccount.withdraw(wd);
                    break;
                case 3:
                    currentAccount.checkBalance();
                    break;
                case 4:
                    currentAccount.closeAccount();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
