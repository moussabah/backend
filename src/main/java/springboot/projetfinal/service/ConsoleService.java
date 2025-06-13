package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import springboot.projetfinal.enums.Category;
import springboot.projetfinal.enums.Status;
import springboot.projetfinal.model.*;
import springboot.projetfinal.repo.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ConsoleService implements CommandLineRunner {

//    @Autowired
//    private CustomerRepository customerRepository;
//
//    public void testInsert1() {
//        Address address1 = new Address("123 Main St", "Springfield", "12345", null, 10, "USA");
//        Address address2 = new Address("456 Oak Ave", "Shelbyville", "67890", null, 15, "USA");
//
//        Customer customer = new Customer();
//        customer.setFirstname("John");
//        customer.setLastname("Doe");
//
//        customer.addAddress(address1);
//        customer.addAddress(address2);
//
//        address1.setCustomer(customer);
//        address2.setCustomer(customer);
//
//        customerRepository.save(customer);
//    }

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public void testInsert() {
        // Créer des adresses
        Address address1 = new Address();
        address1.setStreet("12 Rue Albert Einstein");
        address1.setCity("Rennes");
        address1.setPostalCode("12345");
        address1.setStreetNumber(10);
        address1.setCountry("France");

        Address address2 = new Address();
        address2.setStreet("456 Oak Ave");
        address2.setCity("Shelbyville");
        address2.setPostalCode("67890");
        address2.setStreetNumber(15);
        address2.setCountry("USA");

        Address address3 = new Address();
        address3.setStreet("789 Maple Street");
        address3.setCity("Paris");
        address3.setPostalCode("98765");
        address3.setStreetNumber(22);
        address3.setCountry("France");

        Address address4 = new Address();
        address4.setStreet("123 Elm St");
        address4.setCity("London");
        address4.setPostalCode("45678");
        address4.setStreetNumber(5);
        address4.setCountry("UK");

        // Créer des customers
        Customer customer1 = new Customer();
        customer1.setFirstname("John");
        customer1.setLastname("Doe");

        Customer customer2 = new Customer();
        customer2.setFirstname("Jane");
        customer2.setLastname("Smith");

        // Ajouter les adresses au customer (Relation bidirectionnelle)
        customer1.addAddress(address1);
        customer1.addAddress(address2);
        customer2.addAddress(address3);
        customer2.addAddress(address4);

        // Définir le customer dans chaque adresse (pour respecter la relation bidirectionnelle)
        address1.setCustomer(customer1);
        address2.setCustomer(customer1);
        address3.setCustomer(customer2);
        address4.setCustomer(customer2);

        // Sauvegarder les customers et leurs adresses (Cascading des adresses)
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        // Créer des items (Pizza Margherita et Pizza Pepperoni)
        Item item1 = new Item();
        item1.setRef(101L);
        item1.setName("Pizza Margherita");
        item1.setPrice(12.50);
        item1.setDescription("Delicious Margherita pizza with mozzarella and basil");
        item1.setPathImg("pizza_margherita.jpg");
        item1.setRate(5);
        item1.setNbRate(10);
        item1.setCategory(Category.MAIN);

        Item item2 = new Item();
        item2.setRef(102L);
        item2.setName("Pizza Pepperoni");
        item2.setPrice(14.00);
        item2.setDescription("Pepperoni pizza with mozzarella and spicy pepperoni");
        item2.setPathImg("pizza_pepperoni.jpg");
        item2.setRate(4);
        item2.setNbRate(20);
        item2.setCategory(Category.MAIN);

        // Sauvegarder les items
        itemRepository.save(item1);
        itemRepository.save(item2);

        // Créer des ingrédients pour les pizzas
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Mozzarella");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Basil");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setName("Pepperoni");

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setName("Tomato Sauce");

        // Ajouter les ingrédients aux items
        item1.addIngredient(ingredient1);
        item1.addIngredient(ingredient2);
        item2.addIngredient(ingredient3);
        item2.addIngredient(ingredient4);

        // Sauvegarder les ingrédients
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);

        // Sauvegarder les associations item-ingredient
        itemRepository.save(item1);
        itemRepository.save(item2);

        // Créer des réservations pour les customers
        Reservation reservation1 = new Reservation();
        reservation1.setCustomer(customer1);

        Reservation reservation2 = new Reservation();
        reservation2.setCustomer(customer2);

        // Sauvegarder les réservations
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        // Créer des commandes pour les customers
        Order order1 = new Order();
        order1.setTotalPrice(25.00);
        order1.setStatus(Status.PENDING);
        order1.setCustomer(customer1); // Lier la commande au customer

        Order order2 = new Order();
        order2.setTotalPrice(28.00);
        order2.setStatus(Status.PENDING);
        order2.setCustomer(customer2); // Lier la commande au customer

        // Ajouter des lignes de commande (OrderLine) à la commande
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantity(2);
        orderLine1.setLine_price(25.00);
        orderLine1.setItem(item1);
        orderLine1.setOrder(order1); // Lier la ligne de commande à la commande

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantity(1);
        orderLine2.setLine_price(14.00);
        orderLine2.setItem(item2);
        orderLine2.setOrder(order1);

        OrderLine orderLine3 = new OrderLine();
        orderLine3.setQuantity(1);
        orderLine3.setLine_price(14.00);
        orderLine3.setItem(item2);
        orderLine3.setOrder(order2);

        OrderLine orderLine4 = new OrderLine();
        orderLine4.setQuantity(2);
        orderLine4.setLine_price(28.00);
        orderLine4.setItem(item1);
        orderLine4.setOrder(order2);

        // Ajouter les lignes de commande aux commandes
        order1.getOrder_lines().add(orderLine1);
        order1.getOrder_lines().add(orderLine2);
        order2.getOrder_lines().add(orderLine3);
        order2.getOrder_lines().add(orderLine4);

        // Sauvegarder les commandes
        orderRepository.save(order1);
        orderRepository.save(order2);
    }

    @Override
    public void run(String... args) throws Exception {
        testInsert();
    }
}