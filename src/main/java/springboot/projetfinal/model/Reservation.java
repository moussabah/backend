package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import springboot.projetfinal.enums.Slot;

import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @JsonView(JsonViews.Common.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonView(JsonViews.ReservationWithAll.class)
    @Enumerated(EnumType.STRING)
    private Slot slot;
    @JsonView(JsonViews.Common.class)
    private int nbPersons;
    @JsonView(JsonViews.Common.class)
    private Date date;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    @JsonView(JsonViews.ReservationWithAll.class)
    private Customer customer;
    @JsonView(JsonViews.Common.class)
    @Version
    private int version;

    public Reservation() {
        super();
    }

    public Reservation(Customer customer, Slot slot, int nbPersons, Date date) {
        super();
        this.slot = slot;
        this.nbPersons = nbPersons;
        this.date = date;
        this.customer = customer;
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (slot == null) {
            throw new IllegalArgumentException("Slot cannot be null");
        }
    }

    public Reservation(Slot slot, int nbPersons, Date date) {
        super();
        this.slot = slot;
        this.nbPersons = nbPersons;
        this.date = date;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getNbPersons() {
        return nbPersons;
    }

    public void setNbPersons(int nbPersons) {
        this.nbPersons = nbPersons;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", slot=" + slot +
                ", nbPersons=" + nbPersons +
                ", date=" + date +
                '}';
    }
}
