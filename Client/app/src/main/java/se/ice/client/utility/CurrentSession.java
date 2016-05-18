package se.ice.client.utility;

public class CurrentSession {

    // Singleton instance
    private static CurrentSession instance;

    // Session specific data
    private String name;
    private String email;
    private String phone;
    private String description;
    private boolean isAdmin;

    public static CurrentSession getInstance() {
        if(instance == null) {
            instance = new CurrentSession();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
