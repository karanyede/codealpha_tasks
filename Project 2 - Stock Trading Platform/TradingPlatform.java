import java.util.Scanner;

public class TradingPlatform {
    private Market market;
    private Portfolio portfolio;

    public TradingPlatform() {
        market = new Market();
        portfolio = new Portfolio();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        
        // Brief introduction about the project
        System.out.println("Welcome to the Simulated Stock Trading Platform!");
        System.out.println("This software allows you to buy and sell stocks, view market data, and track your portfolio performance.");
        System.out.println("You can enter commands to interact with the platform. Available commands are: market, buy, sell, view, and exit.");
        System.out.println("Let's get started!\n");

        String command;

        while (true) {
            System.out.println("Enter command (market/buy/sell/view/exit): ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "market":
                    market.displayMarket();
                    break;
                case "buy":
                    buyStock(scanner);
                    break;
                case "sell":
                    sellStock(scanner);
                    break;
                case "view":
                    portfolio.displayPortfolio();
                    double totalValue = portfolio.calculateTotalValue(market);
                    System.out.println("Total Portfolio Value: $" + totalValue);
                    break;
                case "exit":
                    if (confirmExit(scanner)) {
                        System.out.println("Exiting the trading platform.");
                        scanner.close();
                        return;
                    }
                    break;
                default:
                    // Only show invalid command if the command is not recognized
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void buyStock(Scanner scanner) {
        System.out.println("Enter stock name and quantity: ");
        String buySymbol = scanner.next();
        int buyQuantity = getValidQuantity(scanner);
        scanner.nextLine();
        if (buyQuantity > 0) {
            portfolio.buyStock(buySymbol, buyQuantity);
            System.out.println("Bought " + buyQuantity + " shares of " + buySymbol);
        }
    }

    private void sellStock(Scanner scanner) {
        System.out.println("Enter stock name and quantity: ");
        String sellSymbol = scanner.next();
        int sellQuantity = getValidQuantity(scanner);
        scanner.nextLine();
        if (sellQuantity > 0) {
            portfolio.sellStock(sellSymbol, sellQuantity);
            System.out.println("Sold " + sellQuantity + " shares of " + sellSymbol);
        }
    }

    private int getValidQuantity(Scanner scanner) {
        while (true) {
            try {
                int quantity = Integer.parseInt(scanner.next());
                if (quantity > 0) {
                    return quantity;
                } else {
                    System.out.println("Quantity must be a positive integer. Try again:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer:");
            }
        }
    }

    private boolean confirmExit(Scanner scanner) {
        System.out.println("Are you sure you want to exit? (yes/no)");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        platform.start();
    }
} 