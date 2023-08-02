import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Payment class represents the payment and money management in the vending machine.
 * It handles adding money, displaying available money denominations, and managing transactions.
 */
public class Payment {
    private ArrayList<Money> newMoney;
    private ArrayList<Slot> newSlot;
    private Transaction transaction;

    /**
     * The Payment constructor initializes the list of new money with an empty ArrayList.
     */
    public Payment() {
        newMoney = new ArrayList<>();
    }

    /**
     * This constructor initializes the Payment with a list of slots.
     * @param newSlot the ArrayList of slots in the vending machine
     */
    public Payment(ArrayList<Slot> newSlot) {
        this.newSlot = newSlot;
        newMoney = new ArrayList<>();
        this.newSlot = new ArrayList<>();
        this.newSlot = newSlot;
        this.transaction = new Transaction();
    }

    /**
     * This method adds money to the vending machine's available money.
     * @param moneyToAdd the money object to add
     */
    public void addMoney(Money moneyToAdd) {
        for (Money existingMoney : newMoney) {
            if (existingMoney.equals(moneyToAdd)) {
                existingMoney.addMoney(moneyToAdd);
                return;
            }
        }

        // If the money does not exist in the list, add it as a new entry
        newMoney.add(moneyToAdd);
    }

    /**
     * This method handles the buyer's payment for the transaction.
     * @return the amount of money inserted by the buyer
     */
    public double buyerPayment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert money: Php ");
        double money = sc.nextDouble();

        System.out.print("Do you want to proceed with the transaction? (yes/no): ");
        String proceed = sc.next();

