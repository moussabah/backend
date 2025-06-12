package springboot.projetfinal.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends Authentification{

    private String firstname;
    private String lastname;

    private int phone;
    private String photo;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Item> items = new ArrayList<>();


    public Customer() {
        super();
    }

    public Customer(String login, String password) {
        super(login, password);
    }

    public Customer(String firstname, String lastname,String login, String password,
                    Collection<Order> orders, Collection<Address> addresses, Collection<Reservation> reservations) {
        super(login, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.orders= orders;
        this.addresses=addresses;
        this.reservations=reservations;
    }

    public String getName() {return firstname;}
    public void setName(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}

    public Collection<Order> getOrders() {return orders;}
    public void setOrders(Collection<Order> orders) {this.orders = orders;}

    public Collection<Address> getAddresses() {return addresses;}
    public void setAddresses(Collection<Address> addresses) {this.addresses = addresses;}

    public Collection<Reservation> getReservations() {return reservations;}
    public void setReservations(Collection<Reservation> reservations) {this.reservations = reservations;}

    public int getPhone() {return phone;}
    public void setPhone(int phone) {this.phone = phone;}


    public void addOrder(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
            order.setCustomer(this);
        }
    }


    @Override
    public String toString() {
        return "Client [firstname=" + firstname + ", lastname=" + lastname + ", Orders=" + orders + "]";
    }


}

