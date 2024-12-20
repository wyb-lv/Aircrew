import java.util.Map;
import java.util.HashMap;

public class AircrewTeam {
    private String teamID;
    private String teamName;
    private Map<String, AircrewMember> members;

    public AircrewTeam(String teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.members = new HashMap<>();
    }

    public String getTeamID() {
        return teamID;
    }
    public String getTeamName() {
        return teamName;
    }
    public Map<String, AircrewMember> getMembers() {
        return members;
    }
    public void addMember(AircrewMember member) {
        members.put(member.getId(), member);
    }

    protected String toCsvString() {
        StringBuilder sb = new StringBuilder();
        sb.append(teamID).append(",").append(teamName);
        for (AircrewMember member : members.values()) {
            sb.append(",").append(member.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "TeamID: " + teamID + ", Team Name: " + teamName + ", Members: " + members.size();
    }
}