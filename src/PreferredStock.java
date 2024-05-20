public class PreferredStock extends Stock{

    private double dividendRate;

    public PreferredStock(String name,Long price,String ticker,Long quantity,String CEO,RISK risk,double dividendRate){
        super(name,price,ticker,quantity,CEO,risk);
        this.dividendRate = dividendRate;
    }

    public void payDividend(){
        System.out.println("============================== 알림 ==============================");
        System.out.println("티커명:"+getTicker()+"(우) 분기 배당금이 지정계좌로 입금되었습니다.");
        System.out.println("============================================================\n");
    }
}
