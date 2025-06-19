package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import springboot.projetfinal.enums.Category;
import springboot.projetfinal.enums.Role;
import springboot.projetfinal.enums.Slot;
import springboot.projetfinal.enums.Status;
import springboot.projetfinal.model.*;
import springboot.projetfinal.repo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ConsoleService implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EmployeeRepository employeeRepository;



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




    public void testInsert1() {

        // Création des adresses
        Address address1 = new Address("12 rue de la Paix", "Paris", "75002", null, 2, "France");
        Address address2 = new Address("45 avenue des Champs", "Lyon", "69003", null, 10, "France");
        Address address3 = new Address("78 boulevard Saint-Michel", "Marseille", "13006", null, 5, "France");
        Address address4 = new Address("9 place de la République", "Bordeaux", "33000", null, 7, "France");
        Address address5 = new Address("25 rue Victor Hugo", "Nantes", "44000", null, 3, "France");

// Création des clients avec login/password
        Customer customer1 = new Customer("jean@example.com", "1234", "Jean", "Dupont", "0601020304");
        Customer customer2 = new Customer("marie@example.com", "1234", "Marie", "Lefèvre", "0605060708");
        Customer customer3 = new Customer("paul@example.com", "1234", "Paul", "Martin", "0608091011");
        Customer customer4 = new Customer("sophie@example.com", "1234", "Sophie", "Bernard", "0612131415");
        Customer customer5 = new Customer("luc@example.com", "1234", "Luc", "Durand", "0616171819");

// Ajout des adresses
        customer1.addAddress(address1);
        customer2.addAddress(address2);
        customer3.addAddress(address3);
        customer4.addAddress(address4);
        customer5.addAddress(address5);

// Sauvegarde des clients
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);



    }

    public void testInsert2() {
        // Récupération des items par leur ref (id)
        Item boeufBourguignon = itemRepository.findById(2001).get();
        Item coqAuVin = itemRepository.findById(2002).get();
        Item foieGrasEntree = itemRepository.findById(1001).get();
        Item tartareSaumon = itemRepository.findById(1002).get();
        Item mousseChocolat = itemRepository.findById(3001).get();

// Création des OrderLines
        OrderLine ol1 = new OrderLine(boeufBourguignon, 2);
        OrderLine ol2 = new OrderLine(coqAuVin, 1);
        OrderLine ol3 = new OrderLine(foieGrasEntree, 3);
        OrderLine ol4 = new OrderLine(tartareSaumon, 1);
        OrderLine ol5 = new OrderLine(mousseChocolat, 4);

// Création des commandes
        Order order1 = new Order();
        order1.addLine(ol1);
        order1.addLine(ol2);

        Order order2 = new Order();
        order2.addLine(ol3);

        Order order3 = new Order();
        order3.addLine(ol4);
        order3.addLine(ol5);

        Order order4 = new Order();
        order4.addLine(new OrderLine(coqAuVin, 2));
        order4.addLine(new OrderLine(mousseChocolat, 1));

        Order order5 = new Order();
        order5.addLine(new OrderLine(foieGrasEntree, 1));
        order5.addLine(new OrderLine(boeufBourguignon, 1));

// Récupération des clients
        Customer customer1 = customerRepository.findById(1).get();
        Customer customer2 = customerRepository.findById(2).get();
        Customer customer3 = customerRepository.findById(3).get();
        Customer customer4 = customerRepository.findById(4).get();
        Customer customer5 = customerRepository.findById(5).get();

// Association des commandes aux clients
        order1.setCustomer(customer1);
        order2.setCustomer(customer1);
        order3.setCustomer(customer2);
        order4.setCustomer(customer3);
        order5.setCustomer(customer4);

// Sauvegarde des commandes
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        orderRepository.save(order5);

// Création et sauvegarde de réservations pour certains clients
        Reservation resa1 = new Reservation(Slot.AFTERNOON, 2, new Date());
        resa1.setCustomer(customer2);

        Reservation resa2 = new Reservation(Slot.MORNING, 4, new Date());
        resa2.setCustomer(customer3);

        Reservation resa3 = new Reservation(Slot.EVENING, 3, new Date());
        resa3.setCustomer(customer5);

        reservationRepository.save(resa1);
        reservationRepository.save(resa2);
        reservationRepository.save(resa3);
    }

    public void insertMenu(){
        // Création des ingrédients
        Ingredient tomate = new Ingredient("tomate", 100, "gr");
        Ingredient fromage = new Ingredient("fromage", 100, "gr");
        Ingredient pain = new Ingredient("pain", 100, "gr");
        Ingredient beurre = new Ingredient("beurre", 50, "gr");
        Ingredient lait = new Ingredient("lait", 100, "ml");
        Ingredient oeuf = new Ingredient("œuf", 1, "pièce");
        Ingredient chocolat = new Ingredient("chocolat", 100, "gr");
        Ingredient sucre = new Ingredient("sucre", 50, "gr");
        Ingredient cafe = new Ingredient("cafe", 1, "tasse");
        Ingredient the = new Ingredient("the", 1, "tasse");
        Ingredient eau = new Ingredient("eau", 1, "verre");
        Ingredient poulet = new Ingredient("poulet", 150, "gr");
        Ingredient boeuf = new Ingredient("bœuf", 150, "gr");
        Ingredient salade = new Ingredient("salade", 50, "gr");
        Ingredient vinaigrette = new Ingredient("vinaigrette", 20, "ml");
        Ingredient crevette = new Ingredient("crevette", 100, "gr");
        Ingredient citron = new Ingredient("citron", 1, "pièce");
        Ingredient saumon = new Ingredient("saumon", 100, "gr");
        Ingredient creme = new Ingredient("crème", 50, "ml");
        Ingredient champignon = new Ingredient("champignon", 100, "gr");
        Ingredient carotte = new Ingredient("carotte", 50, "gr");
        Ingredient pomme = new Ingredient("pomme", 1, "pièce");
        Ingredient vanille = new Ingredient("vanille", 1, "gousse");
        Ingredient menthe = new Ingredient("menthe", 5, "feuilles");
        Ingredient orange = new Ingredient("orange", 1, "pièce");
        Ingredient fraise = new Ingredient("fraise", 5, "pièces");
        Ingredient foieGras = new Ingredient("foie gras", 80, "gr");
        Ingredient figue = new Ingredient("figue", 30, "gr");
        Ingredient painBrioche = new Ingredient("pain brioché", 50, "gr");
        Ingredient aneth = new Ingredient("aneth", 2, "gr");
        Ingredient huileOlive = new Ingredient("huile d'olive", 10, "ml");
        Ingredient potimarron = new Ingredient("potimarron", 100, "gr");
        Ingredient chataigne = new Ingredient("châtaigne", 50, "gr");
        Ingredient saintJacques = new Ingredient("Saint-Jacques", 100, "gr");
        Ingredient citronVert = new Ingredient("citron vert", 10, "ml");
        Ingredient lapin = new Ingredient("lapin", 100, "gr");
        Ingredient noisette = new Ingredient("noisette", 20, "gr");
        Ingredient roquette = new Ingredient("roquette", 30, "gr");
        Ingredient fromageChevre = new Ingredient("fromage de chèvre", 40, "gr");
        Ingredient miel = new Ingredient("miel", 10, "ml");
        Ingredient bar = new Ingredient("bar", 100, "gr");
        Ingredient citronCaviar = new Ingredient("citron caviar", 5, "gr");
        Ingredient asperge = new Ingredient("asperge", 70, "gr");
        Ingredient huileTruffe = new Ingredient("huile de truffe", 5, "ml");
        Ingredient mozzarella = new Ingredient("mozzarella", 40, "gr");
        Ingredient patate = new Ingredient("patate", 40, "gr");
        Ingredient farine = new Ingredient("farine", 100, "gr");
        Ingredient citronDessert = new Ingredient("citron", 30, "gr");
        Ingredient amande = new Ingredient("amande", 50, "gr");
        Ingredient laitBoisson = new Ingredient("lait", 150, "ml");
        Ingredient sucreBoisson = new Ingredient("sucre", 10, "gr");
        Ingredient citronBoisson = new Ingredient("citron", 20, "gr");
        Ingredient chocolatBoisson = new Ingredient("chocolat", 30, "gr");
        Ingredient glace = new Ingredient("glace", 100, "gr");
        Ingredient haricots = new Ingredient("haricots blancs", 150, "gr");
        Ingredient canardConfit = new Ingredient("confit de canard", 200, "gr");
        Ingredient aubergine = new Ingredient("aubergine", 100, "gr");
        Ingredient courgette = new Ingredient("courgette", 100, "gr");
        Ingredient poivron = new Ingredient("poivron", 50, "gr");
        Ingredient fruitsRouges = new Ingredient("fruits rouges", 50, "gr");
        Ingredient magret = new Ingredient("magret de canard", 200, "gr");
        Ingredient veau = new Ingredient("veau", 150, "gr");
        Ingredient merlu = new Ingredient("merlu", 150, "gr");
        Ingredient madere = new Ingredient("madère", 30, "ml");
        Ingredient foieGrasIngredient = new Ingredient("foie gras", 80, "gr"); // Attention, tu as aussi foieGras déjà créé avant, faire attention aux doublons !



// Création des entrées
        Item foieGrasEntree = new Item(1001, "Foie Gras de Canard ", 18.0, "Foie gras maison accompagné de chutney de figues et pain brioché", "FoieGrasdeCanardmi-cuit", Category.STARTER);
        Item tartareSaumon = new Item(1002, "Tartare de Saumon ", 15.0, "Saumon frais haché au couteau, citron, aneth et huile d’olive", "TartaredeSaumonaAneth", Category.STARTER);
        Item oeufParfait = new Item(1003, "Œuf Parfait sur Crème", 14.0, "Œuf basse température servi sur une mousse de champignons forestiers", "OeufParfaitsurchampignons", Category.STARTER);
        Item veloutePotimarron = new Item(1004, "Velouté de Potimarron ", 12.0, "Soupe onctueuse de potimarron d'automne et châtaignes grillées", "VeloutedePotimarronetChataignes", Category.STARTER);
        Item carpaccioSaintJacques = new Item(1005, "Carpaccio ", 17.0, "Fines tranches de noix de Saint-Jacques marinées au citron vert", "CarpacciodeSaintJacques", Category.STARTER);
        Item terrineLapin = new Item(1006, "Terrine de Lapin ", 13.0, "Terrine rustique maison aux noisettes torréfiées", "TerrinedeLapinauxNoisettes", Category.STARTER);
        Item saladeChevre = new Item(1007, "Chèvre Chaud au Miel", 12.0, "Toasts de chèvre fondant, salade de roquette et miel parfumé", "SaladedeChevreChaud", Category.STARTER);
        Item cevicheBar = new Item(1008, "Ceviche de Bar", 16.0, "Bar mariné aux agrumes et perles de citron caviar", "CevichedeBar", Category.STARTER);
        Item aspergesTruffe = new Item(1009, "Asperges Vertes ", 13.0, "Asperges croquantes servies avec une vinaigrette à la truffe", "AspergesVertes", Category.STARTER);
        Item tarteletteTomate = new Item(1010, "Tartelette à la Tomate", 12.0, "Pâte fine croustillante garnie de tomates confites et mozzarella", "TarteletteTomateMozzarella", Category.STARTER);


// creation des plats de resistance
        Item boeufBourguignon = new Item(2001, "Bœuf Bourguignon", 24.0, "Bœuf mijoté au vin rouge avec carottes et champignons", "BoeufBourguignon", Category.MAIN);
        Item coqAuVin = new Item(2002, "Coq au vin", 23.0, "Poulet mijoté au vin rouge avec lardons et champignons", "CoqAuVin", Category.MAIN);
        Item cassoulet = new Item(2003, "Cassoulet", 25.0, "Ragoût de haricots blancs avec confit de canard", "Cassoulet", Category.MAIN);
        Item saumonGrille = new Item(2004, "Saumon grillé", 22.0, "Filet de saumon grillé avec légumes vapeur", "SaumonGrille", Category.MAIN);
        Item ratatouille = new Item(2005, "Ratatouille", 18.0, "Légumes mijotés (aubergine, courgette, tomate, poivron)", "Ratatouille", Category.MAIN);

        Item magretCanard = new Item(2006, "Magret de canard", 26.0, "Magret de canard sauce aux fruits rouges", "MagretCanard", Category.MAIN);
        Item blanquetteVeau = new Item(2007, "Blanquette de veau", 24.0, "Veau en sauce blanche à la crème et aux champignons", "BlanquetteVeau", Category.MAIN);
        Item filetMerlu = new Item(2008, "Filet de merlu", 21.0, "Filet de poisson blanc sauce citronnée", "FiletMerlu", Category.MAIN);
        Item gratinDauphinois = new Item(2009, "Gratin Dauphinois", 17.0, "Pommes de terre gratinées à la crème et au fromage", "GratinDauphinois", Category.MAIN);
        Item tournedosRossini = new Item(2010, "Tournedos Rossini", 35.0, "Filet de bœuf avec foie gras poêlé et sauce au madère", "TournedosRossini", Category.MAIN);


        // Remplissage de dessert
        Item mousseChocolat = new Item(3001, "Mousse au chocolat", 8.0, "Mousse légère au chocolat noir", "MousseChocolat", Category.DESSERT);
        Item tarteCitron = new Item(3002, "Tarte au citron", 7.5, "Tarte sucrée au citron avec pâte sablée", "TarteCitron", Category.DESSERT);
        Item cremeBrulee = new Item(3003, "Crème brûlée", 9.0, "Crème vanillée caramélisée", "CremeBrulee", Category.DESSERT);
        Item fondantChocolat = new Item(3004, "Fondant au chocolat", 9.5, "Gâteau fondant au chocolat noir", "FondantChocolat", Category.DESSERT);
        Item saladeFruits = new Item(3005, "Salade de fruits frais", 6.5, "Mélange de fruits frais de saison", "SaladeFruits", Category.DESSERT);

        //remplissage de boissons

        Item cafeNoir = new Item(4001, "cafe noir", 2.5, "cafe espresso noir intense", "CafeNoir", Category.DRINK);
        Item cafeAuLait = new Item(4002, "cafe au lait", 3.5, "cafe espresso avec lait chaud", "CafeAuLait", Category.DRINK);
        Item theVert = new Item(4003, "the vert", 3.0, "the vert délicat", "TheVert", Category.DRINK);
        Item theCitron = new Item(4004, "the citron", 3.5, "the noir avec une tranche de citron", "TheCitron", Category.DRINK);
        Item chocolatChaud = new Item(4005, "Chocolat chaud", 4.0, "Chocolat chaud crémeux", "ChocolatChaud", Category.DRINK);
        Item jusOrange = new Item(4006, "Jus d'orange frais", 4.5, "Jus d'orange pressé frais", "JusOrange", Category.DRINK);
        Item limonade = new Item(4007, "Limonade", 3.5, "Boisson pétillante citronnée", "Limonade", Category.DRINK);
        Item eauMinerale = new Item(4008, "Eau minérale", 2.0, "Eau minérale naturelle", "EauMinerale", Category.DRINK);
        Item theMenthe = new Item(4009, "the à la menthe", 3.5, "the vert infusé à la menthe", "TheMenthe", Category.DRINK);
        Item cafeGlace = new Item(4010, "cafe glacé", 4.0, "cafe froid avec glace", "CafeGlace", Category.DRINK);






        // Associations ingrédient -> item
        cafe.addItem(cafeNoir);
        eau.addItem(cafeNoir);

        cafe.addItem(cafeAuLait);
        laitBoisson.addItem(cafeAuLait);
        eau.addItem(cafeAuLait);

        the.addItem(theVert);
        eau.addItem(theVert);

        the.addItem(theCitron);
        citronBoisson.addItem(theCitron);
        eau.addItem(theCitron);
        sucreBoisson.addItem(theCitron);

        chocolatBoisson.addItem(chocolatChaud);
        laitBoisson.addItem(chocolatChaud);
        sucreBoisson.addItem(chocolatChaud);

        citronBoisson.addItem(jusOrange);
        eau.addItem(jusOrange);

        citronBoisson.addItem(limonade);
        eau.addItem(limonade);
        sucreBoisson.addItem(limonade);

        eau.addItem(eauMinerale);

        the.addItem(theMenthe);
        menthe.addItem(theMenthe);
        eau.addItem(theMenthe);
        sucreBoisson.addItem(theMenthe);

        cafe.addItem(cafeGlace);
        glace.addItem(cafeGlace);
        sucreBoisson.addItem(cafeGlace);
        eau.addItem(cafeGlace);

// Associations item -> ingrédient
        cafeNoir.addIngredient(cafe);
        cafeNoir.addIngredient(eau);

        cafeAuLait.addIngredient(cafe);
        cafeAuLait.addIngredient(laitBoisson);
        cafeAuLait.addIngredient(eau);

        theVert.addIngredient(the);
        theVert.addIngredient(eau);

        theCitron.addIngredient(the);
        theCitron.addIngredient(citronBoisson);
        theCitron.addIngredient(eau);
        theCitron.addIngredient(sucreBoisson);

        chocolatChaud.addIngredient(chocolatBoisson);
        chocolatChaud.addIngredient(laitBoisson);
        chocolatChaud.addIngredient(sucreBoisson);

        jusOrange.addIngredient(citronBoisson);
        jusOrange.addIngredient(eau);

        limonade.addIngredient(citronBoisson);
        limonade.addIngredient(eau);
        limonade.addIngredient(sucreBoisson);

        eauMinerale.addIngredient(eau);

        theMenthe.addIngredient(the);
        theMenthe.addIngredient(menthe);
        theMenthe.addIngredient(eau);
        theMenthe.addIngredient(sucreBoisson);

        cafeGlace.addIngredient(cafe);
        cafeGlace.addIngredient(glace);
        cafeGlace.addIngredient(sucreBoisson);
        cafeGlace.addIngredient(eau);
        
        
// Associations ingrédients ↔ items
        foieGrasEntree.addIngredient(foieGras);
        foieGrasEntree.addIngredient(figue);
        foieGrasEntree.addIngredient(painBrioche);
        tartareSaumon.addIngredient(saumon);
        tartareSaumon.addIngredient(aneth);
        tartareSaumon.addIngredient(citron);
        tartareSaumon.addIngredient(huileOlive);
        oeufParfait.addIngredient(oeuf);
        oeufParfait.addIngredient(champignon);
        oeufParfait.addIngredient(creme);
        veloutePotimarron.addIngredient(potimarron);
        veloutePotimarron.addIngredient(chataigne);
        veloutePotimarron.addIngredient(creme);
        carpaccioSaintJacques.addIngredient(saintJacques);
        carpaccioSaintJacques.addIngredient(citronVert);
        terrineLapin.addIngredient(lapin);
        terrineLapin.addIngredient(noisette);
        saladeChevre.addIngredient(fromageChevre);
        saladeChevre.addIngredient(miel);
        saladeChevre.addIngredient(roquette);
        cevicheBar.addIngredient(bar);
        cevicheBar.addIngredient(citronCaviar);
        aspergesTruffe.addIngredient(asperge);
        aspergesTruffe.addIngredient(huileTruffe);
        tarteletteTomate.addIngredient(tomate);
        tarteletteTomate.addIngredient(mozzarella);

        // Associations ingrédient -> item
        chocolat.addItem(mousseChocolat);
        oeuf.addItem(mousseChocolat);
        sucre.addItem(mousseChocolat);
        beurre.addItem(mousseChocolat);

        citronDessert.addItem(tarteCitron);
        sucre.addItem(tarteCitron);
        farine.addItem(tarteCitron);
        beurre.addItem(tarteCitron);
        oeuf.addItem(tarteCitron);

        creme.addItem(cremeBrulee);
        sucre.addItem(cremeBrulee);
        oeuf.addItem(cremeBrulee);
        vanille.addItem(cremeBrulee);

        chocolat.addItem(fondantChocolat);
        oeuf.addItem(fondantChocolat);
        beurre.addItem(fondantChocolat);
        sucre.addItem(fondantChocolat);
        farine.addItem(fondantChocolat);

        fraise.addItem(saladeFruits);
        citronDessert.addItem(saladeFruits);
        sucre.addItem(saladeFruits);
        amande.addItem(saladeFruits);

// Associations item -> ingrédient
        mousseChocolat.addIngredient(chocolat);
        mousseChocolat.addIngredient(oeuf);
        mousseChocolat.addIngredient(sucre);
        mousseChocolat.addIngredient(beurre);

        tarteCitron.addIngredient(citronDessert);
        tarteCitron.addIngredient(sucre);
        tarteCitron.addIngredient(farine);
        tarteCitron.addIngredient(beurre);
        tarteCitron.addIngredient(oeuf);

        cremeBrulee.addIngredient(creme);
        cremeBrulee.addIngredient(sucre);
        cremeBrulee.addIngredient(oeuf);
        cremeBrulee.addIngredient(vanille);

        fondantChocolat.addIngredient(chocolat);
        fondantChocolat.addIngredient(oeuf);
        fondantChocolat.addIngredient(beurre);
        fondantChocolat.addIngredient(sucre);
        fondantChocolat.addIngredient(farine);

        saladeFruits.addIngredient(fraise);
        saladeFruits.addIngredient(citronDessert);
        saladeFruits.addIngredient(sucre);
        saladeFruits.addIngredient(amande);



// Lien inverse ingrédient → items
        foieGras.addItem(foieGrasEntree);
        figue.addItem(foieGrasEntree);
        painBrioche.addItem(foieGrasEntree);
        saumon.addItem(tartareSaumon);
        aneth.addItem(tartareSaumon);
        citron.addItem(tartareSaumon);
        huileOlive.addItem(tartareSaumon);
        oeuf.addItem(oeufParfait);
        champignon.addItem(oeufParfait);
        creme.addItem(oeufParfait);
        creme.addItem(veloutePotimarron);
        potimarron.addItem(veloutePotimarron);
        chataigne.addItem(veloutePotimarron);
        saintJacques.addItem(carpaccioSaintJacques);
        citronVert.addItem(carpaccioSaintJacques);
        lapin.addItem(terrineLapin);
        noisette.addItem(terrineLapin);
        fromageChevre.addItem(saladeChevre);
        miel.addItem(saladeChevre);
        roquette.addItem(saladeChevre);
        bar.addItem(cevicheBar);
        citronCaviar.addItem(cevicheBar);
        asperge.addItem(aspergesTruffe);
        huileTruffe.addItem(aspergesTruffe);
        tomate.addItem(tarteletteTomate);
        mozzarella.addItem(tarteletteTomate);



        // Bœuf Bourguignon
        boeufBourguignon.addIngredient(boeuf);
        boeufBourguignon.addIngredient(carotte);
        boeufBourguignon.addIngredient(champignon);

// Coq au vin
        coqAuVin.addIngredient(poulet);
        coqAuVin.addIngredient(champignon);

// Cassoulet
        haricots.addItem(cassoulet);
        canardConfit.addItem(cassoulet);
        cassoulet.addIngredient(haricots);
        cassoulet.addIngredient(canardConfit);

// Saumon grillé
        saumonGrille.addIngredient(saumon);
        saumonGrille.addIngredient(carotte);

// Ratatouille
        aubergine.addItem(ratatouille);
        courgette.addItem(ratatouille);
        poivron.addItem(ratatouille);
        tomate.addItem(ratatouille);
        ratatouille.addIngredient(aubergine);
        ratatouille.addIngredient(courgette);
        ratatouille.addIngredient(poivron);
        ratatouille.addIngredient(tomate);

        // Magret de canard
        fruitsRouges.addItem(magretCanard);
        magretCanard.addIngredient(fruitsRouges);
        magret.addItem(magretCanard);
        magretCanard.addIngredient(magret);

// Blanquette de veau
        veau.addItem(blanquetteVeau);
        blanquetteVeau.addIngredient(veau);
        blanquetteVeau.addIngredient(creme);
        blanquetteVeau.addIngredient(champignon);

// Filet de merlu
        merlu.addItem(filetMerlu);
        filetMerlu.addIngredient(merlu);
        filetMerlu.addIngredient(citron);

// Gratin Dauphinois
        gratinDauphinois.addIngredient(patate);
        gratinDauphinois.addIngredient(creme);
        gratinDauphinois.addIngredient(fromage);

// Tournedos Rossini
        foieGrasIngredient.addItem(tournedosRossini);
        madere.addItem(tournedosRossini);
        boeuf.addItem(tournedosRossini);
        tournedosRossini.addIngredient(boeuf);
        tournedosRossini.addIngredient(foieGrasIngredient);
        tournedosRossini.addIngredient(madere);




// Sauvegarde ingrédients (extrait)
        ingredientRepository.save(tomate);
        ingredientRepository.save(fromage);
        ingredientRepository.save(pain);
        ingredientRepository.save(beurre);
        ingredientRepository.save(lait);
        ingredientRepository.save(oeuf);
        ingredientRepository.save(chocolat);
        ingredientRepository.save(sucre);
        ingredientRepository.save(cafe);
        ingredientRepository.save(the);
        ingredientRepository.save(eau);
        ingredientRepository.save(poulet);
        ingredientRepository.save(boeuf);
        ingredientRepository.save(salade);
        ingredientRepository.save(vinaigrette);
        ingredientRepository.save(crevette);
        ingredientRepository.save(citron);
        ingredientRepository.save(saumon);
        ingredientRepository.save(creme);
        ingredientRepository.save(champignon);
        ingredientRepository.save(carotte);
        ingredientRepository.save(pomme);
        ingredientRepository.save(vanille);
        ingredientRepository.save(menthe);
        ingredientRepository.save(orange);
        ingredientRepository.save(fraise);
        ingredientRepository.save(foieGras);
        ingredientRepository.save(figue);
        ingredientRepository.save(painBrioche);
        ingredientRepository.save(aneth);
        ingredientRepository.save(huileOlive);
        ingredientRepository.save(potimarron);
        ingredientRepository.save(chataigne);
        ingredientRepository.save(saintJacques);
        ingredientRepository.save(citronVert);
        ingredientRepository.save(lapin);
        ingredientRepository.save(noisette);
        ingredientRepository.save(roquette);
        ingredientRepository.save(fromageChevre);
        ingredientRepository.save(miel);
        ingredientRepository.save(bar);
        ingredientRepository.save(citronCaviar);
        ingredientRepository.save(asperge);
        ingredientRepository.save(huileTruffe);
        ingredientRepository.save(mozzarella);
        ingredientRepository.save(patate);
        ingredientRepository.save(farine);
        ingredientRepository.save(citronDessert);
        ingredientRepository.save(amande);
        ingredientRepository.save(laitBoisson);
        ingredientRepository.save(sucreBoisson);
        ingredientRepository.save(citronBoisson);
        ingredientRepository.save(chocolatBoisson);
        ingredientRepository.save(glace);
        ingredientRepository.save(haricots);
        ingredientRepository.save(canardConfit);
        ingredientRepository.save(aubergine);
        ingredientRepository.save(courgette);
        ingredientRepository.save(poivron);
        ingredientRepository.save(fruitsRouges);
        ingredientRepository.save(magret);
        ingredientRepository.save(veau);
        ingredientRepository.save(merlu);
        ingredientRepository.save(madere);
        ingredientRepository.save(foieGrasIngredient);

        itemRepository.save(foieGrasEntree);
        itemRepository.save(tartareSaumon);
        itemRepository.save(oeufParfait);
        itemRepository.save(veloutePotimarron);
        itemRepository.save(carpaccioSaintJacques);
        itemRepository.save(terrineLapin);
        itemRepository.save(saladeChevre);
        itemRepository.save(cevicheBar);
        itemRepository.save(aspergesTruffe);
        itemRepository.save(tarteletteTomate);

        itemRepository.save(boeufBourguignon);
        itemRepository.save(coqAuVin);
        itemRepository.save(cassoulet);
        itemRepository.save(saumonGrille);
        itemRepository.save(ratatouille);
        itemRepository.save(magretCanard);
        itemRepository.save(blanquetteVeau);
        itemRepository.save(filetMerlu);
        itemRepository.save(gratinDauphinois);
        itemRepository.save(tournedosRossini);

        itemRepository.save(mousseChocolat);
        itemRepository.save(tarteCitron);
        itemRepository.save(cremeBrulee);
        itemRepository.save(fondantChocolat);
        itemRepository.save(saladeFruits);

        itemRepository.save(cafeNoir);
        itemRepository.save(cafeAuLait);
        itemRepository.save(theVert);
        itemRepository.save(theCitron);
        itemRepository.save(chocolatChaud);
        itemRepository.save(jusOrange);
        itemRepository.save(limonade);
        itemRepository.save(eauMinerale);
        itemRepository.save(theMenthe);
        itemRepository.save(cafeGlace);


    }

    public void testInsert4(){
        Employee employee1 = new Employee("admin@gmail.com","1234","Michelle","Brounda",true, Role.ADMIN);
        Employee employee2 = new Employee("employe@gmail.com","1234","Sophie","Cholet",true, Role.EMPLOYEE);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
    }



    @Override
    public void run(String... args) throws Exception {
        insertMenu(); // insert menu
        testInsert1(); // insert customers et addresses
        testInsert2(); //insert commandes et reservations
        testInsert4(); // insert employées

    }
}