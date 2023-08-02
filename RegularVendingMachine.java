import java.util.Scanner;
import java.util.ArrayList;

/**
 * The RegularVendingMachine class represents a vending machine that sells items through regular payment.
 * It extends the Payment class and handles the creation, restocking, displaying of items, and handling transactions.
 * @author Alliyah Gaberielle D. Zulueta
 */
public class RegularVendingMachine extends Payment{
    private ArrayList<Slot> newSlot;
    private Transaction transaction;
    private double totalSales;
    private Money money;

    /**
     * Constructs a new RegularVendingMachine object with the given list of slots.
     * @param newSlot the ArrayList of Slot objects representing the slots in the vending machine
     */
    public RegularVendingMachine(ArrayList<Slot> newSlot) {
        super(newSlot);
        this.newSlot = newSlot;
        this.transaction = new Transaction();
        totalSales = 0.0;
    }

    /**
     * Creates a new regular vending machine by taking user input for slot configuration and item details.
     */
    public void createRegularVendingMachine() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Slot> newSlots = new ArrayList<>();

        System.out.println("Create the Regular Vending Machine");

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

        for (int i = 0; i < slotCapacity; i++) {
            System.out.println();
            System.out.println("Slot " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Price: Php ");
            double price = sc.nextDouble();

            System.out.print("Calorie: " + " kcal");
            int calorie = sc.nextInt();

            int quantity;
            do {
                System.out.print("Quantity: ");
                quantity = sc.nextInt();
                if (quantity > count)
                    System.out.println("Error: There is not enough space in the Slot");

            } while (quantity > count); // Check if the entered quantity exceeds the slot capacity

            Item newItem = new Item(name, price, calorie, quantity);

            // Create a new slot and add the item to the slot
            Slot newSlot = new Slot(1, quantity, (count - quantity), count);
            newSlot.getItems().add(newItem);
            newSlot.setSlotCapacity(count);
            newSlot.setNumSlot(1);
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

        System.out.println("Vending Machine created successfully!");
    }


    /**
     * Displays the items in the vending machine along with their details (name, price, calorie, quantity).
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
                    System.out.println("Calorie: " + item.getCalorie());
                    System.out.println("Quantity: " + item.getAmount());
                    System.out.println();
                }
            }
        }
    }

    /**
     * Processes the buyer's order and dispenses the requested quantity of items if available.
     * @param order    the name of the item the buyer wants to purchase
     * @param quantity the quantity of the item the buyer wants to purchase
     */
    public void buyersOrderSlip(String order, int quantity) {
        int remainingCount = 0;
        boolean itemFound = false;

        for (Slot nslot : newSlot) {
            for (Item nitem : nslot.getItems()) {
                if (nitem.getName().equalsIgnoreCase(order)) {
                    itemFound = true;
                    // Check if the quantity in the slot is sufficient for the order
                    if (nitem.getAmount() >= quantity) {
                        remainingCount = nslot.dispenseItem(quantity);
                        System.out.println("Notice:");
                        System.out.println("Dispensed " + quantity + " " + order + "(s).");
                        System.out.println("Quantity left: " + remainingCount);
                        System.out.println();
                    } else {
                        System.out.println("Error: Insufficient quantity of " + order + " in the slot.");
                    }
                    break;
                }
            }
            if (itemFound) {
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Error: Item not found.");
        }
    }

    /**
     * Calculates the change to be given to the buyer after a successful purchase.
     * @param order      the name of the item the buyer purchased
     * @param quantity   the quantity of the item the buyer purchased
     * @param buyerMoney the amount of money inserted by the buyer
     */
    public void giveChange(String order, int quantity, double buyerMoney) {
        double itemPrice = 0;
        boolean itemFound = false;

        // Normalize the order input by removing leading and trailing spaces
        order = order.trim();

        for (Slot slot : newSlot) {
            if (!slot.getItems().isEmpty()) { // Skip empty slots
                for (Item item : slot.getItems()) {
                    // Normalize the item name by removing leading and trailing spaces
                    String itemName = item.getName().trim();

                    if (itemName.equalsIgnoreCase(order)) {
                        itemPrice = item.getPrice();
                        itemFound = true;
                        break;
                    }
                }
                if (itemFound) {
                    break;
                }
            }
        }

        if (!itemFound) {
            System.out.println("Error: Item not found in the Vending Machine.");
            return;
        }

        double totalAmount = itemPrice * quantity;
        double change = buyerMoney - totalAmount;

        if (change < 0) {
            System.out.println("Error: Insufficient money. Please insert more money.");
            replenishMoney();
            return;
        }
        System.out.println("Total price: Php " + totalAmount);
        System.out.println("Change: Php " + change);
    }

    /**
     * Sets a new price for an item in the vending machine.
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
     * Restocks items in the vending machine by adding a specific quantity of items to a slot.
     * @param quantity the quantity of items to be restocked
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
     * Displays the total sales for a specific item based on the quantity purchased.
     * @param order    the name of the item for which to display total sales
     * @param quantity the quantity of the item purchased
     */
    public void displayTotal(String order, int quantity) {
        Item selectedItem = null;
        for (Slot nslot : newSlot) {
            for (Item nitem : nslot.getItems()) {
                if (nitem.getName().equalsIgnoreCase(order)) {
                    selectedItem = nitem;
                    break;
                }
            }
        }
        if (selectedItem == null) {
            System.out.println("Error: Item not found.");
            return;
        }

        // Calculate the total sales based on the selected item's price and quantity
        double totalSales = selectedItem.getPrice() * quantity;

        // Add transaction details to the Transaction class
        transaction.addToTransaction(order, quantity);

        // Display the total sales for the current item
        System.out.println("Total Sales for " + quantity + " " + order + "(s): Php " + totalSales);
    }

    /**
     * Displays the current inventory of items in the vending machine.
     */
    public void displayInventory() {
        for (Slot slot : newSlot) {
            for (Item item : slot.getItems()) {
                System.out.println("Item: " + item.getName());
                System.out.println("Starting Stock: " + item.getAmount());
                System.out.println("Ending Stock: " + (item.getAmount() - slot.getDispensedItem(item.getName())));
                System.out.println();
            }
        }
    }

    /**
     * Checks if any of the slots in the vending machine are empty (no items available).
     * @return true if at least one slot is empty, false otherwise
     */
    public boolean isSlotEmpty() {
        for (Slot nslot : newSlot) {
            if (nslot.getItems().isEmpty())
                return true;
        }
        return false;
    }
}
