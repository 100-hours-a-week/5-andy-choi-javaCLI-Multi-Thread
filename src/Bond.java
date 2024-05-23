public class Bond extends Product{

    private double interest;
    private String issuedEntity;
    private int year;

    private String credit;

    public int getYear() {
        return year;
    }

    public String getCredit() {
        return credit;
    }


    public double getInterest() {
        return interest;
    }

    public String getIssuedEntity() {
        return issuedEntity;
    }

    public Bond(String name, Long price, Long quantity, int year, double interest, String issuedEntity,RISK risk){
        super(name,price,quantity,risk);
        this.year = year;
        this.interest = interest;
        this.issuedEntity = issuedEntity;

        if(risk == RISK.LOW){
            this.credit = "AAA";
        } else if (risk == RISK.MID) {
            this.credit = "BB+";
        }else{
            this.credit = "CCC-";
        }

    }

    @Override
    public String toString(){

        String info = "======================== 채권 정보 ========================\n"+
                "                   채권명: "+getName()+"\n"+
                "                   주가: "+getPrice()+"\n"+
                "                   발행 기관:"+getIssuedEntity()+"\n"+
                "                   발행채권 수: "+getQuantity()+"\n"+
                "                   평균 거래량: "+getTradingVolume()+"\n"+
                "                   만기: "+getYear()+"년\n"+
                "                   이자: "+(int) (getInterest()*100)+"%\n"+
                "                   신용 등급: " + getCredit() + "\n"+
                "=========================================================\n";

        return info;
    }
}
