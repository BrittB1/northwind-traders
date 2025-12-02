import java.util.Scanner;

// responsible for visuals; shows menus, formats output, takes user input
public class UserInterface {

    // constants denoted with ALL_CAPS_AND_UNDERSCORES. Tells other programmers 'don't change this!'
    private static final int MENU_WIDTH = 45;
    private static final String MENU_BORDER = "=".repeat(MENU_WIDTH);
    private static final String MENU_LINE = "=".repeat(MENU_WIDTH);

    private Scanner keyboard;

    public int displayMainMenu(){
        displayHeader("Northwind Traders - HOME ");
        System.out.println("1 ) Show All Products");
        System.out.println("2 ) Show All Customers");
        System.out.println(" 0) EXIT");

        System.out.println(MENU_LINE);
        System.out.println("Choose an option to get started: ");

        int choice = keyboard.nextInt();
        keyboard.nextLine();
                return choice;
    }
    public void displayProductTableHeader(){
        System.out.printf("%-5s %-35s %-12s %-10s%n", "ID","Product Name", "Price","Stock");
        System.out.println(MENU_LINE);
    }
    public void displayProductRow(int id, String name, double price,int stock) {
        System.out.printf("%-5d %-35s $%-11.2f %-10d%n", id, name, price, stock);

    }
    public void enterToContinue() {
        System.out.println("\nPress ENTER to continue..");
        keyboard.nextLine();

    }
    public UserInterface() {
        this.keyboard = new Scanner(System.in);
    }

    public void displayHeader(String title) {
        System.out.println("\n" + MENU_BORDER);
        System.out.println(centerText(title, MENU_WIDTH));
    }

    public void displayFooter(String message) {
        System.out.println(MENU_LINE);
        System.out.println(message);
        System.out.println(MENU_BORDER);

    }

    public void displaySuccessMessage(String message) {
        System.out.println("\n " + message);

    }

    public void displayErrorMessage(String message) {
        System.out.println("\n" + message);
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;

        return " ".repeat(Math.max(0, padding)) + text;
    }

    public void close() {
        keyboard.close();
    }
}
