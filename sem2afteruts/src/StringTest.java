import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        boolean isUser1Turn = true;

        String chatHistoryString = "";
        StringBuffer chatHistoryBuffer = new StringBuffer();

        System.out.println("== Simulasi Chat Dua Orang ==");
        System.out.println("Ketik 'exit' untuk keluar.\n");

        while (!exit) {
            String currentUser = isUser1Turn ? "User1" : "User2";
            System.out.print(currentUser + ": ");
            String inputText = scanner.nextLine();

            if (inputText.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }

            String message = currentUser + ": " + inputText + "\n";
            chatHistoryString += message;
            chatHistoryBuffer.append(message);

            isUser1Turn = !isUser1Turn;
        }

        System.out.println("\n=== Riwayat Chat (String) ===");
        System.out.print(chatHistoryString);

        System.out.println("\n=== Riwayat Chat (StringBuffer) ===");
        System.out.print(chatHistoryBuffer.toString());

        scanner.close();
    }
}
