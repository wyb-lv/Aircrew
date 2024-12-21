import java.util.*;

public class AircrewManagement {
    private File file;
    private Scanner scanner;
    private Map<String, AircrewTeam> aircrewMap;
    private List<AircrewMember> pilots;
    private List<AircrewMember> attendants;

    public AircrewManagement(File aircrewFile) {
        this.file = aircrewFile;
        this.scanner = new Scanner(System.in);
        this.aircrewMap = new HashMap<>();
        this.pilots = new ArrayList<>();
        this.attendants = new ArrayList<>();
        loadData();
    }

    public void loadData() {
        file.loadAircrewFromFile(aircrewMap);
        file.loadEmployeesFromFile(pilots, attendants);
    }

    public void createAircrewTeam() {
        System.out.print("Enter the Team ID: ");
        String aircrewID = scanner.nextLine().trim();
        if (aircrewMap.containsKey(aircrewID)) {
            System.out.println("Team ID already exists. Please choose a different Team ID.");
            return;
        }
        if (!aircrewID.matches("[A-Za-z0-9]{3,}") || aircrewID.isEmpty()) {
            System.out.println("Invalid ID");
            return;
        }
        System.out.print("Enter the Team Name: ");
        String teamName = scanner.nextLine().trim();
        if (teamName.isEmpty()) {
            System.out.println("Team name cannot be empty");
            return;
        }
        AircrewTeam newTeam = new AircrewTeam(aircrewID, teamName);
        aircrewMap.put(aircrewID, newTeam);
        file.saveAircrewToFile(aircrewMap);
    }

    public void addMemberToAircrew() {
        System.out.print("Enter the Team ID: ");
        String teamId = scanner.nextLine().trim();

        if (!aircrewMap.containsKey(teamId)) {
            System.out.println("No team found with the given ID.");
            return;
        }
        AircrewTeam team = aircrewMap.get(teamId);
        displayAvailableMembers();
        System.out.print("Enter Pilot ID: ");
        String pilotID = scanner.nextLine().trim();
        AircrewMember pilot = findMemberByID(pilotID, pilots);
        if (pilot != null)
            team.addMember(pilot);
        else{
            System.out.println("Invalid Pilot ID.");
            return;
        }

        for (int i = 1; i <= 2; i++) {
            System.out.print("Enter Flight Attendant " + i + " ID: ");
            String attendantID = scanner.nextLine().trim();
            AircrewMember attendant = findMemberByID(attendantID, attendants);
            if (attendant != null) {
                team.addMember(attendant);
            } else {
                System.out.println("Invalid Attendant ID.");
                return;
            }
        }
        file.saveAircrewToFile(aircrewMap);
    }

    private AircrewMember findMemberByID(String id, List<AircrewMember> members) {
        for (AircrewMember member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    private void displayAvailableMembers() {
        System.out.println("Available Pilots:");
        for (AircrewMember pilot : pilots) {
            System.out.println(pilot.getId() + " - " + pilot.getName());
        }
        System.out.println("Available Flight Attendants:");
        for (AircrewMember attendant : attendants) {
            System.out.println(attendant.getId() + " - " + attendant.getName());
        }
    }
    public void displayAllAircrewTeams() {
        if (aircrewMap.isEmpty()) {
            System.out.println("File is empty");
            return;
        }
        for (AircrewTeam team : aircrewMap.values()) {
            System.out.println(team);
            for (AircrewMember member : team.getMembersMap().values()) {
                System.out.println(member);
            }
        }
    }

    public void deleteAircrewTeam() {
        System.out.print("Enter the Team ID to delete: ");
        String teamId = scanner.nextLine().trim();
        if (!aircrewMap.containsKey(teamId)) {
            return;
        }
        aircrewMap.remove(teamId);
        file.saveAircrewToFile(aircrewMap);
    }

    public void searchAircrew() {
        System.out.print("Enter the Team ID to search: ");
        String teamId = scanner.nextLine().trim();
        AircrewTeam team = aircrewMap.get(teamId);
        if (team != null) {
            System.out.println("Team Found: " + team);
            for (AircrewMember member : team.getMembersMap().values()) {
                System.out.println(member);
            }
        } else {
            System.out.println("No team found with the given ID.");
        }
    }
}
