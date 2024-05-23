public class Fund extends Product{

    private String management;

    private Double commission;

    public String getManagement() {
        return management;
    }

    public Double getCommission() {
        return commission;
    }

    public Fund(String name, Long price, Long quantity, String management, Double commission,RISK risk){
        super(name,price,quantity,risk);
        this.management = management;
        this.commission = commission;
    }

    public void restructuring(){

        String previous = "";
        String now = "";

        String high = RISK.HIGH.name();
        String mid = RISK.MID.name();
        String low = RISK.LOW.name();

        if(getRisk().equals(RISK.HIGH)){
            previous = high;
            now = mid;

            setRisk(RISK.MID);

        } else if (getRisk().equals(RISK.MID)) {
            previous = mid;
            now = low;

            setRisk(RISK.LOW);
        } else if (getRisk().equals(RISK.LOW)){
            previous = low;
            now = high;

            setRisk(RISK.HIGH);
        }
        alert("고객님들의 의견을 적극 수용하여 "+getName()+"의 종목 구성이 변경됐음을 알립니다.\n"+"자세한 사항은 "+getManagement()+"사 홈페이지의 공시를 확인해주세요.\n"+
                "(펀드 신용등급 변경:"+previous+" → "+now+")");
    }

    @Override
    public String toString(){
        String info = "======================== 펀드 정보 ========================\n"+
                "                   펀드명: "+getName()+"\n"+
                "                   주가: "+getPrice()+"\n"+
                "                   발행주식 수: "+getQuantity()+"\n"+
                "                   평균 거래량: "+getTradingVolume()+"\n"+
                "                   운용사: "+getManagement()+"\n"+
                "                   신용도: "+getRisk()+"\n" +
                "                   연 수수료: "+(int) (getCommission()*100)+"%\n"+
                "=========================================================\n";
        return info;
    }

    public void alert(String message){
        System.out.println("================================== 알림 ==================================");
        System.out.println(message);
        System.out.println("=========================================================================\n");
    }
}
