package ui;

import java.util.Scanner;

public class BurgerTown {

    public static Scanner reader;
    public static double[] prices;
    public static int[] units;

    public static void main(String[] args) {

        initializeGlobals();
        menu();
    }

    /**
     * Description: This method is responsible for initializing global variables
     * pre: The Scanner reader must be declared
     * post: The Scanner reader is initialized
    */
    public static void initializeGlobals() {
        reader = new Scanner(System.in);
    }

    /**
     * Description: This method is responsible for displaying the menu to the user and showing the result messages for each functionality
     * pre: The Scanner reader must be initialized
     * pre: The prices array must be initialized
    */
    public static void menu() {

        System.out.println("Welcome to BurgerTown!");

        setQuantitySold();

        boolean exit = false;

        do {

            System.out.println("\nMain Menu:");
            System.out.println("1. Request prices ($) and quantities of each dish sold during the day\n" + 
            "2. Calculate the total quantity of dishes sold during the day\n" + 
            "3. Calculate the average price of the dishes sold during the day\n" + 
            "4. Calculate the total sales (money collected) during the day\n" + 
            "5. Check how many dishes have exceeded a minimum sales limit during the day\n" + 
            "6. Exit\n");
            System.out.println("\nEnter the option to execute:");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    requestData();
                    break;
                case 2:
                    System.out.println("\nThe total number of dishes sold during the day was: " + calculateTotalDishesSold());
                    break;
                case 3:
                    System.out.println("\nThe average price of the dishes sold during the day was: " + calculateAveragePrice());
                    break;
                case 4:
                    System.out.println("\nThe total sales (money collected) during the day were: " + calculateTotalSales());
                    break;
                case 5:
                    System.out.println("\nEnter the minimum sales limit to analyze:");
                    double limit = reader.nextDouble();
                    System.out.println("\nOut of " + prices.length + " references sold during the day, " + consultDishesAboveLimit(limit) + " exceeded the minimum sales limit of " + limit);
                    break;
                case 6:
                    System.out.println("\nThank you for using our services!");
                    exit = true;
                    reader.close();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (!exit);

    }

    /**
     * Description: This method asks the user for the number of different dishes sold during the day and initializes the arrays that store prices and quantities with that value
     * @param double prices
     * @param int units
     */
    public static void setQuantitySold() {
        System.out.println("\nEnter the number of different dishes sold during the day:");
        int dishes = reader.nextInt();

        prices = new double[dishes];
        units = new int[dishes];
    }

    /**
     * Description: This method requests the user to input the prices and quantities of each dish sold during the day
     * pre: The Scanner reader must be initialized
     * post: The prices and units arrays are filled with the values entered by the user
     */
    public static void requestData() {
        for (int i = 0; i < prices.length; i++) {
            System.out.println("Enter the price of food plate " + (i + 1) + ":");
            prices[i] = reader.nextDouble();
            System.out.println("Enter the number of units sold of food plates " + (i + 1) + ":");
            units[i] = reader.nextInt();
        }
    }

    /**
     * Description: Calculates the total number of dishes sold by summing all units
     * pre: The prices and units arrays must be initialized
     * @return The total number of dishes sold
     */
    public static int calculateTotalDishesSold() {
        int totalDishes = 0;
        for (int unitsSold : units) {
            totalDishes += unitsSold;
        }
        return totalDishes;
    }

    /**
     * Description: Calculates the average price of the dishes sold
     * pre: The prices and units arrays must be initialized
     * @return The average price of the dishes sold
     */
    public static double calculateAveragePrice() {
        double totalSales = calculateTotalSales();
        int totalDishes = calculateTotalDishesSold();
        if (totalDishes == 0) {
            return 0;
        }
        return totalSales / totalDishes;
    }

    /**
     * Description: Calculates the total sales (money collected) by summing the price times the quantity sold of each dish
     * pre: The prices and units arrays must be initialized
     * @return The total sales of the day
     */
    public static double calculateTotalSales() {
        double totalSales = 0;
        for (int i = 0; i < prices.length; i++) {
            totalSales += prices[i] * units[i];
        }
        return totalSales;
    }

    /**
     * Description: Checks how many dishes sold more than a minimum limit
     * pre: The prices and units arrays must be initialized
     * @param limit The minimum sales limit
     * @return The number of dishes that exceeded the limit
     */
    public static int consultDishesAboveLimit(double limit) {
        int dishesAboveLimit = 0;
        for (int i = 0; i < prices.length; i++) {
            double totalSalesDish = prices[i] * units[i];
            if (totalSalesDish > limit) {
                dishesAboveLimit++;
            }
        }
        return dishesAboveLimit;
    }
}