
import java.util.*;
 
 
class Farmer {
    String farmerName;
    String village;

    Farmer(String farmerName, String village) {
        this.farmerName = farmerName;
        this.village = village;
    }

    void displayFarmer() {
        System.out.println("Farmer Name : " + farmerName);
        System.out.println("Village     : " + village);
    }
}


class FarmerSeller extends Farmer {
    int farmerId;

    FarmerSeller(int farmerId, String farmerName, String village) {
        super(farmerName, village);
        this.farmerId = farmerId;
    }

    void displaySeller() {
        System.out.println("Farmer ID   : " + farmerId);
        displayFarmer();
    }
}

public class FarmerMarketTracker {

    
    static String[] products = {
        "Rice",
        "Wheat",
        "Tomato",
        "Potato",
        "Onion"
    };

    static double[] prices = {
        50.0,
        45.0,
        30.0,
        35.0,
        40.0
    };

    static int[] quantities = {
        100,
        80,
        150,
        120,
        90
    };

    
    static ArrayList<String> salesRecords = new ArrayList<>();

    static void displayProducts() {

        System.out.println("\n========== AVAILABLE PRODUCTS ==========");

        for (int i = 0; i < products.length; i++) {
            System.out.println(
                (i + 1) + ". " +
                products[i] +
                " - Rs." +
                prices[i] +
                " per kg - Available: " +
                quantities[i] +
                " kg"
            );
        }
    }

    
    static double calculateSales(double price, int quantity) {

       
        return price * quantity;
    }

    
    static void sellProduct(int productNumber, int quantity) {

        
        if (productNumber < 1 || productNumber > products.length) {
            System.out.println("Invalid product number!");
            return;
        }

        int index = productNumber - 1;

        
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        if (quantity > quantities[index]) {
            System.out.println("Not enough stock available!");
            return;
        }

        double total = calculateSales(prices[index], quantity);

        quantities[index] -= quantity;

        String record =
            products[index] +
            " | Quantity: " +
            quantity +
            " kg | Total: Rs." +
            total;

        salesRecords.add(record);

        System.out.println("\nSale Successful!");
        System.out.println("Product  : " + products[index]);
        System.out.println("Quantity  : " + quantity + " kg");
        System.out.println("Total     : Rs." + total);
    }

    
    static void displaySales() {

        System.out.println("\n========== SALES RECORDS ==========");

        if (salesRecords.isEmpty()) {
            System.out.println("No sales recorded.");
        } else {

            
            for (String record : salesRecords) {
                System.out.println(record);
            }
        }
    }

    
    static void analyzeSale(String saleData) {

        System.out.println("\n========== STRING TOKENIZER ==========");

        StringTokenizer tokenizer =
            new StringTokenizer(saleData, "|");

        while (tokenizer.hasMoreTokens()) {
            System.out.println(
                tokenizer.nextToken().trim()
            );
        }
    }

    
    static void inventoryValue() {

        double totalValue = 0;

        for (int i = 0; i < products.length; i++) {

            totalValue += prices[i] * quantities[i];
        }

        System.out.println(
            "\nTotal Inventory Value = Rs." +
            totalValue
        );
    }

    
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);

        
        int choice;
        int productNumber;
        int quantity;


        
        FarmerSeller farmer =
            new FarmerSeller(
                101,
                "JHON SNOW",
                "Warangal"
            );

        System.out.println("==============================================");
        System.out.println("     FARMER MARKET PLACE & SALES TRACKER");
        System.out.println("==============================================");

        farmer.displaySeller();

        
        do {

            System.out.println("\n============== MENU ==============");
            System.out.println("1. Display Products");
            System.out.println("2. Sell Product");
            System.out.println("3. Display Sales Records");
            System.out.println("4. Display Inventory Value");
            System.out.println("5. Analyze Sale using StringTokenizer");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            
            switch (choice) {

                case 1:

                    displayProducts();

                    break;

                case 2:

                    displayProducts();

                    System.out.print(
                        "\nEnter product number: "
                    );

                    productNumber = sc.nextInt();

                    System.out.print(
                        "Enter quantity in kg: "
                    );

                    quantity = sc.nextInt();

                    sellProduct(
                        productNumber,
                        quantity
                    );

                    break;

                case 3:

                    displaySales();

                    break;

                case 4:

                    inventoryValue();

                    break;

                case 5:

                    if (salesRecords.isEmpty()) {

                        System.out.println(
                            "No sales available for analysis."
                        );

                    } else {

                        String lastSale =
                            salesRecords.get(
                                salesRecords.size() - 1
                            );

                        analyzeSale(lastSale);
                    }

                    break;

                case 6:

                    System.out.println(
                        "Thank you for using Farmer Market Tracker!"
                    );

                    break;

                default:

                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }

        } while (choice != 6);

        sc.close();
    }
}
