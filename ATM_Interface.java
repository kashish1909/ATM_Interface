import java.util.Scanner;

class BankAccount {
    private String accountholder;
    private String accountnum;
    private double balance1;

    BankAccount(String accountholder, String accountnum, double balance1) {
        this.accountholder = accountholder;
        this.accountnum = accountnum;
        this.balance1 = balance1;
    }

    public double getBalance() {
        return balance1;
    }

    public void withdraw(double amount) {
        balance1 = balance1 -amount;
    }

    public void deposit1(double amount) {
        balance1 =balance1+ amount;
    }
}
public class ATM_Interface {
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccount currentAcc;
    
    private static void showWelcomeMessage() {
        System.out.println("Welcome to the ATM!");
    }
    private static int userInput(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }
    private static boolean authenticateUser(int userId, int pin) {
        if (userId == 2130 && pin == 1819) {
            currentAcc = new BankAccount("Kashish Dhabuwala", "012345", 200000.0);
            return true;
        }
        return false;
    }
    private static void showMenu() {
        System.out.println("\nATM Menu\nWhich operation you want to perform?? ");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
    }
    private static void performOp(int option) {
        switch (option) {
            case 1:
                viewTransactionHistory();
                break;
            case 2:
                withdrawal();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transfer();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    private static void viewTransactionHistory() {
        System.out.println("Transaction History:");
        System.out.println("$" +currentAcc.getBalance());
    }
    private static void withdrawal() {
        double amount = userInput("Enter withdrawal amount: ");
        if (currentAcc.getBalance() >= amount) {
            currentAcc.withdraw(amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + currentAcc.getBalance());
        } else {
            System.out.println("Insufficient funds. Unable to perform withdrawal.");
        }
    }
    private static void deposit() {
        double amount = userInput("Enter deposit amount: ");
        currentAcc.deposit1(amount);
        System.out.println("Deposit successful. Updated balance: $" + currentAcc.getBalance());
    }
    private static void transfer() {
        int recipientAccount = userInput("Enter recipient's account number: ");
        double amount = userInput("Enter transfer amount: ");
        if (currentAcc.getBalance() >= amount) {
            currentAcc.withdraw(amount);
            System.out.println("Transfer successful. Remaining balance: $" + currentAcc.getBalance());
        } else {
            System.out.println("Insufficient funds. Unable to perform transfer.");
        }

       
    }
    public static void main(String[] args) {
        showWelcomeMessage();
        int userId = userInput("Enter User ID: ");
        int pin = userInput("Enter PIN: ");
        if (authenticateUser(userId, pin)) {
            showMenu();
            int option = userInput("Select an option: ");
            while (option != 5) {
                performOp(option);
                showMenu();
                option = userInput("Select an option: ");
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting...");
        }
    }
}