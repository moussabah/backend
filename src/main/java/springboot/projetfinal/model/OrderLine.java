package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private int id;
	@JsonView(JsonViews.Common.class)
	private int quantity;
	@JsonView(JsonViews.Common.class)
	private double line_price;

	@ManyToOne
	@JoinColumn(name= "ITEM_ID")
	@JsonView(JsonViews.OrderLineWithAll.class)
	private Item item;

	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	@JsonView(JsonViews.OrderLineWithAll.class)
	private Order order;
	@JsonView(JsonViews.Common.class)
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		return order;
	}
	public void setOrder(Order Order) {
		this.order = Order;
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
		this.line_price = this.item.getPrice() * this.quantity;
	}



	public void removeQuantity(int quantity) {
		if (this.quantity - quantity >= 0) {
			this.quantity -= quantity;
			this.line_price = this.item.getPrice() * this.quantity;
		} else {
			// Gérer le cas où la quantité devient négative si nécessaire
			this.quantity = 0;
			this.line_price = 0;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof OrderLine orderLine)) return false;
        return this.item.getRef() == orderLine.item.getRef() ;
	}


	@Override
	public String toString() {
		return "LigneOrder [id=" + id + ", quantity=" + quantity + ", line_price=" + line_price + ", item="
				+ item + "]";
	}

}
