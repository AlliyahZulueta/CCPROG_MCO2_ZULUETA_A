/**
 * The Item class represents an item that can be sold in a vending machine.
 * Each item has a name, price, calorie count, quantity, and quantity sold.
 * @author Alliyah Gaberielle D. Zulueta
 */
public class Item {

    // name of the item
    private final String name;

    // price of the item
    private double price;

    // calorie count of the item
    private final int calorie;

    // quantity of items
    private int amount;

    // quantity of items sold
    private int quantitySold;

    /** The Item Constructor initializes the item that would be sold in the vending machine
     * @param name name of the item
     * @param price price of the item
     * @param calorie calorie (kcal) count for the item
     * @param amount amount is the quantity of items
     */
    public Item(String name, double price, int calorie, int amount){
        this.name = name;
        this.price = price;
        this.calorie = calorie;
        this.amount = amount;
        this.quantitySold = 0;
    }

    /** This method is used to return the name of the item
     * @return name the item name to be sold
     */
    public String getName(){
        return this.name;
    }

    /** This method is used to return the price of the item
     * @return price the price of an item
     */
    public double getPrice(){
        return this.price;
    }

    /** This method is used to return the quantity of item in a slot
     * @return the amount or quantity of items
     */
    public int getAmount(){
        return this.amount;
    }

    /** This method is used to return the calorie count (kcal) of the item
     * @return calorie the calorie of the item
     */
    public int getCalorie() {
        return this.calorie;
    }

    /** This method sets the price of the item
     * @param price price of the item
     */
    public double setPrice(double price){
        this.price = price;
        return this.price;
    }

    /** This method sets the quantity of item
     * @param amount amount is the quantity of an item
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /** This method increments the quantity of the item sold.
     * @param quantity the quantity of the item sold to be incremented
     */
    public void incrementQuantitySold(int quantity) {
        this.quantitySold += quantity;
    }


}
