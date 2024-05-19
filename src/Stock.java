public class Stock extends Product{

    private String CEO;

    private int per;

    private int revenue; // 매출액 (기본 단위 = 억) ex) revenue = 10 => 10억

    private String unit = "억"; // revenue의 기본 단위 (ex. 억, 조)

    public Stock(String name,Long price,String ticker,Long quantity,String CEO,RISK risk){
        super(name,price,ticker,quantity,risk);
        this.CEO = CEO;
        this.per = random.nextInt(1,300); // per은 1 ~ 300 사이의 값을 가짐.
        this.revenue = random.nextInt(10,90_000_000);
        if(this.revenue >= 10_000){
            unit = "조";
            this.revenue /= 10_000; // 소수점 단위는 생략
        }
    }

    @Override
    public String toString(){
        String info = "============================== 주식 정보 ==============================\n"+
                "주식명: "+getName()+"\n"+
                "티커명: "+getTicker()+"\n"+
                "매출액:"+getRevenue()+getUnit()+"\n"+
                "주가: "+getPrice()+"\n"+
                "발행주식 수: "+getQuantity()+"\n"+
                "CEO: "+getCEO()+"\n"+
                "PER: "+getPer()+"\n"+
                "============================================================\n\n";

        return info;
    }

    public void floor(){
        setPrice((long) (getPrice()*0.7));
        alert("금일 "+getTicker()+" 종목의 거래를 정지합니다.","하한가 (-30%)");
        haltTrade();
    }

    public void ceil(){
        setPrice((long) (getPrice()*1.3));
        alert("금일 "+getTicker()+" 종목의 거래를 정지합니다.","상한가 (+30%)");
        haltTrade();
    }

    public void delisting(){
        alert("티커명: "+getTicker()+"의 거래지원 종료를 안내드립니다.","상장폐지");
    }

    public void alert(String message,String reason){
        System.out.println("============================== 알림 ==============================");
        System.out.println(message);
        System.out.println("사유: "+reason);
        System.out.println("============================================================\n");
    }

    public String getCEO() {
        return CEO;
    }

    public int getPer() {
        return per;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getUnit() {
        return unit;
    }
}
