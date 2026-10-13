package Stock;

public class Stock {

    private String symbol;
    private double openPrice, max, min, closePrice;
    private long volume;

    public Stock(String symbol, double openPrice, double max, double min, double closePrice, long volume) {
        this.symbol = symbol;
        this.openPrice = openPrice;
        this.max = max;
        this.min = min;
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public long getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return symbol + " " + "close price = " + closePrice + " " + "volume = " + volume;
    }

}
