import java.util.Map;

public class User implements Runnable{
    private Long id;
    private Account account;
    private String userId;

    public Account getAccount() {
        return account;
    }

    private String password;

    public User(Account account,String userId,String password){
        id = IdGenerator.setId();
        this.account = account;
        this.userId = userId;
        this.password = password;
    }

    @Override
    public void run() {
        ProductController productController = ProductController.getProductController();
        Map<String,Product> product_lst = productController.getProduct_lst();
        for(String key: product_lst.keySet()){
            account.buy(key,5l);
        }
    }
}
