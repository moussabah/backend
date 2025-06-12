package springboot.projetfinal.model;

import jakarta.persistence.*;
import springboot.projetfinal.enums.Slot;

import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Slot slot;
    private int nbPersons;
    private Date reservationDate;
    private boolean confirmed;

    @Version
    private int version;
    public Reservation() {
        super();
    }

    public Reservation(Slot slot, int nbPersons, Date reservationDate, boolean confirmed) {
        super();
        this.slot = slot;
        this.nbPersons = nbPersons;
        this.reservationDate = reservationDate;
        this.confirmed = confirmed;
    }

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
                ", reservationDate=" + reservationDate +
                ", customer=" + customer +
                '}';
    }
}
