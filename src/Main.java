import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String banner = """
                           _  _                     __  __        _______ _            __  __  ____  _   _ ________     __
                          | || |                   |  \\/  |      |__   __| |          |  \\/  |/ __ \\| \\ | |  ____\\ \\   / /
                         / __) |__   _____      __ | \\  / | ___     | |  | |__   ___  | \\  / | |  | |  \\| | |__   \\ \\_/ /\s
                         \\__ \\ '_ \\ / _ \\ \\ /\\ / / | |\\/| |/ _ \\    | |  | '_ \\ / _ \\ | |\\/| | |  | | . ` |  __|   \\   / \s
                         (   / | | | (_) \\ V  V /  | |  | |  __/    | |  | | | |  __/ | |  | | |__| | |\\  | |____   | |  \s
                          |_||_| |_|\\___/ \\_/\\_/   |_|  |_|\\___|    |_|  |_| |_|\\___| |_|  |_|\\____/|_| \\_|______|  |_|  \s
                                                                                                                         \s
                                                                                                                         \s
                """;
        System.out.println(banner);

        System.out.print("난이도 선택 1.EASY 2.NORMAL 3.HARD:");
        String userInput = scanner.nextLine();
        System.out.println("입력하신 문자는 "+userInput+"입니다.");
    }
}
