package springboot.projetfinal.model;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import springboot.projetfinal.enums.Status;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.Common.class)
    private int id;
    @JsonView(JsonViews.Common.class)
    private double totalPrice;

    @JsonView(JsonViews.OrderWithAll.class)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(JsonViews.OrderWithAll.class)
    private Collection<OrderLine> order_lines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    @JsonView(JsonViews.OrderWithAll.class)
    private Customer customer;
    @JsonView(JsonViews.Common.class)
    @Version
    private int version;

    public Order() {
        super();
        this.totalPrice = 0;
        this.status = Status.INCOMPLETE;
    }

    public Order(Customer customer) {
        super();
        this.customer = customer;
        this.status = Status.INCOMPLETE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<OrderLine> getLine() {
        return order_lines;
    }

    public void setLine(Collection<OrderLine> line) {
        this.order_lines = line;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<OrderLine> getOrder_Lines() {
        return order_lines;
    }

    public void setOrder_Lines(Collection<OrderLine> order_lines) {
        this.order_lines = order_lines;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Collection<OrderLine> getOrder_lines() {
        return order_lines;
    }

    public void setOrder_lines(Collection<OrderLine> order_lines) {
        this.order_lines = order_lines;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void addLine(OrderLine line){
        if (!order_lines.contains(line)) {
            order_lines.add(line);
            line.setOrder(this);
            this.totalPrice += line.getLine_Price();
        }
    }

    public void removeLine(OrderLine line){
        if (order_lines.contains(line)) {
            order_lines.remove(line);
            line.setOrder(null);
        }
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", totalPrice=" + totalPrice + ", status=" + status +
                ", order_lines=" + order_lines + '}';
    }
}
