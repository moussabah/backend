package springboot.projetfinal.controller.mvc;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springboot.projetfinal.enums.Category;
import springboot.projetfinal.model.Ingredient;
import springboot.projetfinal.model.Item;
import springboot.projetfinal.model.Reservation;
import springboot.projetfinal.repo.IngredientRepository;
import springboot.projetfinal.repo.ItemRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mvcItems")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private IngredientRepository ingredientRepository;


    @RequestMapping("/findall")
    public ModelAndView findall() {

        ModelAndView modelAndView = new ModelAndView("item/findAll", "liste", itemRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/detail/{ref}")
    public ModelAndView detail(@PathVariable int ref) {
        Item item = itemRepository.findById(ref).orElseThrow();
        return new ModelAndView("item/detail", "item", item);
    }

    // AFFICHER FORMULAIRE CREATE
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView mv = new ModelAndView("item/create");
        mv.addObject("item", new Item());
        mv.addObject("ingredients", ingredientRepository.findAll());
        mv.addObject("categories", Arrays.asList(Category.values()));
        return mv;
    }

    // TRAITER CREATE

    @PostMapping("/create")
    public String createItem(@ModelAttribute Item item,
                             @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + "." + extension;
            Path uploadPath = Paths.get("src/main/webapp/images");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            imageFile.transferTo(uploadPath.resolve(filename));
            item.setPathImg(filename);
        }
        itemRepository.save(item);
        return "redirect:/mvcItems/findall";
    }


    // AFFICHER FORMULAIRE UPDATE
    @GetMapping("/update/{ref}")
    public ModelAndView showUpdateForm(@PathVariable("ref") int ref) {
        Item item = itemRepository.findById(ref)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ref: " + ref));

        ModelAndView mv = new ModelAndView("item/update");
        System.out.println("je suis dans le Get et item.path = "+item.getPathImg());
        mv.addObject("item", item);
        mv.addObject("ingredients", ingredientRepository.findAll());
        mv.addObject("categories", Arrays.asList(Category.values()));
        return mv;
    }

    // TRAITER UPDATE avec ingredients et categories

    @PostMapping("/update/{ref}")
    public String update(@PathVariable("ref") int ref,
                         @ModelAttribute Item item,
                         @RequestParam(value = "ingredientIds", required = false) List<Integer> ingredientIds,
                         @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        // S'assurer que la ref est correcte
        item.setRef(ref);

        // Gestion des ingrédients
        if (ingredientIds != null) {
            List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIds);
            item.setIngredients(ingredients);
        } else {
            item.getIngredients().clear();
        }

        System.out.println("Path = "+item.getPathImg());

        // Gestion upload image
        if (imageFile != null && !imageFile.isEmpty()) {
            System.out.println("JE rentre ici car j'ai selectionné une nouvelle image !!!! ");

                String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
                String filename = UUID.randomUUID().toString() + "." + extension;
                Path uploadPath = Paths.get("src/main/webapp/images");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                imageFile.transferTo(uploadPath.resolve(filename));
                item.setPathImg(filename);

        }else   System.out.println("JE rentre ici car je n'ai rien selectionée !!!! ");


        itemRepository.save(item);
        return "redirect:/mvcItems/findall";
    }


    // DELETE
    @GetMapping("/delete/{ref}")
    public String delete(@PathVariable("ref") int ref) {
        itemRepository.deleteById(ref);
        return "redirect:/mvcItems/findall";
    }
}
