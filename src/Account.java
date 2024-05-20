import java.util.HashMap;

public class Account {

    private Long money = 0l;

    private HashMap<String, Long> myProduct = new HashMap<>();
    private ProductController productController = ProductController.getProductController();

    private Account() {}
    private static final Account account = new Account();

    public static Account getAccount(){
        return account;
    }

    public void deposit(Long money){
        this.money += money;
    }

    public Long getMoney() {
        return money;
    }
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

            money += productController.getProduct(name).getPrice() * amount;

            if(myProduct.get(name) == 0l){
                myProduct.remove(name);
            }
            System.out.println(name+" "+amount+" 주 매도완료.");
        }
    }

    public void showAccount(){
        System.out.println("============================= 계좌 정보 =============================");
        if(myProduct.isEmpty()){
            System.out.println("\n\n\n                비어있음\n\n\n");
        }else{
            for (String s : myProduct.keySet()) {
                System.out.println("        "+s+" :"+myProduct.get(s)+" 주\n");
            }
        }
        System.out.println("============================================================\n");
        Long total_value = calcTotal();
        System.out.println("                  총 자산: "+total_value+" (원)");
    }

    public Long calcTotal(){
        Long total = 0l;
        HashMap product_lst = productController.getProduct_lst();
        for(Object p : product_lst.keySet()){
            String key = product_lst.get(p).toString();
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
        double ratio = nowMoney/firstMoney;
        Long percent = (long) ratio*100;

        if(ratio < 1){
            System.out.println(100 - percent+"% ▼");
        } else if (ratio > 1) {
            System.out.println(percent - 100+"% ▲");
        }else {
            System.out.println("보합 -");
        }
        return ratio;
    }
}
