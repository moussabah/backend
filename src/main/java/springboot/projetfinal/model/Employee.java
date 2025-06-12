package springboot.projetfinal.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends Authentification{
    private boolean active;

    private String name;
    private String surname;

    @Version
    private int version;
    public Employee() {
        super();
    }
    public Employee(String login, String password, boolean active, String name, String surname) {
        super(login, password);
        this.active = active;
        this.name = name;
        this.surname = surname;
    }

    public Employee(String login, String password, boolean active) {
        super(login, password);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return super.toString() + "Admin [active=" + active + "]";
    }


}
