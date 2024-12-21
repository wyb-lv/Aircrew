import java.util.Scanner;

public class AircrewMenu {
    private Scanner scanner;

    public AircrewMenu() {
        this.scanner = new Scanner(System.in);
    }

    protected void run() {
        File aircrewFile = new File();
        AircrewManagement aircrewManagement = new AircrewManagement(aircrewFile);

        while (true) {
            System.out.println("==== Aircrew Management System ====");
            System.out.println("1. Create Aircrew Team");
            System.out.println("2. Add Members to Aircrew");
            System.out.println("3. Display Aircrew Teams");
            System.out.println("4. Search Aircrew Team by ID");
            System.out.println("5. Delete Aircrew Team");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter number of Aircrew to create: ");
                    int numberOfTeams = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numberOfTeams; i++) {
                        System.out.println("Creating Team " + (i + 1) + ":");
                        aircrewManagement.createAircrewTeam();
                    }
                    break;
                case 2:
                    aircrewManagement.addMemberToAircrew();
                    break;
                case 3:
                    aircrewManagement.displayAllAircrewTeams();
                    break;
                case 4:
                    aircrewManagement.searchAircrew();
                    break;
                case 5:
                    aircrewManagement.deleteAircrewTeam();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
