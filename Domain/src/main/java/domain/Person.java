package domain;

/**
 * Created by oliv on 4/14/16.
 */
public abstract class Person {
    private String password;
    private String username;
    private String name;
    private String email;

    public Person(String email, String name, String password) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return !(email != null ? !email.equals(person.email) : person.email != null);

    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email: " + email + " Name:" + name;
    }
}
