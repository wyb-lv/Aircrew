import java.util.Map;
import java.util.HashMap;

public class AircrewTeam {
    private String teamID;
    private String teamName;
    private Map<String, AircrewMember> membersMap;

    public AircrewTeam(String teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.membersMap = new HashMap<>();
    }

    public Map<String, AircrewMember> getMembersMap() {
        return membersMap;
    }
    public void addMember(AircrewMember member) {
        membersMap.put(member.getId(), member);
    }

    protected String toCsvString() {
        StringBuilder sb = new StringBuilder();
        sb.append(teamID).append(",").append(teamName);
        for (AircrewMember member : membersMap.values()) {
            sb.append(",").append(member.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "TeamID: " + teamID + ", Team Name: " + teamName;
    }
}