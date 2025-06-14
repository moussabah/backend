package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends Authentification{

    @JsonView(JsonViews.Common.class)
    private boolean active;
    @JsonView(JsonViews.Common.class)
    private String firstname;
    @JsonView(JsonViews.Common.class)
    private String lastname;
    @JsonView(JsonViews.Common.class)
    private String email;


    public Employee() {
        super();
    }
    public Employee(String login, String password, String firstname, String lastname,boolean active) {
        super(login, password);
        this.active = active;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Employee(String login, String password, boolean active) {
        super(login, password);
        this.active = active;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFullName() {
        return firstname + " " + lastname;
    }
    public void setFullName(String fullName) {
        String[] names = fullName.split(" ");
        if (names.length == 2) {
            this.firstname = names[0];
            this.lastname = names[1];
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                "active=" + active +
                '}';
    }
}