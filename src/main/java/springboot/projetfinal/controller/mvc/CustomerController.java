package springboot.projetfinal.controller.mvc;

import jakarta.persistence.OptimisticLockException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    // DETAIL
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID client invalide : " + id));
        return new ModelAndView("customer/detail", "customer", customer);
    }

    // FORM CREATE
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        Customer customer = new Customer();
        customer.addAddress(new Address()); // Une seule adresse pour commencer
        ModelAndView mv = new ModelAndView("customer/create");
        mv.addObject("customer", customer);
        mv.addObject("items", itemRepository.findAll());
        return mv;
    }

    // POST CREATE
    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer,
                         @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {
        if (!imageFile.isEmpty()) {
            String ext = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + "." + ext;
            Path uploadPath = Paths.get("src/main/webapp/images");
            Files.createDirectories(uploadPath);
            imageFile.transferTo(uploadPath.resolve(filename));
            customer.setPhoto(filename);
        }

        for (Address address : customer.getAddresses()) {
            address.setCustomer(customer);
        }

        customerRepository.save(customer);
        return "redirect:/mvcCustomers/findall";
    }

    // FORM UPDATE
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID client invalide : " + id));

        if (customer.getAddresses().isEmpty()) {
            customer.addAddress(new Address());
        }

        ModelAndView mv = new ModelAndView("customer/update");
        mv.addObject("customer", customer);
        return mv;
    }

    // POST UPDATE
    @PostMapping("/update")
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes redirectAttributes) {

        try {
            Customer existingCustomer = customerRepository.findById(customer.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

            // --- Infos de base ---
            existingCustomer.setLastname(customer.getLastname());
            existingCustomer.setFirstname(customer.getFirstname());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setVersion(customer.getVersion());

            // --- Photo ---
            if (!imageFile.isEmpty()) {
                String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path imagePath = Path.of("src/main/resources/static/images/customers", filename);
                Files.copy(imageFile.getInputStream(), imagePath);
                existingCustomer.setPhoto(filename);
            }

            // --- Mise à jour des adresses ---
            if (customer.getAddresses() != null) {
                for (Address updatedAddr : customer.getAddresses()) {
                    if (updatedAddr != null) {
                        // On cherche l'adresse existante correspondante par ID
                        for (Address existingAddr : existingCustomer.getAddresses()) {
                            if (existingAddr.getId()==(updatedAddr.getId())) {
                                existingAddr.setStreet(updatedAddr.getStreet());
                                existingAddr.setStreetNumber(updatedAddr.getStreetNumber());
                                existingAddr.setCity(updatedAddr.getCity());
                                existingAddr.setPostalCode(updatedAddr.getPostalCode());
                                break;
                            }
                        }
                    }
                }
            }

            customerRepository.save(existingCustomer);
            redirectAttributes.addFlashAttribute("success", "Client mis à jour avec succès.");
        } catch (OptimisticLockException e) {
            redirectAttributes.addFlashAttribute("error", "Conflit de version : le client a été modifié par un autre utilisateur.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour du client : " + e.getMessage());
        }

        return "redirect:/mvcCustomers/findall";
    }



    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/mvcCustomers/findall";
    }
}
