/**
 * The Money class represents the different denominations of money in a vending machine.
 * It keeps track of the quantity of each denomination (1-peso, 5-peso, 10-peso, etc.)
 * and provides methods to manipulate the money.
 * @author Alliyah Gaberielle D. Zulueta
 */
public class Money {
    private double n1;
    private double n5;
    private double n10;
    private double n20;
    private double n50;
    private double n100;
    private double n200;
    private double n500;
    private double n1k;

    /**
     * Constructs a new Money object with all denominations set to 0.
     */
    public Money() {
        n1 = 0;
        n5 = 0;
        n10 = 0;
        n20 = 0;
        n50 = 0;
        n100 = 0;
        n200 = 0;
        n500 = 0;
        n1k = 0;
    }

    /**
     * This method returns the quantity of 1-peso coins.
     * @return the quantity of 1-peso coins
     */
    public double getN1() {
        return n1;
    }

    /**
     * This method sets the quantity of 1-peso coins.
     * @param n1 the quantity of 1-peso coins to set
     */
    public void setN1(double n1) {
        this.n1 = n1;
    }

    /**
     * This method returns the quantity of 5-peso coins.
     * @return the quantity of 5-peso coins
     */
    public double getN5() {
        return n5;
    }

    /**
     * This method sets the quantity of 5-peso coins.
     * @param n5 the quantity of 5-peso coins to set
     */
    public void setN5(double n5) {
        this.n5 = n5;
    }

    /**
     * This method returns the quantity of 10-peso coins.
     * @return the quantity of 10-peso coins
     */
    public double getN10() {
        return n10;
    }

    /**
     * This method returns the quantity of 10-peso coins.
     * @param n10 the quantity of 10-peso coins to set
     */
    public void setN10(double n10) {
        this.n10 = n10;
    }

    /**
     * This method returns the quantity of 20-peso coins.
     * @return the quantity of 20-peso coins
     */
    public double getN20() {
        return n20;
    }

    /**
     * This method sets the quantity of 20-peso coins.
     * @param n20 the quantity of 20-peso coins to set
     */
    public void setN20(double n20) {
        this.n20 = n20;
    }

    /**
     * This method returns the quantity of 50-peso coins.
     * @return the quantity of 50-peso coins
     */
    public double getN50() {
        return n50;
    }

    /**
     * This method sets the quantity of 50-peso coins.
     * @param n50 the quantity of 50-peso coins to set
     */
    public void setN50(double n50) {
        this.n50 = n50;
    }

    /**
     * This method returns the quantity of 100-peso bills.
     * @return the quantity of 100-peso bills
     */
    public double getN100() {
        return n100;
    }

    /**
     * This method returns the quantity of 100-peso bills.
     * @param n100 the quantity of 100-peso bills to set
     */
    public void setN100(double n100) {
        this.n100 = n100;
    }

    /**
     * This method returns the quantity of 200-peso bills.
     * @return the quantity of 200-peso bills
     */
    public double getN200() {
        return n200;
    }

    /**
     * This method sets the quantity of 200-peso bills.
     * @param n200 the quantity of 200-peso bills to set
     */
    public void setN200(double n200) {
        this.n200 = n200;
    }

    /**
     * This method returns the quantity of 500-peso bills.
     * @return the quantity of 500-peso bills
     */
    public double getN500() {
        return n500;
    }

    /**
     * This method sets the quantity of 500-peso bills.
     * @param n500 the quantity of 500-peso bills to set
     */
    public void setN500(double n500) {
        this.n500 = n500;
    }

    /**
     * This method returns the quantity of 1000-peso bills.
     * @return the quantity of 1000-peso bills
     */
    public double getN1k() {
        return n1k;
    }

    /**
     * This method sets the quantity of 1000-peso bills.
     * @param n1k the quantity of 1000-peso bills to set
     */
    public void setN1k(double n1k) {
        this.n1k = n1k;
    }

    /**
     * Calculates and returns the total amount of money in the Money object.
     * @return the total amount of money
     */
    public double getTotalAmount() {
        return n1 + (n5 * 5.0) + (n10 * 10.0) + (n20 * 20.0) + (n50 * 50.0)
                + (n100 * 100.0) + (n200 * 200.0) + (n500 * 500.0) + (n1k * 1000.0);
    }

    /**
     * Adds the quantities of each denomination from the given Money object to this Money object.
     * @param moneyToAdd the Money object containing the quantities of each denomination to add
     */
    public void addMoney(Money moneyToAdd) {
        this.n1 += moneyToAdd.getN1();
        this.n5 += moneyToAdd.getN5();
        this.n10 += moneyToAdd.getN10();
        this.n20 += moneyToAdd.getN20();
        this.n50 += moneyToAdd.getN50();
        this.n100 += moneyToAdd.getN100();
        this.n200 += moneyToAdd.getN200();
        this.n500 += moneyToAdd.getN500();
        this.n1k += moneyToAdd.getN1k();
    }
}


