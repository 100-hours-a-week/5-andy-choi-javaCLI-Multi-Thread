public class PreferredStock extends Stock{

    private double dividendRate;

    public PreferredStock(String name,Long price,Long quantity,String CEO,RISK risk,double dividendRate){
        super(name,price,quantity,CEO,risk);
        this.dividendRate = dividendRate;
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
