public class MessageController {

    private MessageController(){};

    private static final MessageController messageController = new MessageController();

    public static MessageController getMessageController(){
        return messageController;
    }

    public String banner = """
                           _  _                     __  __        _______ _            __  __  ____  _   _ ________     __
                          | || |                   |  \\/  |      |__   __| |          |  \\/  |/ __ \\| \\ | |  ____\\ \\   / /
                         / __) |__   _____      __ | \\  / | ___     | |  | |__   ___  | \\  / | |  | |  \\| | |__   \\ \\_/ /\s
                         \\__ \\ '_ \\ / _ \\ \\ /\\ / / | |\\/| |/ _ \\    | |  | '_ \\ / _ \\ | |\\/| | |  | | . ` |  __|   \\   / \s
                         (   / | | | (_) \\ V  V /  | |  | |  __/    | |  | | | |  __/ | |  | | |__| | |\\  | |____   | |  \s
                          |_||_| |_|\\___/ \\_/\\_/   |_|  |_|\\___|    |_|  |_| |_|\\___| |_|  |_|\\____/|_| \\_|______|  |_|  \s
                                                                                                                         \s
                                                                                                                         \s
                """;

    public String mode = "난이도 선택 1.EASY 2.NORMAL 3.HARD:";

    public String endMessage = """
                        💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀                                                              
                                                              ___                      ___ \s
                                                             (   )  .-.               (   )\s
                         ___  ___    .--.    ___  ___      .-.| |  ( __)   .--.     .-.| | \s
                        (   )(   )  /    \\  (   )(   )    /   \\ |  (''")  /    \\   /   \\ | \s
                         | |  | |  |  .-. ;  | |  | |    |  .-. |   | |  |  .-. ; |  .-. | \s
                         | |  | |  | |  | |  | |  | |    | |  | |   | |  |  | | | | |  | | \s
                         | '  | |  | |  | |  | |  | |    | |  | |   | |  |  |/  | | |  | | \s
                         '  `-' |  | |  | |  | |  | |    | |  | |   | |  |  ' _.' | |  | | \s
                          `.__. |  | '  | |  | |  ; '    | '  | |   | |  |  .'.-. | '  | | \s
                          ___ | |  '  `-' /  ' `-'  /    ' `-'  /   | |  '  `-' / ' `-'  / \s
                         (   )' |   `.__.'    '.__.'      `.__,'   (___)  `.__.'   `.__,'  \s
                          ; `-' '                                                          \s
                           .__.'                                                           \s
                        💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀
                        """;

    public void period(String time){
        System.out.println("==========================================================");
        System.out.println("=#=#=#+#=#=#=#=#=#=#=#=#= 장 "+time+" =#=#=#+#=#=#=#=#=#=#=#=#=");
        System.out.println("==========================================================\n\n");
    }

    public void choice(){
        String dice = "\uD83C\uDFB2".repeat(33);
        System.out.println("\n"+dice);
        System.out.println("선택 1.매수 2.매도 3.계좌 조회 4.잔액 조회 5.주식 목록 6.종료");
        System.out.println(dice);
        System.out.print("입력 >>");
    }
}
