package springboot.projetfinal.controller.mvc;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springboot.projetfinal.model.Address;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.repo.CustomerRepository;
import springboot.projetfinal.repo.ItemRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mvcCustomers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    // AFFICHER TOUS LES CLIENTS
    @GetMapping("/findall")
    public ModelAndView findAll() {
        return new ModelAndView("customer/findAll", "liste", customerRepository.findAll());
    }
    // AFFICHER LES DETAILS DE UN CLIENT
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID client invalide : " + id));

        ModelAndView mv = new ModelAndView("customer/detail");
        mv.addObject("customer", customer);
        return mv;
    }

    // AFFICHER FORM CREATE
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView mv = new ModelAndView("customer/create");
        mv.addObject("customer", new Customer());
        mv.addObject("items", itemRepository.findAll()); // Pour checkbox favoris
        return mv;
    }
    // TRAITER CREATE
    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         @RequestParam(value="address.street", required=false) String street,
                         @RequestParam(value="address.city", required=false) String city,
                         @RequestParam(value="address.postalCode", required=false) String postalCode
    ) throws IOException {

        if (!imageFile.isEmpty()) {
            String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + "." + extension;
            Path uploadPath = Paths.get("src/main/webapp/images");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            imageFile.transferTo(uploadPath.resolve(filename));
            customer.setPhoto(filename);
        }

        // Création adresse si au moins un champ rempli
        if ((street != null && !street.trim().isEmpty())
                || (city != null && !city.trim().isEmpty())
                || (postalCode != null && !postalCode.trim().isEmpty())) {
            Address address = new Address();
            address.setStreet(street);
            address.setCity(city);
            address.setPostalCode(postalCode);
            address.setCustomer(customer); // Lien bidirectionnel si besoin
            customer.getAddresses().add(address);
        }

        customerRepository.save(customer);
        return "redirect:/mvcCustomers/findall";
    }

    // AFFICHER FORM UPDATE
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));

        ModelAndView mv = new ModelAndView("customer/update");
        mv.addObject("customer", customer);
        mv.addObject("items", itemRepository.findAll()); // Pour checkbox favoris
        return mv;
    }

    // POST UPDATE
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute Customer customer,
                         @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                         @RequestParam(value = "addresses[].id", required = false) List<Integer> addressIds,
                         @RequestParam(value = "addresses[].street", required = false) List<String> streets,
                         @RequestParam(value = "addresses[].city", required = false) List<String> cities,
                         @RequestParam(value = "addresses[].postalCode", required = false) List<String> postalCodes,
                         @RequestParam(value = "addresses[].delete", required = false) List<String> deletes,
                         @RequestParam(value = "newAddress.street", required = false) String newStreet,
                         @RequestParam(value = "newAddress.city", required = false) String newCity,
                         @RequestParam(value = "newAddress.postalCode", required = false) String newPostalCode
    ) throws IOException {

        customer.setId(id);

        if (imageFile != null && !imageFile.isEmpty()) {
            String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + "." + extension;
            Path uploadPath = Paths.get("src/main/webapp/images/customers");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            imageFile.transferTo(uploadPath.resolve(filename));
            customer.setPhoto(filename);
        } else {
            Customer oldCustomer = customerRepository.findById(id).orElseThrow();
            customer.setPhoto(oldCustomer.getPhoto());
        }

        // Gestion des adresses
        List<Address> updatedAddresses = new ArrayList<>();

        if (addressIds != null) {
            for (int i = 0; i < addressIds.size(); i++) {
                Integer addrId = addressIds.get(i);
                String street = streets.get(i);
                String city = cities.get(i);
                String postalCode = postalCodes.get(i);

                // Vérifie si l'adresse a été cochée pour suppression
                boolean toDelete = deletes != null && deletes.contains(addrId.toString());

                if (!toDelete) {
                    Address addr = new Address();
                    addr.setId(addrId);
                    addr.setStreet(street);
                    addr.setCity(city);
                    addr.setPostalCode(postalCode);
                    addr.setCustomer(customer); // Assure la relation bidirectionnelle si besoin
                    updatedAddresses.add(addr);
                }
            }
        }

        // Ajout de la nouvelle adresse si renseignée (au moins une info non vide)
        if ((newStreet != null && !newStreet.trim().isEmpty()) ||
                (newCity != null && !newCity.trim().isEmpty()) ||
                (newPostalCode != null && !newPostalCode.trim().isEmpty())) {

            Address newAddress = new Address();
            newAddress.setStreet(newStreet);
            newAddress.setCity(newCity);
            newAddress.setPostalCode(newPostalCode);
            newAddress.setCustomer(customer);
            updatedAddresses.add(newAddress);
        }

        customer.setAddresses(updatedAddresses);

        customerRepository.save(customer);

        return "redirect:/mvcCustomers/findall";
    }


    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/mvcCustomers/findall";
    }
}
