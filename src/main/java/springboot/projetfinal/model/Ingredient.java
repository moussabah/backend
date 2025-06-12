package springboot.projetfinal.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Ingredient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double quantity;

    private String unit; // Exemple : "g", "ml", "pcs"

	@ManyToMany
	@JoinTable(
			name = "ingredient_item",
			joinColumns = @JoinColumn(name = "ingredient_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private Collection<Item> items;


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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getUnit() {
		return unit;
	}

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
		return "Ingredient [name=" + name + ", quantity=" + quantity + ", unit=" + unit + "]";
	}
}
