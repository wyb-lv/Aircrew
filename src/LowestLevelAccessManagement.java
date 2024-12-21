import java.util.Scanner;

public class LowestLevelAccessManagement {
    private Scanner scanner;
    public LowestLevelAccessManagement(){
        this.scanner = new Scanner(System.in);
    }
    protected void run(){
        File file = new File();
        AircrewManagement aircrewManagement = new AircrewManagement(file);
        while (true){
            System.out.println("==== Lowest Level Access Management Account ====");
            System.out.println("1. Display Aircrew Teams");
            System.out.println("2. Search Aircrew Team by ID");
            System.out.println("3. Display Flight Information");
            System.out.println("4. Search Flight Information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    aircrewManagement.displayAllAircrewTeams();
                    break;
                case 2:
                    aircrewManagement.searchAircrew();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
