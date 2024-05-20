import java.util.HashMap;
import java.util.Random;

public class ProductController {

    private ProductController(){}

    private static final ProductController productController = new ProductController();

    public static ProductController getProductController(){
        return productController;
    }

    static Random random = new Random();

    private String [] company = {"현대","대우","삼성","SK","LG","KT","롯데","하나","한국","POSCO"};

    private String [] field = {"전자","전기","조선","금융","식품","전력","물산","네트웍스","모바일","모비스","자동차"};
    private String [] group = {"주식 회사","그룹","컴퍼니","보험","기업","글로벌","솔루션"};
    private String [] CEO ={"ELON","MIKE","MICHEAL","JOHN","STEVE","LEE","JOSH","KIM"};

    // 펀드
    private String [] theme = {"KODEX","NASDAQ","S&P500","JAPAN","글로벌","INDEX","REVERSE","고위험 고수익","GROWTH"};
    private String [] manageCompany = {"INVESCO","KODEX","미래에셋자산운용","Vanguard","JP.MORGAN"};

    // 채권
    private String [] country = {"미국","한국","일본","영국","북한","중국"};
    private String [] year = {"5","10","30"};


    private Product [] products = {};

    private HashMap<String,Product> product_lst = new HashMap();

    public HashMap getProduct_lst() {
        return product_lst;
    }

    public Product getProduct(String key){
        return product_lst.get(key);
    }

    public void createStock(RISK risk){
        String stockName;
        while(true){
            int company_idx = random.nextInt(0,company.length);
            int field_idx = random.nextInt(0,field.length);
            int group_idx = random.nextInt(0,group.length);
            int ceo_idx = random.nextInt(0,CEO.length);

            stockName = company[company_idx] + " " + field[field_idx] + " " + group[group_idx];
            if(!product_lst.containsKey(stockName)){
                Long price = random.nextLong(10_000,500_000);
                Long quantity = random.nextLong(10_000,100_000);

                Stock stock = new Stock(stockName,price,"ABCD",quantity,CEO[ceo_idx],risk);

                product_lst.put(stockName,stock);
                break;
            }
        }
    }

    public void createFund(){
        String fundName;
        while(true){
            int theme_idx = random.nextInt(0, theme.length);
            int field_idx = random.nextInt(0, field.length);
            int manage_idx = random.nextInt(0, manageCompany.length);

            RISK risk;

            if(theme_idx >= 5){
                risk = RISK.HIGH;
            }else if (theme_idx == 0){
                risk = RISK.LOW;
            }else{
                risk = RISK.MID;
            }

            fundName = theme[theme_idx] + " " + field[field_idx] + " 펀드";

            if(!product_lst.containsKey(fundName)){
                Long price = random.nextLong(10_000,200_000);
                Long quantity = random.nextLong(10_000,100_000);
                String management = manageCompany[manage_idx];
                Double commission = random.nextDouble(0.08,0.15);

                Fund fund = new Fund(fundName,price,"ABCD",quantity,management,commission,risk);

                product_lst.put(fundName,fund);
                break;
            }
        }
    }

    public void createBond(){
        String bondName;
        while(true){
            int country_idx = random.nextInt(0, country.length);
            int year_idx = random.nextInt(0, year.length);

            RISK risk;

            if(country_idx >= 4){
                risk = RISK.HIGH;
            }else if (country_idx == 0){
                risk = RISK.LOW;
            }else{
                risk = RISK.MID;
            }

            bondName = country[country_idx] + " 국채 " + year[year_idx] +"년";

            if(!product_lst.containsKey(bondName)){
                Long price = random.nextLong(10_000,300_000);
                Long quantity = random.nextLong(10_000,50_000);
                int yr = Integer.parseInt(year[year_idx]);
                double interest = random.nextDouble(0.05,0.1);
                String issuedEntity = country[country_idx];

                Bond bond = new Bond(bondName,price,"ABCD",quantity,yr,interest,issuedEntity,risk);

                product_lst.put(bondName,bond);
                break;
            }
        }
    }

    public void createPreferredStock(Stock stock){
        String preStockName = stock.getName()+" (우)";
        Long price = (long) (stock.getPrice() * 0.9);
        Long quantity = (long) (stock.getQuantity() * 0.5);
        String ceo = stock.getCEO();
        double dividendRate = random.nextDouble(0.08,0.2);
        RISK risk;

        if(stock.getRisk() == RISK.HIGH){
            risk =  RISK.MID;
        }else{
            risk = RISK.LOW;
        }

        PreferredStock preferredStock = new PreferredStock(preStockName,price,"ABCD",quantity,ceo,risk,dividendRate);
        product_lst.put(preStockName,preferredStock);
    }

    public void initProducts() {
        
        // 주식 - 안정적 3개, 중간 2개, 위험 2개
        // 펀드 - 랜덤 5개
        // 채권 - 안정 3개
        
        for (int i=0;i<3;i++){
            productController.createStock(RISK.LOW);
            productController.createBond();
        }

        for (int i=0;i<2;i++){
            productController.createStock(RISK.MID);
            productController.createStock(RISK.HIGH);
        }

        for (int i=0;i<5;i++){
            productController.createFund();
        }

        for (String key : product_lst.keySet()){
            Product product = product_lst.get(key);
            if(product instanceof Stock){
                productController.createPreferredStock((Stock) product);
                break;
            }
        }
    }

    public boolean checkAvailable(String name,Long amount){
        if(product_lst.containsKey(name) && product_lst.get(name).getQuantity() >= amount){
            return true;
        }
        if(!product_lst.containsKey(name)){
            System.out.println("주식명을 다시 한번 확인해주세요.");
            return false;
        }
        else{
            System.out.println("수량이 부족합니다. (잔여 수량:" + product_lst.get(name).getQuantity()+")");
        }

        return false;
    }
}
