import java.util.HashMap;

public class Account {

    private Long money = 0l;

    private Long dept = 0l;

    private HashMap<String, Long> myProduct = new HashMap<>();
    private ProductController productController = ProductController.getProductController();

    private Account() {}
    private static final Account account = new Account();

    public static Account getAccount(){
        return account;
    }

    public void deposit(Long money){
        if(!dept.equals(0l)){
            Long tmp = Math.min(dept,money);
            dept -= tmp;
            money -= tmp;
        }
        this.money += money;
    }

    public Long getMoney() {
        return money;
    }

    public Long getDept() { return dept; }
    public boolean withDraw(Long money){
        if(this.money < money){
            System.out.println("잔액이 부족합니다. 현재 잔액:"+this.money+"(원)");
            return false;
        }

        this.money -= money;
        return true;
    }

    public void checkMoney(){
        System.out.println("현재 잔액: "+this.money+"(원)");
    }

    public void append(String name,Long amount){
        if(myProduct.containsKey(name)){
            System.out.println(name+" "+amount+" 주 매수완료.");
            myProduct.put(name,myProduct.get(name) + amount);
        }else{
            myProduct.put(name,amount);
            System.out.println(name+" "+amount+" 주 매수완료.");
        }
    }

    public void sell(String name,Long amount){
        if(!myProduct.containsKey(name)){
            System.out.println("해당 주식을 보유하고 계시지 않습니다.");
            return;
        }else if(myProduct.get(name) <  amount){
            System.out.println("보유 주식 수 이상을 매도할 수 없습니다.");
        }else{
            myProduct.put(name,myProduct.get(name) - amount);

            Long sellMoney = productController.getProduct(name).getPrice() * amount;

            if(!dept.equals(0l)){
                Long tmp = Math.min(dept,sellMoney);
                dept -= tmp;
                sellMoney -= tmp;
            }

            money += sellMoney;

            if(myProduct.get(name) == 0l){
                myProduct.remove(name);
            }
            System.out.println(name+" "+amount+" 주 매도완료.");
        }
    }

    public void showAccount(){
        System.out.println("========================= 계좌 정보 =========================");
        if(myProduct.isEmpty()){
            System.out.println("\n\n\n                          (비어있음)\n\n\n");
        }else{
            for (String s : myProduct.keySet()) {
                System.out.println("                    ▶ "+s+" :"+myProduct.get(s)+" 주\n");
            }
        }
        System.out.println("============================================================\n");
        Long total_value = calcTotal();
        System.out.println("                     총 자산: "+total_value+" (원)");
    }

    public void product_fee(){
        HashMap<String, HashMap<String,Long>> payLog = new HashMap<>();

        payLog.put("펀드",new HashMap<>());
        payLog.put("채권",new HashMap<>());
        payLog.put("우선주",new HashMap<>());

        Long totalCommission = 0l;

        Long nowPrice;
        double commission_percent;
        Long commission;
        Long count;
        String kind = "";

        for(String name: myProduct.keySet()){

            count = myProduct.get(name);

            if(name.endsWith("펀드")){
                kind = "펀드";
                Fund fund = (Fund) productController.getProduct(name);
                nowPrice = fund.getPrice();
                commission_percent = fund.getCommission();
            }else if(name.endsWith("년")){
                kind = "채권";
                Bond bond = (Bond) productController.getProduct(name);
                nowPrice = bond.getPrice();
                commission_percent = bond.getInterest();
            }else if(name.endsWith("(우)")){
                kind = "우선주";
                PreferredStock preferredStock = (PreferredStock) productController.getProduct(name);
                nowPrice = preferredStock.getPrice();
                commission_percent = preferredStock.getDividendRate();
            }else{
                continue;
            }

            commission = (long) (nowPrice * count * commission_percent);
            payLog.get(kind).put(name,commission);
        }

        for(String key : payLog.keySet()){
            HashMap<String,Long> map = payLog.get(key);
            if(!map.isEmpty()){

                String title = "";

                if(key.equals("펀드")){
                    title = "수수료";
                }else if (key.equals("채권")){
                    title = "이자";
                }else{
                    title = "배당금";
                }

                String head = String.format("\n====================== %s 안내 ======================\n",title);

                System.out.printf(head);

                for(String name : map.keySet()){
                    System.out.printf("        %s명: %s     금액: %,d원\n",key, name, map.get(name));
                    totalCommission += map.get(name);
                }
                System.out.println("=".repeat(head.length())+"\n\n");

                if(key.equals("펀드")) {  //펀드면 돈 출금
                    if(!withDraw(totalCommission)){
                        System.out.printf("\n===================== 경고 안내 =====================\n");
                        System.out.println("수수료를 납부할 잔액이 부족합니다.\n 요금은 빚으로 계산되며 잔액에 돈이 입금되는대로 징수해 갑니다.");
                        System.out.printf("\n====================================================\n");
                    }
                }else{      // 그 이외에는 돈 넣는다.
                    deposit(totalCommission);
                }
            }
        }
    }

    public Long calcTotal(){
        Long total = 0l;
        HashMap product_lst = productController.getProduct_lst();
        for(Object p : product_lst.keySet()){
            String key = p.toString();
            if(myProduct.containsKey(key)){
                Long price = productController.getProduct(key).getPrice();
                Long cnt = myProduct.get(key);
                total += price * cnt;
            }
        }
        return total;
    }

    public double calcProfit(Long firstMoney){
        Long nowMoney = money + calcTotal();

        System.out.println("\n---------------------");
        System.out.println("------- 수익률 -------");
        System.out.println("---------------------\n");
        double ratio = (double) nowMoney/firstMoney;
        Long percent = (long) (ratio*100);

        System.out.print("현재 자산:"+nowMoney+"");

        if(ratio < 1){
            System.out.println("    "+(100 - percent)+"% ▼");
        } else if (ratio > 1) {
            System.out.println("    "+(percent - 100)+"% ▲");
        }else {
            System.out.println("    보합 -");
        }

        System.out.println();

        return ratio;
    }
}
