
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private static String buyerOrder;
    private static int buyerQuantity;
    private static double buyerMoney;
    private  static double indivAmount;
    private static double fruitBowl;
    private static ArrayList<Slot> newSlot;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean program = true;
         int vending, menu, test, main;
        ArrayList<Slot> regularVendingSlots = new ArrayList<>();
        ArrayList<Slot> specialVendingSlots = new ArrayList<>();
        RegularVendingMachine RVM = new RegularVendingMachine(regularVendingSlots);
        SpecialVendingMachine SVM = new SpecialVendingMachine(specialVendingSlots);

        while(program)
        {
            System.out.println("[1] Regular Vending Machine");
            System.out.println("[2] Special Vending Machine");
            System.out.println("[3] Exit the program");
            System.out.print("Select an Option: ");
            vending = sc.nextInt();

            if(vending == 1)
            {
                buyerMoney = 0;
                System.out.println("REGULAR VENDING MACHINE");
                boolean regularMenu = true;
                while(regularMenu)
                {
                    System.out.println("[1] Create a Vending Machine");
                    System.out.println("[2] Test a Vending Machine");
                    System.out.println("[3] Exit to Main Menu");
                    System.out.print("Select Option: ");
                    menu = sc.nextInt();
                    switch(menu)
                    {
                        case 1:
                            System.out.println();
                            System.out.println("CREATE VENDING MACHINE");
                            System.out.println();
                            RVM.createRegularVendingMachine();
                            RVM.displayItems();
                            RVM.moneyInVendingMachine();
                            break;
                        case 2:
                            System.out.println("TEST THE VENDING MACHINE");
                            System.out.println("[1] Test the Vending Machine");
                            System.out.println("[2] Test Maintenance");
                            System.out.print("Select Option: ");
                            test = sc.nextInt();
                            if(test == 1)
                            {
                                System.out.println();
                                System.out.println("TEST VENDING MACHINE");
                                System.out.println();
                                System.out.println("Notice: Will Test the following");
                                System.out.println("- Display Items");
                                System.out.println("- Insert Money");
                                System.out.println("- Buy an Item");
                                System.out.println("- Dispense Item");
                                System.out.println("- Get Change");
                                if(RVM.isSlotEmpty())
                                {
                                    RVM.createRegularVendingMachine();
                                    RVM.displayItems();
                                    RVM.moneyInVendingMachine();
                                }
                                buyerMoney = RVM.buyerPayment();
                                RVM.displayItems();
                                System.out.print("Choose your Order: ");
                                buyerOrder = sc.next();
                                System.out.print("Choose Quantity: ");
                                buyerQuantity = sc.nextInt();
                                RVM.buyersOrderSlip(buyerOrder, buyerQuantity);
                                RVM.giveChange(buyerOrder, buyerQuantity, buyerMoney);
                                System.out.println();
                            }
                            else if(test == 2)
                            {

                                System.out.println();
                                System.out.println("MAINTENANCE");
                                System.out.println();
                                boolean maintenanceMenu = true;
                                while(maintenanceMenu)
                                {
                                    System.out.println(" Notice: Will Test the following");
                                    System.out.println("[1] Restock Items");
                                    System.out.println("[2] Set New Prices");
                                    System.out.println("[3] Collect Payment");
                                    System.out.println("[4] Replenish Money");
                                    System.out.println("[5] Print Transaction");
                                    System.out.println("[6] Exit to main menu");
                                    System.out.print("Select Option: ");
                                    main = sc.nextInt();
                                    switch (main) {
                                        case 1:
                                            System.out.println();
                                            System.out.println("RESTOCKING ITEMS");
                                            RVM.restockItemsInDriver(buyerQuantity);
                                            break;
                                        case 2:
                                            System.out.println();
                                            System.out.println("SET NEW PRICES");
                                            RVM.setNewPrice();
                                            RVM.displayItems();
                                            break;
                                        case 3:
                                            System.out.println();
                                            System.out.println("COLLECT PAYMENT");
                                            RVM.displayTotal(buyerOrder, buyerQuantity);
                                            break;
                                        case 4:
                                            System.out.println();
                                            System.out.println("REPLENISH MONEY");
                                            RVM.replenishMoney();
                                            break;
                                        case 5:
                                            System.out.println();
                                            System.out.println(" PRINT TRANSACTION");
                                            System.out.println();
                                            RVM.displayTotal(buyerOrder, buyerQuantity);
                                            System.out.println();
                                            RVM.displayInventory();
                                            break;
                                        case 6:
                                            System.out.println();
                                            System.out.println("BACK TO MENU");
                                            maintenanceMenu = false;
                                            break;

                                        default:
                                            System.out.println("ERROR: Invalid Input");
                                            break;
                                    }
                                }
                            }
                            else
                                System.out.println("Error: Invalid Input");
                            break;
                        case 3:
                            System.out.println("Exit Regular Vending Machine menu");
                            regularMenu = false;
                            break;
                        default:
                            System.out.println("Error: Invalid Input");
                            break;
                    }
                }
            }
            else if(vending == 2)
            {
                menu = 0;
                buyerMoney = 0;
                System.out.println("SPECIAL VENDING MACHINE");
                boolean specialMenu = true;
                while(specialMenu)
                {
                    System.out.println("[1] Create a Vending Machine");
                    System.out.println("[2] Test a Vending Machine");
                    System.out.println("[3] Exit the Program");
                    System.out.print("Select Option: ");
                    menu = sc.nextInt();
                    switch(menu)
                    {
                        case 1:
                            System.out.println();
                            System.out.println("CREATE VENDING MACHINE");
                            System.out.println();
                            SVM.createSpecialVendingMachine();
                            SVM.displayItems();
                            SVM.moneyInVendingMachine();
                            break;
                        case 2:
                            System.out.println();
                            System.out.println("TEST THE VENDING MACHINE");
                            System.out.println();
                            System.out.println("[1] Test the Vending Machine");
                            System.out.println("[2] Test Maintenance");
                            System.out.print("Select Option: ");
                            test = sc.nextInt();
                            if(test == 1)
                            {
                                System.out.println();
                                System.out.println("TEST VENDING MACHINE");
                                System.out.println();
                                System.out.println("Notice: Will Test the following");
                                System.out.println("- Display Items");
                                System.out.println("- Insert Money");
                                System.out.println("- Buy an Item");
                                System.out.println("- Dispense Item");
                                System.out.println("- Get Change");
                                if(SVM.isSlotEmpty())
                                {
                                    SVM.createSpecialVendingMachine();
                                    SVM.displayItems();
                                    SVM.moneyInVendingMachine();
                                }

                                buyerMoney = SVM.buyerPayment();
                                System.out.println();
                                System.out.println("WHAT TO PURCHASE");
                                System.out.println();
                                System.out.println("[1] Buy Individual Items");
                                System.out.println("[2] Buy a Fruit Bowl");
                                System.out.print("Select Option: ");
                                int special = sc.nextInt();
                                if(special == 1)
                                {
                                    SVM.displayItems();
                                    indivAmount = SVM.buyingIndividualItems();
                                    SVM.giveChangeSpecial(buyerMoney,indivAmount);
                                }
                                else if(special == 2)
                                {
                                    SVM.displayItems();
                                    fruitBowl = SVM.createFruitBowl();
                                    SVM.giveChangeSpecial(buyerMoney,fruitBowl);
                                }
                                else
                                    System.out.println("Error: Invalid Input");
                            }
                            else if(test == 2)
                            {
                                System.out.println();
                                System.out.println("MAINTENANCE");
                                System.out.println();
                                boolean specialMaintenanceMenu = true;
                                while(specialMaintenanceMenu)
                                {
                                    System.out.println(" Notice: Will Test the following");
                                    System.out.println("[1] Restock Items");
                                    System.out.println("[2] Set New Prices");
                                    System.out.println("[3] Collect Payment");
                                    System.out.println("[4] Replenish Money");
                                    System.out.println("[5] Print Transaction");
                                    System.out.println("[6] Exit to main menu");
                                    System.out.print("Select Option: ");
                                    main = sc.nextInt();
                                    switch (main) {
                                        case 1:
                                            System.out.println();
                                            System.out.println("RESTOCKING ITEMS");
                                            SVM.restockItemsInDriver(buyerQuantity);
                                            break;
                                        case 2:
                                            System.out.println();
                                            System.out.println("SET NEW PRICES");
                                            SVM.setNewPrice();
                                            SVM.displayItems();
                                            break;
                                        case 3:
                                            System.out.println();
                                            System.out.println("COLLECT PAYMENT");
                                            SVM.displayTotal();
                                            break;
                                        case 4:
                                            System.out.println();
                                            System.out.println("REPLENISH MONEY");
                                            SVM.replenishMoney();
                                            break;
                                        case 5:
                                            System.out.println();
                                            System.out.println(" PRINT TRANSACTION");
                                            SVM.displayTotal();
                                            SVM.displayInventory();
                                            break;
                                        case 6:
                                            System.out.println();
                                            System.out.println("BACK TO MENU");
                                            specialMaintenanceMenu = false;
                                            break;
                                        default:
                                            System.out.println("ERROR: Invalid Input");
                                            break;
                                    }
                                }
                            }
                            else
                                System.out.println("Error: Invalid Input");
                            break;
                        case 3:
                            System.out.println("Exit Special Vending Machine");
                            specialMenu = false;
                            break;
                        default:
                            System.out.println("Error: Invalid Input");
                            break;
                    }
                }
            }
            else if (vending == 3)
            {
                program = false;
            } else
                System.out.println("Error: Invalid Input");
        }
    }
}