import java.io.*;
import java.util.*;

public class File {
    private static final String fileAircrew = "aircrew.csv";
    private static final String fileEmployees = "employee_data.csv";

    protected void checkFileExist(String fileName){
        java.io.File file = new java.io.File(fileName);
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
    }

    public void loadAircrewFromFile(Map<String, AircrewTeam> aircrewMap) {
        checkFileExist(fileAircrew);
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

    public void loadEmployeesFromFile(List<AircrewMember> pilots, List<AircrewMember> attendants) {
        checkFileExist(fileEmployees);
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
                    if (role.equalsIgnoreCase("Pilot")) {
                        pilots.add(member);
                    } else if (role.equalsIgnoreCase("Flight Attendant")) {
                        attendants.add(member);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void saveAircrewToFile(Map<String, AircrewTeam> aircrewMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAircrew))) {
            for (AircrewTeam team : aircrewMap.values()) {
                writer.write(team.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
