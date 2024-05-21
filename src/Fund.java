public class Fund extends Product{

    private String management;

    private Double commission;

    public String getManagement() {
        return management;
    }

    public Double getCommission() {
        return commission;
    }

    public Fund(String name, Long price, String ticker, Long quantity, String management, Double commission,RISK risk){
        super(name,price,ticker,quantity,risk);
        this.management = management;
        this.commission = commission;
    }

    public void restructuring(){
        alert(getTicker()+"의 종목 구성이 변경됐음을 알립니다.\n"+"자세한 사항은 "+getManagement()+"사 홈페이지의 공시를 확인해주세요.");
    }

    public void chargeCommision(){
        alert(getTicker()+"의 연 수수료("+getCommission()+")를 징수해가겠습니다.\n"+"정확한 금액은 "+getManagement()+"사 홈페이지의 공시를 확인해주세요.");
    }

    @Override
    public String toString(){
        String info = "======================== 펀드 정보 ========================\n"+
                "                   펀드명: "+getName()+"\n"+
                "                   티커명: "+getTicker()+"\n"+
                "                   주가: "+getPrice()+"\n"+
                "                   발행주식 수: "+getQuantity()+"\n"+
                "                   운용사: "+getManagement()+"\n"+
                "                   연 수수료: "+(int) (getCommission()*100)+"%\n"+
                "=========================================================\n";
        return info;
    }

    public void alert(String message){
        System.out.println("============================== 알림 ==============================");
        System.out.println(message);
        System.out.println("================================================================\n");
    }
}
