import java.util.ArrayList;
import java.util.Scanner;

/**
 * The SpecialVendingMachine class represents a special vending machine that sells individual items and creates fruit bowls.
 * It extends the Payment class and handles the creation, restocking, displaying of items, and handling transactions.
 * @author Alliyah Gaberielle D. Zulueta
 */
public class SpecialVendingMachine extends Payment{
    private ArrayList<Item> newItems;
    private ArrayList<Slot> newSlot;
    private Transaction transaction;

    /**
     * Constructs a new SpecialVendingMachine object with the given list of slots.
     * @param newSlot the ArrayList of Slot objects representing the slots in the vending machine
     */
    public SpecialVendingMachine(ArrayList<Slot> newSlot) {
        this.newItems = new ArrayList<>();
        this.transaction = new Transaction();
        if (newSlot == null) {
            this.newSlot = new ArrayList<>();
        } else {
            this.newSlot = newSlot;
        }
    }

    /**
     * Creates a fruit bowl using the items available in the special vending machine.
     * @return the total price of the created fruit bowl, or 0.0 if no items are available or the creation is canceled.
     */
    public double createFruitBowl() {
        System.out.println("Creating a Fruit Bowl using the items in the Special Vending Machine:");
        System.out.println("Available items to choose from:");

        // Display available items (excluding chocolate, cream-cheese, and bowl)
        ArrayList<Item> availableItems = new ArrayList<>();
        for (Slot slot : newSlot) {
            for (Item item : slot.getItems()) {
                if (!item.getName().equalsIgnoreCase("chocolate") &&
                        !item.getName().equalsIgnoreCase("cream-cheese") &&
                        !item.getName().equalsIgnoreCase("bowl")) {
                    System.out.println("Item: " + item.getName());
                    System.out.println("Price: Php " + item.getPrice());
                    System.out.println("Calorie: " + item.getCalorie() + " kcal ");
                    System.out.println("Quantity: " + item.getAmount());
                    System.out.println();
                    availableItems.add(item);
                }
            }
        }

        if (availableItems.isEmpty()) {
            System.out.println("No individual fruits available for sale.");
            return 0.0; // Return 0.0 if no items are available for sale
        }

        Scanner sc = new Scanner(System.in);
        ArrayList<Item> chosenItems = new ArrayList<>();

        System.out.println("Choose items to combine:");
        System.out.println("\nPreparing your Bowl...");
        System.out.println();
        Item bowlItem = newItems.get(2); // Bowl is at index 2 in the newItems list
        bowlItem.setAmount(1);
        chosenItems.add(bowlItem);

        System.out.print("Do you want Chocolate dip? (yes/no)? ");
        String choice = sc.next().toLowerCase();
        if (choice.equals("yes")) {
            System.out.println("Adding your Chocolate dip in your bowl...");
            System.out.println();
            Item chocolateItem = newItems.get(0); // Chocolate is at index 0 in the newItems list
            chocolateItem.setAmount(1); // Set the quantity of chocolate to 1
            chosenItems.add(chocolateItem);
        }

        System.out.print("Do you want Cream Cheese dip? (yes/no)? ");
        choice = sc.next().toLowerCase();
        if (choice.equals("yes")) {
            System.out.println("Adding your Cream cheese dip in your bowl...");
            System.out.println();
            Item creamCheeseItem = newItems.get(1); // Cream Cheese is at index 1 in the newItems list
            creamCheeseItem.setAmount(1); // Set the quantity of cream cheese to 1
            chosenItems.add(creamCheeseItem);
        }

        while (true) {
            System.out.print("Enter the name of the fruit to add (-1 to finish adding): ");
            String fruitName = sc.next().toLowerCase();

            if (fruitName.equals("-1")) {
                break;
            }

            boolean found = false;
            for (Item availableItem : availableItems) {
                if (availableItem.getName().equalsIgnoreCase(fruitName)) {
                    found = true;

                    // Check if the selected item is available for purchase
                    if (availableItem.getAmount() > 0) {
                        System.out.print("Choose quantity (maximum " + availableItem.getAmount() + "): ");
                        int quantity = sc.nextInt();

                        if (quantity > availableItem.getAmount()) {
                            System.out.println("Insufficient quantity. Available quantity: " + availableItem.getAmount());
                            continue; // Ask the user to input the quantity again
                        } else {
                            // Reduce the stock of the selected item by the chosen quantity
                            for (Slot slot : newSlot) {
                                for (Item item : slot.getItems()) {
                                    if (item.getName().equalsIgnoreCase(fruitName)) {
                                        int remainingCount = slot.dispenseItem(quantity);
                                        availableItem.setAmount(availableItem.getAmount() - quantity);
                                        double itemTotalPrice = quantity * availableItem.getPrice();
                                        System.out.println("Dispensed " + quantity + " " + fruitName + ".");
                                        System.out.println("Price: Php " + itemTotalPrice);
                                        System.out.println("Quantity left: " + remainingCount);
                                        chosenItems.add(new Item(fruitName, availableItem.getPrice(), availableItem.getCalorie(), quantity)); // Add the chosen item to the list
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("The selected item is not available.");
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid fruit name. Please choose a fruit from the list.");
            }
        }

        // Check if at least one item (other than the bowl) is added
        if (chosenItems.size() <= 1) {
            System.out.println("No items selected (other than the bowl). Fruity Bowl creation canceled.");
            return 0.0; // Return 0.0 if the fruit bowl is not created due to insufficient items
        }

        double totalPrice = 0;

        System.out.println();
        System.out.println("Wait a moment...");
        System.out.println();
        System.out.println("Mixing all your fruits in your bowl!");
        System.out.println("\nFruity Bowl has been created:");
        for (Item chosenItem : chosenItems) {
            System.out.println("Name: " + chosenItem.getName());
            System.out.println("Price: Php " + chosenItem.getPrice());
            System.out.println("Calorie: " + chosenItem.getCalorie() + " kcal ");

            // Calculate the total price for each chosen item using its quantity (which is now set to 1)
            double itemTotalPrice = chosenItem.getPrice() * chosenItem.getAmount();
            System.out.println("Total Price for " + chosenItem.getAmount() + " " + chosenItem.getName() + ": Php " + itemTotalPrice);

            totalPrice += itemTotalPrice; // Accumulate the total price
        }

        System.out.println("\nTotal Price of the Fruity Bowl: Php " + totalPrice);
        return totalPrice; // Return the total price of the fruit bowl
    }


    /**
     * Allows the buyer to purchase individual items from the special vending machine.
     * @return the total price of the items bought, or 0.0 if no items are available or the purchase cannot be completed.
     */
    public double buyingIndividualItems() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Buying Individual Items:");
        ArrayList<Item> availableItems = new ArrayList<>();

        for (Slot slot : newSlot) {
            for (Item item : slot.getItems()) {
                if (!item.getName().equalsIgnoreCase("chocolate") &&
                        !item.getName().equalsIgnoreCase("cream-cheese") &&
                        !item.getName().equalsIgnoreCase("bowl")) {
                    System.out.println("Item: " + item.getName());
                    System.out.println("Price: Php " + item.getPrice());
                    System.out.println("Calorie: " + item.getCalorie() + " kcal ");
                    System.out.println("Quantity: " + item.getAmount());
                    System.out.println();
                    availableItems.add(item);
                }
            }
        }

        if (availableItems.isEmpty()) {
            System.out.println("No individual fruits available for sale.");
            return 0.0; // Return 0.0 if no items are available for sale
        }

        System.out.print("Choose your Item: ");
        String fruitName = sc.next().toLowerCase();

        if (fruitName.equals("-1")) {
            return 0.0; // Return 0.0 if the user chose to finish buying
        }

        boolean found = false;
        for (Item availableItem : availableItems) {
            if (availableItem.getName().equalsIgnoreCase(fruitName)) {
                found = true;

                System.out.print("Choose quantity: ");
                int quantity = sc.nextInt();

                if (quantity > availableItem.getAmount()) {
                    System.out.println("Insufficient quantity. Available quantity: " + availableItem.getAmount());
                    return 0.0; // Return 0.0 as the purchase cannot be completed
                } else {
                    // Reduce the stock of the selected item by the chosen quantity
                    for (Slot slot : newSlot) {
                        for (Item item : slot.getItems()) {
                            if (item.getName().equalsIgnoreCase(fruitName)) {
                                int remainingCount = slot.dispenseItem(quantity);
                                double itemTotalPrice = quantity * item.getPrice();
                                System.out.println();
                                System.out.println("Dispensed " + quantity + " " + fruitName + ".");
                                System.out.println("Price: Php " + itemTotalPrice);
                                System.out.println("Quantity left: " + remainingCount);
                                // Update the end stocks for the transaction
                                transaction.updateEndStocks(fruitName, quantity);
                                return itemTotalPrice; // Return the total price of the items bought
                            }
                        }
                    }
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid fruit name. Please choose a fruit from the list.");
        }
        return 0.0; // Return 0.0 if no items are bought or invalid fruit name
    }

    /**
     * Creates the special vending machine by setting up slots and items based on user input.
     */
    public void createSpecialVendingMachine() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Slot> newSlots = new ArrayList<>();

        System.out.println("Create the Special Vending Machine");

        int slotCapacity;
        do {
            System.out.print("Enter the number of slots (minimum of 8): ");
            slotCapacity = sc.nextInt();
            if (slotCapacity < 8) {
                System.out.println("Slot capacity should be 8 or more. Please try again.");
            }
        } while (slotCapacity < 8);

        int count;
        do {
            System.out.print("Enter the quantity a Slot can hold (minimum 10): ");
            count = sc.nextInt();
            if (count < 10) {
                System.out.println("Count should be 10 or more. Please try again.");
            }
        } while (count < 10);

        // Initialize items at index 0, 1, and 2 with default quantities of 0
        Item chocolate = new Item("chocolate", 60, 68, 0);
        Item creamCheese = new Item("cream-cheese", 60, 100, 0);
        Item bowl = new Item("bowl", 20, 0, 0);

        int chocolateQuantity;
        int creamCheeseQuantity;
        int bowlQuantity;

        // Allow the user to input the quantity of chocolate, cream-cheese, and bowl
        do {
            System.out.print("Enter the quantity of chocolate (maximum " + count + "): ");
            chocolateQuantity = sc.nextInt();
            if (chocolateQuantity > count) {
                System.out.println("Error: Quantity exceeds the slot capacity. Please try again.");
            }
        } while (chocolateQuantity > count);

        do {
            System.out.print("Enter the quantity of cream-cheese (maximum " + count + "): ");
            creamCheeseQuantity = sc.nextInt();
            if (creamCheeseQuantity > count) {
                System.out.println("Error: Quantity exceeds the slot capacity. Please try again.");
            }
        } while (creamCheeseQuantity > count);

        do {
            System.out.print("Enter the quantity of bowl (maximum " + count + "): ");
            bowlQuantity = sc.nextInt();
            if (bowlQuantity > count) {
                System.out.println("Error: Quantity exceeds the slot capacity. Please try again.");
            }
        } while (bowlQuantity > count);

        // Set the quantities for chocolate, cream-cheese, and bowl
        chocolate.setAmount(chocolateQuantity);
        creamCheese.setAmount(creamCheeseQuantity);
        bowl.setAmount(bowlQuantity);

        // Add items to the newItems ArrayList
        ArrayList<Item> newItems = new ArrayList<>();
        newItems.add(chocolate);
        newItems.add(creamCheese);
        newItems.add(bowl);

        // Create slots for each item and add items to the corresponding slots
        Slot slot1 = new Slot(1, chocolate.getAmount(), (count - chocolate.getAmount()), count);
        Slot slot2 = new Slot(2, creamCheese.getAmount(), (count - creamCheese.getAmount()), count);
        Slot slot3 = new Slot(3, bowl.getAmount(), (count - bowl.getAmount()), count);

        slot1.getItems().add(chocolate);
        slot2.getItems().add(creamCheese);
        slot3.getItems().add(bowl);

        // Add slots to the newSlots ArrayList
        newSlots.add(slot1);
        newSlots.add(slot2);
        newSlots.add(slot3);

        for (int i = 3; i < slotCapacity; i++) {
            System.out.println();
            System.out.println("Slot " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Price: Php ");
            double price = sc.nextDouble();

            System.out.print("Calorie: ");
            int calorie = sc.nextInt();

            int quantity;
            do {
                System.out.print("Quantity: ");
                quantity = sc.nextInt();
                if (quantity > count) {
                    System.out.println("Error: Quantity exceeds the slot capacity. Please try again.");
                }
            } while (quantity > count);

            Item newItem = new Item(name, price, calorie, quantity);

            // Create a new slot and add the item to the slot
            Slot newSlot = new Slot((i + 1), quantity, (count - quantity), count);
            newSlot.getItems().add(newItem);
            newSlot.setSlotCapacity(count);
            newSlot.setNumSlot((i + 1));
            newSlots.add(newSlot);

            // Set the start stock of the item to the initial quantity
            newItem.setAmount(quantity);
        }

        // After creating all slots and items, set the newSlot attribute in the RegularVendingMachine
        this.newSlot = newSlots;

        // Create a new Payment object and pass the newSlots ArrayList to its constructor
        Payment payment = new Payment(newSlots);

        // Set the newSlot attribute in the Transaction class
        transaction.setNewSlot(newSlots);

        // Set the newItems attribute in the SpecialVendingMachine
        this.newItems = newItems;

        System.out.println("Vending Machine created successfully!");
    }

    /**
     * Displays the items available in the special vending machine along with their details.
     */
    public void displayItems() {
        if (newSlot == null || newSlot.isEmpty()) {
            System.out.println("No items in the Vending Machine.");
            return;
        }

        System.out.println("Items in the Vending Machine:");
        System.out.println();
        for (int i = 0; i < newSlot.size(); i++) {
            Slot slot = newSlot.get(i);
            ArrayList<Item> items = slot.getItems();

            System.out.println("Slot " + (i + 1) + ":");

            if (items.isEmpty()) {
                System.out.println("No items in this slot.");
            } else {
                for (int j = 0; j < items.size(); j++) {
                    Item item = items.get(j);
                    System.out.println("Name: " + item.getName());
                    System.out.println("Price: Php " + item.getPrice());
                    System.out.println("Calorie: " + item.getCalorie() +" kcal");
                    System.out.println("Quantity: " + item.getAmount());
                    System.out.println();
                }
            }
        }
    }

    /**
     * Gives change to the buyer after a successful purchase.
     * @param buyerMoney   the amount of money inserted by the buyer
     * @param productAmount the total price of the purchased items
     */
    public void giveChangeSpecial(double buyerMoney, double productAmount) {
        if (buyerMoney < productAmount) {
            System.out.println("Error: Insufficient money. Please insert more money.");
            return;
        }

        // Update the total sales in the transaction class
        transaction.updateTotalSales(productAmount);

        // Create a new Money object to represent the product amount
        Money productMoney = new Money();
        productMoney.setN1(productAmount);

        // Provide change to the buyer
        double change = buyerMoney - productAmount;
        System.out.println("Change: Php " + change);
    }

    /**
     * Sets a new price for an item in the special vending machine.
     */
    public void setNewPrice() {
        Scanner sc = new Scanner(System.in);
        boolean continueSettingPrices = true;

        while (continueSettingPrices) {
            // Ask for the item name
            System.out.print("Enter the name of the item: ");
            String itemName = sc.nextLine();

            Item foundItem = null;
            Slot foundSlot = null;

            for (Slot slot : newSlot) {
                for (Item item : slot.getItems()) {
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        foundItem = item;
                        foundSlot = slot;
                        break;
                    }
                }
                if (foundItem != null) {
                    break;
                }
            }

            if (foundItem != null && foundSlot != null) {
                // Ask for the new price
                System.out.print("Enter the new price for " + foundItem.getName() + ": ");
                double newPrice = sc.nextDouble();

                // Update the item's price
                foundItem.setPrice(newPrice);

                System.out.println("The price of " + foundItem.getName() + " has been updated to " + foundItem.getPrice());
            } else {
                System.out.println("Error: Item not found.");
            }

            // Ask the user for the next action
            System.out.println("\n[1] Set new Price for another item");
            System.out.println("[2] Exit");
            System.out.print("Select an option: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume the remaining newline character

            if (option == 2) {
                continueSettingPrices = false;
            }
        }
    }

    /**
     * Restocks items in a specific slot of the special vending machine.
     * @param quantity the quantity to restock
     */
    public void restockItemsInDriver(int quantity) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the name of the item to restock: ");
        String itemName = sc.nextLine();

        System.out.print("Enter the quantity to restock: ");
        int count = sc.nextInt();

        boolean itemFound = false;
        for (Slot slot : newSlot) {
            for (Item newItem : slot.getItems()) {
                if (newItem.getName().equalsIgnoreCase(itemName)) {
                    itemFound = true;
                    int availableSpace = slot.getSlotCapacity() - slot.getAvailableItems();
                    if (availableSpace >= count) {
                        slot.setAvailableItems(slot.getAvailableItems() + count); // Increase the available items in the slot
                        System.out.println("Name: " + newItem.getName());
                        System.out.println("Available stock: " + slot.getAvailableItems());
                        System.out.println("Item restocked successfully!");
                    } else {
                        System.out.println("Error: Insufficient space in the slot to restock the item.");
                    }
                    break;
                }
            }
            if (itemFound) {
                break; // Exit the loop if the item is found in any slot
            }
        }
        if (!itemFound) {
            System.out.println("Error: Item not found.");
        }
    }

    /**
     * Displays the total sales from all transactions in the special vending machine.
     */
    public void displayTotal() {
        double totalSales = 0.0;

        // Loop through all slots to get the total sales
        for (Slot slot : newSlot) {
            for (Item item : slot.getItems()) {
                int quantitySold = slot.getDispensedItem(item.getName());
                totalSales += item.getPrice() * quantitySold;
            }
        }

        // Display the total sales
        System.out.println("Total Sales: Php " + totalSales);
    }

    /**
     * Displays the inventory of items in the special vending machine, including starting and ending stock.
     */
    public void displayInventory() {
        for (Slot slot : newSlot) {
            for (Item item : slot.getItems()) {
                int startingStock = item.getAmount();
                int endingStock = startingStock - slot.getDispensedItem(item.getName());

                System.out.println("Item: " + item.getName());
                System.out.println("Starting Stock: " + startingStock);
                System.out.println("Ending Stock: " + endingStock);
                System.out.println();
            }
        }
    }

    /**
     * Checks if any slot in the special vending machine is empty (no items).
     * @return true if any slot is empty, false otherwise
     */
    public boolean isSlotEmpty() {
        for (Slot nslot : newSlot) {
            if (nslot.getItems().isEmpty())
                return true;
        }
        return false;
    }

}

