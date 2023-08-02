import java.util.ArrayList;
/**
 * The Transaction class represents the transactions made in the vending machine.
 * It keeps track of the starting and ending stock of items, total sales, and the items bought.
 * @author Alliyah Gaberielle D. Zulueta
 */
public class Transaction {
    private int startStock;
    private int endStock;
    private double sales;
    private ArrayList<Item> newItem = new ArrayList<>();
    private ArrayList<Slot> newSlot;
    private ArrayList<String> boughtItems = new ArrayList<>();
    private ArrayList<Integer> boughtQuantities = new ArrayList<>();

    /**
     * The Transaction constructor initializes a transaction with empty lists of items and slots.
     */
    public Transaction() {
        newSlot = new ArrayList<Slot>();
    }

    /**
     * This method returns the ending stock of items after the transactions.
     * @return the ending stock
     */
    public int getEndStock() {
        return endStock;
    }

    /**
     * This method sets the starting stock of items before the transactions.
     * @param startStock the starting stock to set
     */
    public void setStartStock(int startStock) {
        this.startStock = startStock;
    }

    /**
     * This method sets the ending stock of items after the transactions.
     * @param endStock the ending stock to set
     */
    public void setEndStock(int endStock) {
        this.endStock = endStock;
    }


    /**
     * This method sets the ArrayList of slots containing items involved in the transaction.
     * @param newSlot the ArrayList of slots to set
     */
    public void setNewSlot(ArrayList<Slot> newSlot) {
        this.newSlot = newSlot;
    }

    /**
     * This method updates the ending stock of items after a transaction.
     * @param itemName the name of the item being sold
     * @param quantity the quantity of the item being sold
     */
    public void updateEndStocks(String itemName, int quantity) {
        for (Slot slot : newSlot) {
            for (Item item : slot.getItems()) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    int newEndStock = item.getAmount() - quantity;
                    item.setAmount(newEndStock);
                    slot.setAvailableItems(slot.getAvailableItems() - quantity);
                    item.incrementQuantitySold(quantity); // Increment the quantitySold for the sold item
                    break;
                }
            }
        }
    }

    /**
     * This method updates the total sales amount by adding the given amount.
     * @param amount the amount to add to the total sales
     */
    public void updateTotalSales(double amount) {
        sales += amount;
    }

    /**
     * This method adds the bought item and quantity to the transaction details.
     * @param item     the name of the item bought
     * @param quantity the quantity of the item bought
     */
    public void addToTransaction(String item, int quantity) {
        boughtItems.add(item);
        boughtQuantities.add(quantity);
    }

}
