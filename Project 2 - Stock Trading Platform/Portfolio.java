import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks; // Stock symbol and quantity
    private List<String> transactionHistory; // List to store transaction history

    public Portfolio() {
        stocks = new HashMap<>();
        transactionHistory = new ArrayList<>();
    }

    public void buyStock(String symbol, int quantity) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
        transactionHistory.add("Bought " + quantity + " shares of " + symbol);
    }

    public void sellStock(String symbol, int quantity) {
        if (stocks.containsKey(symbol) && stocks.get(symbol) >= quantity) {
            stocks.put(symbol, stocks.get(symbol) - quantity);
            transactionHistory.add("Sold " + quantity + " shares of " + symbol);
            if (stocks.get(symbol) == 0) {
                stocks.remove(symbol);
            }
        } else {
            System.out.println("Not enough stocks to sell.");
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public double calculateTotalValue(Market market) {
        double totalValue = 0.0;
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            Stock stock = market.getStock(entry.getKey());
            if (stock != null) {
                totalValue += stock.getPrice() * entry.getValue();
            }
        }
        return totalValue;
    }
} 