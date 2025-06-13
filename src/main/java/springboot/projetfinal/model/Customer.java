package springboot.projetfinal.model;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends Authentification{
    @JsonView(JsonViews.Common.class)
    private String firstname;
    @JsonView(JsonViews.Common.class)
    private String lastname;
    @JsonView(JsonViews.Common.class)
    private int phone;
    @JsonView(JsonViews.Common.class)
    private String photo;

    @JsonView(JsonViews.CustomerWithOrders.class)
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Order> orders = new ArrayList<>();

    @JsonView(JsonViews.CustomerWithAddress.class)
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Address> addresses = new ArrayList<>();

    @JsonView(JsonViews.CustomerWithReservations.class)
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Reservation> reservations = new ArrayList<>();

    @JsonView(JsonViews.CustomerWithItems.class)
    @ManyToMany
    @JoinTable(
            name = "customer_items",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Collection<Item> items = new ArrayList<>();

    public Customer() {
        super();
    }

    public Customer(String login, String password) {
        super(login, password);
    }

    public Customer(String login, String password, String firstname, String lastname){
        super(login, password);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Customer(String login, String password, String firstname, String lastname, int phone){
        super(login, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}

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

    public void removeOrder(Order order) {
        if (orders.contains(order)) {
            orders.remove(order);
            order.setCustomer(null);
        }
    }

    // Dans la classe Customer
    public void addAddress(Address address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(address);
        if (address.getCustomer() != this) {
            address.setCustomer(this); // Assurer que la relation bidirectionnelle est correctement définie
        }
    }
    
    public void removeAddress(Address address) {
        if (addresses.contains(address)) {
            addresses.remove(address);
            address.setCustomer(null);
        }
    }

    @Override
    public String toString() {
        return "Customer{" + "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' +
                ", phone=" + phone + ", photo='" + photo + '\'' + ", orders=" + orders + ", addresses=" + addresses +
                ", reservations=" + reservations + ", items=" + items + '}';
    }
}

