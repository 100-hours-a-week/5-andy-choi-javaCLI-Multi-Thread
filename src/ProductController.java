import java.util.HashMap;
import java.util.Random;

public class ProductController {
    static Random random = new Random();

    private String [] company = {"현대","대우","삼성","SK","LG","KT","롯데","하나","한국","POSCO"};

    private String [] field = {"전자","전기","조선","금융","식품","전력","물산","네트웍스","모바일","모비스","자동차"};
    private String [] group = {"주식 회사","그룹","컴퍼니","보험","기업","글로벌","솔루션"};
    private String [] CEO ={"ELON","MIKE","MICHEAL","JOHN","STEVE","LEE","JOSH","KIM"};

    // 펀드
    private String [] theme = {"KODEX","NASDAQ","S&P500","JAPAN","INDEX","글로벌","Premium","GROWTH"};
    private String [] manageCompany = {"INVESCO","KODEX","미래에셋자산운용","Vanguard","JP.MORGAN"};

    // 채권
    private String [] country = {"미국","한국","일본","영국","북한","중국"};
    private String [] year = {"5","10","30"};


    private Product [] products = {};

    private HashMap product_lst = new HashMap();

    public Stock createStock(RISK risk){
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
                return new Stock(stockName,price,"ABCD",quantity,CEO[ceo_idx],risk);
            }
        }
    }

    public Fund createFund(){
        String fundName;
        while(true){
            int theme_idx = random.nextInt(0, theme.length);
            int field_idx = random.nextInt(0, field.length);
            int manage_idx = random.nextInt(0, manageCompany.length);

            RISK risk;

            if(theme_idx >= 4){
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

                return new Fund(fundName,price,"ABCD",quantity,management,commission,risk);
            }
        }
    }
}
