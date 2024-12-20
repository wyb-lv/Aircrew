import java.util.*;

public class AircrewManagement {
    private AircrewFile aircrewFile;
    private Scanner scanner;

    public AircrewManagement() {
        aircrewFile = new AircrewFile();
        this.scanner = new Scanner(System.in);
    }

    protected void createAircrewTeam() {
        System.out.print("Enter the Team ID: ");
        String teamId = scanner.nextLine().trim();
        if (teamId.isEmpty()) {
            System.out.println("Team ID cannot be empty. Please enter a valid Team ID.");
            return;
        }
        if (aircrewFile.getAircrewMap().containsKey(teamId)) {
            System.out.println("Team ID already exists. Please choose a different Team ID.");
            return;
        }
        if (!teamId.matches("[A-Za-z0-9]{3,}")) {
            System.out.println("Invalid Team ID format. Team ID must be at least 3 characters long and contain only letters and numbers.");
            return;
        }
        System.out.print("Enter the Team Name: ");
        String teamName = scanner.nextLine().trim();
        if (teamName.isEmpty()) {
            System.out.println("Team Name cannot be empty. Please enter a valid Team Name.");
            return;
        }
        AircrewTeam newTeam = new AircrewTeam(teamId, teamName);
        aircrewFile.addAircrewTeamtoFile(newTeam);
        System.out.println("Aircrew with ID " + teamId + " and Name " + teamName + " created successfully.");
    }

    protected void addMemberToAircrew() {
        System.out.print("Enter the Team ID to add members: ");
        String teamId = scanner.nextLine();

        if (!aircrewFile.getAircrewMap().containsKey(teamId)) {
            System.out.println("Invalid Team ID. Please enter a valid ID");
            return;
        }
        AircrewTeam team = aircrewFile.getAircrewMap().get(teamId);
        System.out.println("Team found: " + team.getTeamName());

        System.out.println("Available Pilots:");
        for (AircrewMember pilot : aircrewFile.pilots) {
            System.out.println(pilot.getId()+ "-" + pilot.getName());
        }
        System.out.println("Available Flight Attendants:");
        for (AircrewMember attendant : aircrewFile.attendants) {
            System.out.println(attendant.getId() + "-" + attendant.getName());
        }
        System.out.print("Enter ID of Pilot: ");
        String pilotID = scanner.nextLine();
        AircrewMember pilot = findMemberByID(pilotID, aircrewFile.pilots);
        if (pilot != null) {
            team.addMember(pilot);
        } else {
            System.out.println("Invalid Pilot ID");
            return;
        }
        System.out.print("Enter ID of 1st Flight Attendant: ");
        String stAttendantID = scanner.nextLine();
        AircrewMember attendant1 = findMemberByID(stAttendantID, aircrewFile.attendants);
        if (attendant1 != null) {
            team.addMember(attendant1);
        } else {
            System.out.println("Invalid Flight Attendant ID");
            return;
        }
        System.out.print("Enter ID of 2nd Flight Attendant: ");
        String ndAttendantID = scanner.nextLine();
        AircrewMember attendant2 = findMemberByID(ndAttendantID, aircrewFile.attendants);
        if (attendant2 != null) {
            team.addMember(attendant2);
        } else {
            System.out.println("Invalid Flight Attendant ID");
            return;
        }
        aircrewFile.saveAircrewToFile();
        System.out.println("Successfully added members to the team!");
    }

    protected AircrewMember findMemberByID(String ID, List<AircrewMember> members) {
        for (AircrewMember member : members) {
            if (member.getId().equals(ID)) {
                return member;
            }
        }
        return null;
    }

    protected void searchAircrew() {
        System.out.print("Enter the Team ID to search: ");
        String teamId = scanner.nextLine();

        AircrewTeam team = aircrewFile.getAircrewMap().get(teamId);
        if (team != null) {
            System.out.println("Team found: " + team.getTeamName());
            System.out.println("Team ID: " + team.getTeamID());
            System.out.println("Members:");
            for (AircrewMember member : team.getMembers().values()) {
                System.out.println("- " + member.getName() + " (" + member.getRole() + ")");
            }
        } else {
            System.out.println("No team found with the given ID");
        }
    }

    protected void displayAllAircrewTeams() {
        if (aircrewFile.getAircrewMap().isEmpty()) {
            System.out.println("No aircrew teams available");
            return;
        }
        System.out.println("=== List of All Aircrew Teams ===");
        for (AircrewTeam team : aircrewFile.getAircrewMap().values()) {
            System.out.println("Team ID: " + team.getTeamID());
            System.out.println("Team Name: " + team.getTeamName());
            System.out.println("Members:");
            for (AircrewMember member : team.getMembers().values()) {
                System.out.println("- " + member.getName() + " (" + member.getRole() + ")");
            }
            System.out.println();
        }
    }

    protected void deleteAircrewTeam() {
        System.out.print("Enter the Team ID to delete: ");
        String teamId = scanner.nextLine();
        if (!aircrewFile.getAircrewMap().containsKey(teamId)) {
            System.out.println("Team ID not found. Please enter a valid ID");
            return;
        }
        aircrewFile.removeAircrewTeam(teamId);
        aircrewFile.saveAircrewToFile();
        System.out.println("Aircrew Team with ID " + teamId + " has been deleted");
    }
}