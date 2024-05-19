public class Bond extends Product{

    private double interest;
    private String issuedEntity;

    public double getInterest() {
        return interest;
    }

    public String getIssuedEntity() {
        return issuedEntity;
    }

    public Bond(String name, Long price, String ticker, Long quantity, int year, double interest, String issuedEntity,RISK risk){
        super(name,price,ticker,quantity,risk);
        this.interest = interest;
        this.issuedEntity = issuedEntity;
    }

    public void payInterest(){
        System.out.println("============================== 알림 ==============================");
        System.out.println("채권명: "+getName()+"연 이자가("+getInterest()+") 지정계좌로 입금되었습니다");
        System.out.println("============================================================\n");
    }
}
