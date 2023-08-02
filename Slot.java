import java.util.ArrayList;
/**
 * The Slot class represents a slot in a vending machine that can hold items.
 * Each slot has an available space, an ArrayList to store Item objects, a slot number,
 * available items, and the slot's capacity.
 * @author Alliyah Gaberielle D. Zulueta
 */
public class Slot {
    // available space in the slot
    private int availableSpaces;

    // ArrayList to store Item objects
    private ArrayList<Item> items;

    // number of slots
    private int numSlot;

    // available items
    private int availableItems;

    //the slots capacity or how much a slot can contain
    private int slotCapacity;

    /**
     * The Slot constructor initializes a slot with the provided parameters.
     * @param numSlot         the slot number
     * @param availableItems  the initial number of available items in the slot
     * @param availableSpaces the initial number of available spaces in the slot
     * @param slotCapacity    the capacity of the slot or how much it can contain
     */
    public Slot(int numSlot, int availableItems, int availableSpaces, int slotCapacity) {
        this.numSlot = numSlot;
        this.items = new ArrayList<>(); // Initialize the ArrayList for items
        this.availableItems = availableItems;
        this.availableSpaces = availableSpaces;
        this.slotCapacity = slotCapacity;
    }

    /**
     * This method returns the ArrayList that stores the items in the slot.
     * @return the ArrayList of Item objects in the slot
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * This method returns the number of available items in the slot.
     * @return the number of available items
     */
    public int getAvailableItems() {
        return availableItems;
    }

    /**
     * This method returns the capacity of the slot or how much it can contain.
     * @return the slot's capacity
     */
    public int getSlotCapacity() {
        return slotCapacity;
    }

    /**
     * This method sets the number of available items in the slot.
     * @param availableItems the number of available items to set
     */
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    /**
     * This method sets the capacity of the slot or how much it can contain.
     * @param slotCapacity the capacity of the slot to set
     */
    public void setSlotCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    /**
     * This method sets the slot number.
     * @param numSlot the slot number to set
     */
    public void setNumSlot(int numSlot) {
        this.numSlot = numSlot;
    }

    /**
     * This method dispenses the specified quantity of items from the slot.
     * It reduces the available items in the slot by the dispensed quantity.
     * @param quantity the quantity of items to dispense
     * @return the updated number of available items in the slot after dispensing
     */
    public int dispenseItem(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Please enter a valid quantity.");
            return availableItems;
        }

        if (availableItems == 0) {
            System.out.println("Error: Item is out of stock.");
            return 0;
        }

        int itemsToDispense = Math.min(quantity, availableItems);
        availableItems -= itemsToDispense;

        return availableItems;
    }

    /**
     * This method returns the quantity of a specific item that has been dispensed from the slot.
     * @param itemName the name of the item to check for dispensed quantity
     * @return the quantity of the specified item that has been dispensed from the slot
     */
    public int getDispensedItem(String itemName) {
        int dispensedItemQuantity = 0;
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                dispensedItemQuantity = item.getAmount() - availableItems;
                break;
            }
        }
        return dispensedItemQuantity;
    }
}
