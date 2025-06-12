package springboot.projetfinal.model;

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
    private Long ref;
    private String name;
    private double price;
    private String description;
    private String pathImg;
    private int rate;
    private int nbRate;

    private Category category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderLine> order_lines = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private Collection<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="CUSTOMER_FAV_ID")
    private Customer customer;

    @Version
    private int version;

    public Item() {
        super();
    }

    public Item(long ref,String name, double price, String description, String pathImg) {
        super();
        this.ref = ref;
        this.name = name;
        this.price = price;
        this.description = description;
        this.pathImg = pathImg;
    }

    public String getName() {
        return name;
    }

    public long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
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

    public Collection<OrderLine> getOrder_Lines() {
        return order_lines;
    }

    public void setOrder_Lines(Collection<OrderLine> order_lines) {
        this.order_lines.clear(); // vide l'ancienne liste
        if (order_lines != null) {
            this.order_lines.addAll(order_lines); // ajoute les nouvelles order_lines
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public void removeIngredient(Long ingredientId) {
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
