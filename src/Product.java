import java.util.Random;

public class Product {
    static Random random = new Random();
    private Long quantity; //발행 수

    private Long max_trade;   // 거래량

    private Long min_trade;

    private RISK risk;

    private String name;    //상품명
    private Long price;     //상품 가격
    private Long tradingVolume;     //당일 거래량
    public Product(String name,Long price,Long quantity,RISK risk){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.max_trade = quantity/2;    // 당일 거래되는 거래량 상한  (50%)
        this.min_trade = quantity/50;   // 당일 거래되는 거래량 하한  (2%)
        this.tradingVolume = Math.max(random.nextLong(max_trade+1),min_trade); // 당일 거래량은 총 상품 수의 2% ~ 50% 사이의 랜덤 값을 가짐.
        this.risk = risk;
    }

    void setPrice(Long price) {
        this.price = price;
    }
    public RISK getRisk() {
        return risk;
    }

    public void setRisk(RISK risk) {
        this.risk = risk;
    }

    public String getName() {
        return name;
    }
    public Long getPrice() {
        return price;
    }
    public Long getTradingVolume() {
        return tradingVolume;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void ceil(){
        setPrice((long) (getPrice()*1.3));
    }

    public void floor(){
        setPrice((long) (getPrice()*0.7));
    }


}
