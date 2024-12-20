public class AircrewMember {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String role;

    public AircrewMember(String id, String name, String gender, int age, String role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public String getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Role: " + role;
    }
}
