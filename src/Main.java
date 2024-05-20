import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isSurvive = true;

        MessageController msgController = MessageController.getMessageController();

        Scanner scanner = new Scanner(System.in);

        ProductController productController = ProductController.getProductController();

        Account account = Account.getAccount();

        HashMap products = productController.getProduct_lst();

        System.out.println(msgController.banner);

        System.out.print(msgController.mode);

        String userInput = scanner.nextLine();

        EventController eventController = EventController.getEventController();
        eventController.setMode(Integer.parseInt(userInput)-1);

        productController.initProducts();

        if(userInput.equals("1")){      // 난이도에 따른 시드머니 설정
            account.deposit(100_000_000l);
        } else if (userInput.equals("2")) {
            account.deposit(10_000_000l);
        } else{
            account.deposit(1_000_000l);
        }

        while(true) {

            if(!isSurvive){
                System.out.println(msgController.endMessage);
                break;
            }

            String[] time = {"초반", "중반","후반"};

            for(int i =0;i<3;i++){

                msgController.period(time[i]);

                eventController.refresh();

                msgController.choice();

                userInput = scanner.nextLine();

                if(userInput.equals("1")){
                    while(true) {
                        System.out.println("구매할 주식 명을 입력>>");

                        String name = scanner.nextLine();

                        System.out.println("매수 수량>>");

                        Long amount = scanner.nextLong();
                    }

                }
            }
        }
    }
}
