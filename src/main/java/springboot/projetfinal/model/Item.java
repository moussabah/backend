package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import springboot.projetfinal.enums.Category;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public class Item {

    @Id
    @JsonView(JsonViews.Common.class)
    private int ref;
    @JsonView(JsonViews.Common.class)
    private String name;
    @JsonView(JsonViews.Common.class)
    private double price;
    @JsonView(JsonViews.Common.class)
    private String description;
    @JsonView(JsonViews.Common.class)
    private String pathImg;
    @JsonView(JsonViews.Common.class)
    private int rate;
    @JsonView(JsonViews.Common.class)
    private int nbRate;
    @JsonView(JsonViews.ItemWithAll.class)
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(JsonViews.ItemWithAll.class)
    private Collection<OrderLine> order_lines = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "item_ingredient",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JsonView(JsonViews.ItemWithAll.class)
    private Collection<Ingredient> ingredients = new ArrayList<>();

    @ManyToMany (mappedBy = "items")
    @JsonView(JsonViews.ItemWithAll.class)
    private Collection<Customer> customers = new ArrayList<>();
    @JsonView(JsonViews.Common.class)
    @Version
    private int version;

    public Item() {
        super();
    }

    public Item(int ref,String name, double price, String description,
                String pathImg, int rate, int nbRate, Category category) {
        super();
        this.ref = ref;
        this.name = name;
        this.price = price;
        this.description = description;
        this.pathImg = pathImg;
        this.rate = rate;
        this.nbRate = nbRate;
        this.category = category;
    }
    public Item(int ref,String name, double price, String description, String pathImg, Category category) {
        super();
        this.ref = ref;
        this.name = name;
        this.price = price;
        this.description = description;
        this.pathImg = pathImg;
        this.category = category;
    }

    public Item(int ref,String name, double price, String description, String pathImg) {
        super();
        this.ref = ref;
        this.name = name;
        this.price = price;
        this.description = description;
        this.pathImg = pathImg;
    }

    public Item(int ref,String name, double price, String description) {
        super();
        this.ref = ref;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item(int ref,String name, double price) {
        super();
        this.ref = ref;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRef() {
        return ref;
    }
    public void setRef(int ref) {
        this.ref = ref;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImg() {
        return pathImg;
    }
    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public Collection<OrderLine> getOrder_Lines() {
        return order_lines;
    }

    public void setOrder_Lines(Collection<OrderLine> order_lines) {
        this.order_lines.clear();
        if (order_lines != null) {
            this.order_lines.addAll(order_lines);
        }
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getNbRate() {
        return nbRate;
    }

    public void setNbRate(int nbRate) {
        this.nbRate = nbRate;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<OrderLine> getOrder_lines() {
        return order_lines;
    }

    public void setOrder_lines(Collection<OrderLine> order_lines) {
        this.order_lines = order_lines;
    }

    public void addLigne(OrderLine ligne) {
        if (!order_lines.contains(ligne)) {
            order_lines.add(ligne);
            ligne.setItem(this);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            ingredients.add(ingredient);
            ingredient.getItems().add(this);
        }
    }
    public void removeLine(OrderLine line) {
        if (order_lines.contains(line)) {
            order_lines.remove(line);
            line.setItem(null);
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            ingredients.remove(ingredient);
            ingredient.getItems().remove(this);
        }
    }

    public void removeIngredient(int ingredientId) {
        Ingredient ingredient = new Ingredient();
    }

    public void removeAllLines() {
        for (OrderLine line : order_lines) {
            removeLine(line);
        }
    }

    @Override
    public String toString() {
        return "Item [ref=" + ref + ", name=" + name + ", price=" + price + ", description=" + description
                + ", pathImg=" + pathImg + ", ingredients=" + ingredients + "]";
    }

}
