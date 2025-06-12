package springboot.projetfinal.model;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int streetNumber;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @Version
    private int version;

    public Address() {
        super();
    }
    public Address(String street, String city, String postalCode,  Customer customer, int streetNumber, String country) {
        super();
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.customer = customer;
        this.streetNumber = streetNumber;
        this.country = country;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    @Override
    public String toString() {
        return "Address [id=" + id + ", street=" + street + ", city=" + city + ", postalCode=" + postalCode + "]";
    }
}
