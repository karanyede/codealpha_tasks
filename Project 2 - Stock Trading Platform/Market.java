import java.util.HashMap;
import java.util.Map;

public class Market {
    private Map<String, Stock> stocks;

    public Market() {
        stocks = new HashMap<>();
        // Adding some initial stocks
        stocks.put("APPLE", new Stock("APPLE", 150.00));
        stocks.put("GOOGLE", new Stock("GOOGLE", 2800.00));
        stocks.put("AMAZON", new Stock("AMAZON", 3400.00));
        stocks.put("SBI", new Stock("SBI", 4000.00));
        stocks.put("RBI", new Stock("RBI", 4300.00));
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void updateStockPrice(String symbol, double newPrice) {
        if (stocks.containsKey(symbol)) {
            stocks.get(symbol).setPrice(newPrice);
        }
    }

    public void displayMarket() {
        System.out.println("Market Data:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock.getSymbol() + ": $" + stock.getPrice());
        }
    }
} 