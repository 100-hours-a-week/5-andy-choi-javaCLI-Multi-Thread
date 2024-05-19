import java.util.HashMap;
import java.util.Random;

public class ProductController {
    static Random random = new Random();

    private String [] company = {"현대","대우","삼성","SK","LG","KT","롯데","하나","한국","POSCO"};
    private String [] field = {"전자","전기","조선","금융","식품","전력","물산","네트웍스","모바일","모비스","자동차"};
    private String [] group = {"주식 회사","그룹","컴퍼니","보험","기업","글로벌","솔루션"};

    private String [] country = {"미국","한국","일본","영국","북한","중국"};
    private String [] year = {"5","10","30"};

    private String [] CEO ={"ELON","MIKE","MICHEAL","JOHN","STEVE","LEE","JOSH","KIM"};

    private Product [] products = {};

    private HashMap product_lst = new HashMap();

    public void createStock(){
        String newStockName = null;
        while(true){
            int company_idx = random.nextInt(0,company.length);
            int field_idx = random.nextInt(0,field.length);
            int group_idx = random.nextInt(0,group.length);

            newStockName = company[company_idx] + " " + field[field_idx] + " " + group[group_idx];
            if(!product_lst.containsKey(newStockName)){

            }

        }
    }
}
