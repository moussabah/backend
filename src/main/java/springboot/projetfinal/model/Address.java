package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.Common.class)
    private int id;
    @JsonView(JsonViews.Common.class)
    private int streetNumber;
    @JsonView(JsonViews.Common.class)
    private String street;
    @JsonView(JsonViews.Common.class)
    private String city;
    @JsonView(JsonViews.Common.class)
    private String postalCode;
    @JsonView(JsonViews.Common.class)
    private String country;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonView(JsonViews.AddressWithAll.class)
    private Customer customer;
    @JsonView(JsonViews.Common.class)
    @Version
    private int version;

    public Address() {
        super();
    }

    public Address(String street, String city, String postalCode, Customer customer, int streetNumber, String country) {
        super();
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.customer = customer;
        this.streetNumber = streetNumber;
        this.country = country;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Customer getCustomer() {
        return customer;
    }
    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null && !customer.getAddresses().contains(this)) {
            customer.getAddresses().add(this);
        }
    }

    @Override
    public String toString() {
        return streetNumber+" " + street + ", " + postalCode + ", " +city + ", " + country ;
    }
}
