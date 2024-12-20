import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AircrewFile {
    private static final String fileAircrew = "aircrew.csv";
    private static final String fileEmployees = "employee_data.csv";
    private Map<String, AircrewTeam> aircrewMap;
    private List<AircrewMember> employees;
    List<AircrewMember> pilots;
    List<AircrewMember> attendants;

    public AircrewFile() {
        aircrewMap = new HashMap<>();
        employees = new ArrayList<>();
        pilots = new ArrayList<>();
        attendants = new ArrayList<>();
        loadAircrewFromFile();
        loadEmployeesFromFile();
    }

    protected void loadAircrewFromFile() {
        File file = new File(fileAircrew);
        if (!file.exists()) {
            System.out.println("File does not exist. Creating a new file.");
            try {
                if (file.createNewFile()) {
                    System.out.println("File created.");
                } else {
                    System.out.println("Failed to create file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileAircrew))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String teamId = parts[0].trim();
                    String teamName = parts[1].trim();
                    AircrewTeam team = new AircrewTeam(teamId, teamName);
                    for (int i = 2; i < parts.length; i++) {
                        String memberName = parts[i].trim();
                        String role = (i == 2) ? "Pilot" : "Flight Attendant";
                        AircrewMember member = new AircrewMember("", memberName, "", 0, role);
                        team.addMember(member);
                    }
                    aircrewMap.put(teamId, team);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    protected void saveAircrewToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAircrew))) {
            for (AircrewTeam team : aircrewMap.values()) {
                writer.write(team.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    protected void addAircrewTeamtoFile(AircrewTeam team) {
        aircrewMap.put(team.getTeamID(), team);
    }

    public Map<String, AircrewTeam> getAircrewMap() {
        return aircrewMap;
    }

    protected void removeAircrewTeam(String teamId) {
        aircrewMap.remove(teamId);
    }

    protected void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileEmployees))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String gender = data[2].trim();
                    String role = data[3].trim();
                    int age = Integer.parseInt(data[4].trim());
                    AircrewMember member = new AircrewMember(id, name, gender, age, role);
                    employees.add(member);

                    if (role.equalsIgnoreCase("Pilot")) {
                        pilots.add(member);
                    } else if (role.equalsIgnoreCase("Flight Attendant")) {
                        attendants.add(member);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the employee data: " + e.getMessage());
        }
    }
}
