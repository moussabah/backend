package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ingredients")
public class Ingredient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
    private int id;
	@JsonView(JsonViews.Common.class)
	private String name;
	@JsonView(JsonViews.Common.class)
    private double quantity;
	@JsonView(JsonViews.Common.class)
    private String unit; // Exemple : "g", "ml", "pcs"

	@JsonView(JsonViews.IngredientWithAll.class)
	@ManyToMany(mappedBy = "ingredients")
	private Collection<Item> items = new ArrayList<>();
	@JsonView(JsonViews.Common.class)
	@Version
	private int version;

	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ingredient(String name, double quantity, String unit) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {return unit;}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}


	public void addItem(Item item) {
		if (!items.contains(item)) {
			items.add(item);
			item.getIngredients().add(this);
		}
	}

	public void removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
			item.getIngredients().remove(this);
		}
	}

	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Ingredient{" + "id=" + id + ", name='" + name + '\'' + ", quantity=" + quantity +
				", unit='" + unit + '}';
	}
}
