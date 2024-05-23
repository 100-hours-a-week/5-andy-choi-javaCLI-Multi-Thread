import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isDead = false;

        boolean win = false;

        boolean exit = false;

        int turn = 0;

        MessageController msgController = MessageController.getMessageController();

        Scanner scanner = new Scanner(System.in);

        ProductController productController = ProductController.getProductController();

        Account account = Account.getAccount();

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

        Long firstMoney = account.getMoney();

        while(true) {

            if(isDead){
                System.out.println(msgController.endMessage);
                break;
            }

            String[] time = {"초반", "중반","후반"};

            for(int i =0;i<3;i++){

                if(turn != 0 && turn%10 == 0){ // 10턴에 한번 씩 종목들의 배당, 수수료, 이자를 발동
                    account.product_fee();
                }

                msgController.period(time[i]);

                eventController.refresh();

                while(true) {
                    msgController.choice();

                    userInput = scanner.nextLine();

                    if (userInput.equals("1")) {
                        while (true) {
                            System.out.print("구매 주식 명을 입력 (현재 잔액:" + account.getMoney() + ") >>");

                            String name = scanner.nextLine();
                            if (productController.getProduct(name)== null) {
                                System.out.println("존재하지 않는 종목입니다. 입력을 확인해주세요.");
                                continue;
                            }
                            System.out.print("매수 수량 (최대 " + (long) account.getMoney() / productController.getProduct(name).getPrice() + "주) >>");

                            Long amount = scanner.nextLong();
                            scanner.nextLine();  // 버퍼 비우기

                            if (productController.checkAvailable(name, amount)) {
                                Product product = productController.getProduct(name);
                                if (account.withDraw(product.getPrice() * amount)) {
                                    account.append(name, amount);
                                }
                            }

                            System.out.print("구매를 종료하시겠습니까?(1.예 2.아니요)>>");
                            userInput = scanner.nextLine();
                            if (userInput.equals("1")) {
                                break;
                            }
                        }
                    } else if (userInput.equals("2")) {
                        account.showAccount();

                        System.out.print("\n매도할 주식을 입력해주세요>>");
                        String name = scanner.nextLine();
                        System.out.print("\n매도 수량을 입력하세요>>");
                        Long amount = scanner.nextLong();
                        scanner.nextLine();  // 버퍼 비우기

                        account.sell(name, amount);
                    } else if (userInput.equals("3")) {
                        account.showAccount();
                    } else if (userInput.equals("4")) {
                        account.checkMoney();
                    } else if (userInput.equals("5")) {
                        System.out.print("종목명을 입력하세요 >>");
                        String name = scanner.nextLine();
                        Product product = productController.getProduct(name);
                        if (!product.equals(null)) {
                            System.out.println(product);
                        }
                    } else if (userInput.equals("6")) {
                        break;
                    } else if (userInput.equals("7")) {
                        System.out.println(msgController.exitMessage);
                        exit = true;
                        break;
                    } else if(userInput.equals("8")){
                        eventController.printMessage();
                    }
                }
                if(exit){
                    break;
                }

                double ratio = account.calcProfit(firstMoney);
                isDead = eventController.gameOver(ratio);
                if(isDead){
                    break;
                }
                if(ratio>5) {
                    System.out.println(msgController.winMessage);
                    win = true;
                    break;
                }

                turn += 1;
            }
            if(win || exit){
                break;
            }


        }
    }
}
