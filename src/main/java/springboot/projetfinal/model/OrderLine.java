package springboot.projetfinal.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int quantity;
	private double line_price;

	@ManyToOne
	@JoinColumn(name= "ITEM_ID")
	private Item item;

	@ManyToOne
	@JoinColumn(name="Order_ID")
	private Order Order;

	@Version
	private int version;

	public OrderLine() {
		super();
	}
	public OrderLine(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.line_price = item.getPrice() * quantity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getLine_Price() {
		return line_price;
	}
	public void setLine_Price(double line_price) {
		this.line_price = line_price;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Order getOrder() {
		return Order;
	}
	public void setOrder(Order Order) {
		this.Order = Order;
	}

	public double getLine_price() {
		return line_price;
	}

	public void setLine_price(double line_price) {
		this.line_price = line_price;
	}

	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
		this.line_price = item.getPrice() * quantity;
	}
	public void removeQuantity(int quantity) {
		this.quantity -= quantity;
		this.line_price = item.getPrice() * quantity;
	}


	@Override
	public boolean equals(Object o) {
		if (!(o instanceof OrderLine orderLine)) return false;
        return this.item.getRef() == orderLine.item.getRef() ;
	}


	@Override
	public String toString() {
		return "LigneOrder [id=" + id + ", quantity=" + quantity + ", line_price=" + line_price + ", item="
				+ item + ", Order=" + Order + "]";
	}

}