        if (proceed.equalsIgnoreCase("yes")) {
            return money;
        } else {
            double refundAmount = cancelPayment();
            System.out.println("Transaction canceled. Refund amount: Php " + refundAmount);
            return 0.0; // Return 0 as the transaction was canceled
        }
    }

    /**
     * This method cancels the payment and calculates the refund amount.
     * @return the refund amount
     */
    public double cancelPayment() {
        double refundAmount = 0.0;

        // Check if the newMoney list is not empty
        if (!newMoney.isEmpty()) {
            // Get the last money object from the list
            Money obj = newMoney.get(newMoney.size() - 1);

            // Get the denominations of the specified object
            ArrayList<Money> refundDenominations = getMoneyDenominations(obj.getTotalAmount());

            // Remove the denominations from the newMoney list
            for (Money refundMoney : refundDenominations) {
                newMoney.remove(refundMoney); // Remove the money object from the ArrayList
                refundAmount += refundMoney.getTotalAmount();
            }
        }

        return refundAmount;
    }

    /**
     * This method calculates the frequency of a money object in the newMoney list.
     * @param obj the money object to calculate the frequency for
     * @return the frequency of the specified money object
     */
    public int denominationsFrequency(Money obj) {
        int frequency = 0;

        for (Money money : newMoney) {
            if (money.equals(obj)) {
                frequency++;
            }
        }
        return frequency;
    }

    /**
     * This method calculates the denominations of a given total amount.
     * @param totalAmount the total amount to calculate denominations for
     * @return the ArrayList of money objects representing the denominations
     */
    public ArrayList<Money> getMoneyDenominations(double totalAmount) {
        ArrayList<Money> denominations = new ArrayList<>();

        // Calculate the number of 1000-peso denominations
        int n1000 = (int) (totalAmount / 1000);
        if (n1000 > 0) {
            Money n1000Money = new Money();
            n1000Money.setN1k(n1000);
            denominations.add(n1000Money);
            totalAmount -= n1000 * 1000;
        }

        // Calculate the number of 500-peso denominations
        int n500 = (int) (totalAmount / 500);
        if (n500 > 0) {
            Money n500Money = new Money();
            n500Money.setN500(n500);
            denominations.add(n500Money);
            totalAmount -= n500 * 500;
        }

        // Calculate the number of 100-peso denominations
        int n100 = (int) (totalAmount / 100);
        if (n100 > 0) {
            Money n100Money = new Money();
            n100Money.setN100(n100);
            denominations.add(n100Money);
            totalAmount -= n100 * 100;
        }

        // Calculate the number of 50-peso denominations
        int n50 = (int) (totalAmount / 50);
        if (n50 > 0) {
            Money n50Money = new Money();
            n50Money.setN50(n50);
            denominations.add(n50Money);
            totalAmount -= n50 * 50;
        }

        // Calculate the number of 20-peso denominations
        int n20 = (int) (totalAmount / 20);
        if (n20 > 0) {
            Money n20Money = new Money();
            n20Money.setN20(n20);
            denominations.add(n20Money);
            totalAmount -= n20 * 20;
        }

        // Calculate the number of 10-peso denominations
        int n10 = (int) (totalAmount / 10);
        if (n10 > 0) {
            Money n10Money = new Money();
            n10Money.setN10(n10);
            denominations.add(n10Money);
            totalAmount -= n10 * 10;
        }

        // Calculate the number of 5-peso denominations
        int n5 = (int) (totalAmount / 5);
        if (n5 > 0) {
            Money n5Money = new Money();
            n5Money.setN5(n5);
            denominations.add(n5Money);
            totalAmount -= n5 * 5;
        }

        // Calculate the number of 1-peso denominations
        int n1 = (int) totalAmount;
        if (n1 > 0) {
            Money n1Money = new Money();
            n1Money.setN1(n1);
            denominations.add(n1Money);
        }

        return denominations;
    }

    /**
     * This method prints the total amount of money in the vending machine along with the denominations.
     */
    public void totalAmountWithDenominations() {
        System.out.println("Total Amount of Money in Cashier:");

        for (Money money : newMoney) {
            double totalAmount = money.getTotalAmount();
            int frequency = denominationsFrequency(money);

            System.out.println("Total Amount: " + totalAmount);
            System.out.println("Denominations:");

            if (frequency > 0) {
                System.out.println("1-peso: " + (money.getN1() * frequency));
                System.out.println("5-peso: " + (money.getN5() * frequency));
                System.out.println("10-peso: " + (money.getN10() * frequency));
                System.out.println("20-peso: " + (money.getN20() * frequency));
                System.out.println("50-peso: " + (money.getN50() * frequency));
                System.out.println("100-peso: " + (money.getN100() * frequency));
                System.out.println("200-peso: " + (money.getN200() * frequency));
                System.out.println("500-peso: " + (money.getN500() * frequency));
                System.out.println("1000-peso: " + (money.getN1k() * frequency));
            } else {
                System.out.println("No denominations for this money object.");
            }
            System.out.println();
        }
    }

    /**
     * This method calculates and returns the total amount of money in the vending machine.
     * @return the total amount of money in the vending machine
     */
    public double getTotalAmountInCashier() {
        double totalAmount = 0.0;

        for (Money money : newMoney) {
            totalAmount += money.getTotalAmount();
        }

        return totalAmount;
    }

    /**
     * This method allows the user to add money to the vending machine.
     * It prompts the user to enter the number of different denominations and updates the newMoney list accordingly.
     */
    public void moneyInVendingMachine() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add money to the vending machine:");
        System.out.print("Enter the number of 1-peso coins: ");
        double n1 = sc.nextDouble();

        System.out.print("Enter the number of 5-peso coins: ");
        double n5 = sc.nextDouble();

        System.out.print("Enter the number of 10-peso coins: ");
        double n10 = sc.nextDouble();

        System.out.print("Enter the number of 20-peso coins: ");
        double n20 = sc.nextDouble();

        System.out.print("Enter the number of 50-peso coins: ");
        double n50 = sc.nextDouble();

        System.out.print("Enter the number of 100-peso bills: ");
        double n100 = sc.nextDouble();

        System.out.print("Enter the number of 200-peso bills: ");
        double n200 = sc.nextDouble();

        System.out.print("Enter the number of 500-peso bills: ");
        double n500 = sc.nextDouble();

        System.out.print("Enter the number of 1000-peso bills: ");
        double n1k = sc.nextDouble();

        // Create a new Money object to represent the added money
        Money addedMoney = new Money();
        addedMoney.setN1(n1);
        addedMoney.setN5(n5);
        addedMoney.setN10(n10);
        addedMoney.setN20(n20);
        addedMoney.setN50(n50);
        addedMoney.setN100(n100);
        addedMoney.setN200(n200);
        addedMoney.setN500(n500);
        addedMoney.setN1k(n1k);

        // Add the new Money object to the vending machine
        addMoney(addedMoney);

        // Display the denominations of the added money and the total amount
        totalAmountWithDenominations();
    }

    /**
     * This method allows the user to replenish the money inside the vending machine.
     * It prompts the user to enter the number of different denominations and updates the newMoney list accordingly.
     */
    public void replenishMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Restock the money inside the vending machine");
        System.out.println("Notice: this will be added to the current money");

        Money additionalMoney = new Money();

        System.out.print("Number of 1-peso coins: ");
        double n1 = sc.nextDouble();
        additionalMoney.setN1(n1);

        System.out.print("Number of 5-peso coins: ");
        double n5 = sc.nextDouble();
        additionalMoney.setN5(n5);

        System.out.print("Number of 10-peso coins: ");
        double n10 = sc.nextDouble();
        additionalMoney.setN10(n10);

        System.out.print("Number of 20-peso coins: ");
        double n20 = sc.nextDouble();
        additionalMoney.setN20(n20);

        System.out.print("Number of 50-peso coins: ");
        double n50 = sc.nextDouble();
        additionalMoney.setN50(n50);

        System.out.print("Number of 100-peso bills: ");
        double n100 = sc.nextDouble();
        additionalMoney.setN100(n100);

        System.out.print("Number of 200-peso bills: ");
        double n200 = sc.nextDouble();
        additionalMoney.setN200(n200);

        System.out.print("Number of 500-peso bills: ");
        double n500 = sc.nextDouble();
        additionalMoney.setN500(n500);

        System.out.print("Number of 1000-peso bills: ");
        double n1k = sc.nextDouble();
        additionalMoney.setN1k(n1k);

        // Ensure newMoney list is initialized
        if (newMoney == null) {
            newMoney = new ArrayList<>();
        }

        newMoney.add(additionalMoney);

        System.out.println("Money has been added to the machine.");
        System.out.println("Current money in the machine: Php " + getTotalAmountInCashier());
    }

}
