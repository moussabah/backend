package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import springboot.projetfinal.enums.Category;
import springboot.projetfinal.enums.Slot;
import springboot.projetfinal.enums.Status;
import springboot.projetfinal.model.*;
import springboot.projetfinal.repo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//        // Définir le customer dans chaque adresse (pour respecter la relation bidirectionnelle)
//        address1.setCustomer(customer1);
//        address2.setCustomer(customer1);
//        address3.setCustomer(customer2);
//        address4.setCustomer(customer2);

        // Sauvegarder les customers et leurs adresses (Cascading des adresses)
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        // Créer des items (Pizza Margherita et Pizza Pepperoni)
        Item item1 = new Item();
        item1.setRef(101);
        item1.setName("Pizza Margherita");
        item1.setPrice(12.50);
        item1.setDescription("Delicious Margherita pizza with mozzarella and basil");
        item1.setPathImg("pizza_margherita.jpg");
        item1.setRate(5);
        item1.setNbRate(10);
        item1.setCategory(Category.MAIN);

        Item item2 = new Item();
        item2.setRef(102);
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

    //ne fonctionne pas

    public void testInsert3() {

        // Création des ingrédients
        Ingredient tomate = new Ingredient("tomate", 100, "gr");
        Ingredient fromage = new Ingredient("fromage", 100, "gr");

        // Création des items
        Item pizza = new Item(2001, "pizza", 15.0, "pizza marguerita", "");

        Item salade = new Item(1001, "salade", 10.0, "salade verte", "");

        pizza.setCategory(Category.MAIN);
        salade.setCategory(Category.STARTER);

        System.out.println(" add ingredient a item ");
        pizza.addIngredient(tomate);
        pizza.addIngredient(fromage);
        salade.addIngredient(tomate);

        System.out.println(" save item ");
        itemRepository.save(pizza);
        itemRepository.save(salade);

        System.out.println(" save ingredient ");
        ingredientRepository.save(tomate);
        ingredientRepository.save(fromage);


    }


    public void testInsert1() {

        // Création des ingrédients
        Ingredient tomate = new Ingredient("tomate", 100, "gr");
        Ingredient fromage = new Ingredient("fromage", 100, "gr");
        Ingredient lait = new Ingredient("lait", 100, "ml");
        Ingredient patate = new Ingredient("pomme de terre", 100, "gr");



        // Création des items
        Item pizza = new Item(2001, "pizza", 15.0, "pizza marguerita", "");
        Item fritte = new Item(2002, "fritte", 10.0, "frittes belges", "");
        Item salade = new Item(1001, "salade", 10.0, "salade verte", "");
        Item mousse = new Item(1002, "mousse", 5.0, "mousse au chocolat", "");

        pizza.setCategory(Category.MAIN);
        fritte.setCategory(Category.MAIN);
        salade.setCategory(Category.STARTER);
        mousse.setCategory(Category.DESSERT);

        pizza.addIngredient(tomate);
        pizza.addIngredient(fromage);
        fritte.addIngredient(patate);
        salade.addIngredient(tomate);

        tomate.addItem(pizza);
        tomate.addItem(salade);
        patate.addItem(fritte);
        fromage.addItem(pizza);

        ingredientRepository.save(tomate);
        ingredientRepository.save(fromage);
        ingredientRepository.save(lait);
        ingredientRepository.save(patate);

        itemRepository.save(pizza);
        itemRepository.save(fritte);
        itemRepository.save(salade);
        itemRepository.save(mousse);







        // Création des adresses
        Address address1 = new Address("123 Main St", "Springfield", "12345", null, 10, "USA");
        Address address2 = new Address("456 Oak Ave", "Shelbyville", "67890", null, 15, "USA");
        Address address3 = new Address("789 Maple Rd", "Capital City", "54321", null, 20, "USA");
        Address address4 = new Address("987 Pine St", "Ogdenville", "98765", null, 25, "USA");


        // Création des clients
        Customer customer1 = new Customer();
        customer1.setLogin("John@gmail.com");
        customer1.setPassword("1234");
        customer1.setFirstname("John");
        customer1.setLastname("Doe");

        Customer customer2 = new Customer();
        customer1.setLogin("Jane@gmail.com");
        customer1.setPassword("1234");
        customer2.setFirstname("Jane");
        customer2.setLastname("Dali");

        Customer customer3 = new Customer();
        customer1.setLogin("Jonathan@gmail.com");
        customer1.setPassword("1234");
        customer3.setFirstname("Jonathan");
        customer3.setLastname("Dule");

        // Ajout des adresses
        customer1.addAddress(address1);
        customer2.addAddress(address2);
        customer3.addAddress(address3);
        customer3.addAddress(address4);

        customer1.setItems(List.of(pizza, fritte));
        customer3.setItems(List.of(pizza, fritte,salade));

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);


    }

    public void testInsert2() {


        //  recupération des items
        Item pizza = itemRepository.findById(2001).get();
        Item fritte = itemRepository.findById(2002).get();
        Item salade = itemRepository.findById(1001).get();
        Item mousse = itemRepository.findById(1002).get();

        // Création des OrderLines
        OrderLine ol1 = new OrderLine(pizza, 10);
        OrderLine ol2 = new OrderLine(fritte, 10);
        OrderLine ol3 = new OrderLine(mousse, 5);
        OrderLine ol4 = new OrderLine(pizza, 1);
        OrderLine ol5 = new OrderLine(salade, 1);

        // Création des Orders
        Order order1 = new Order();
        order1.addLine(ol1);
        order1.addLine(ol2);

        Order order2 = new Order();
        order2.addLine(ol3);

        Order order3 = new Order();
        order3.addLine(ol4);
        order3.addLine(ol5);

        //  recupération des customer
        Customer customer1 = customerRepository.findById(1).get();
        Customer customer2 = customerRepository.findById(2).get();
        Customer customer3 = customerRepository.findById(3).get();


        order1.setCustomer(customer1);
        order2.setCustomer(customer1);
        order3.setCustomer(customer2);


        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);




        // Création et sauvegarde de la réservation
        Reservation resa1 = new Reservation(Slot.AFTERNOON, 2, new Date());
        resa1.setCustomer(customer2);

        reservationRepository.save(resa1);
    }






    @Override
    public void run(String... args) throws Exception {
        testInsert1();
        testInsert2();
    }
}