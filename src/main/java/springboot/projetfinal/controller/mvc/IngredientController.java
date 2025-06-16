package springboot.projetfinal.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springboot.projetfinal.model.Ingredient;
import springboot.projetfinal.repo.IngredientRepository;



@Controller
@RequestMapping("/mvcIngredients")
public class IngredientController {



    @Autowired
    private IngredientRepository repository;


    @RequestMapping("/findall")
    public ModelAndView findall() {

        ModelAndView modelAndView = new ModelAndView("ingredient/findAll", "liste", repository.findAll());
        return modelAndView;
    }
    // AFFICHER FORMULAIRE CREATE
    @GetMapping("/create")
    public ModelAndView showCreateForm(@RequestParam(value = "returnTo", required = false) String returnTo) {
        ModelAndView mv = new ModelAndView("ingredient/create");
        mv.addObject("ingredient", new Ingredient());
        mv.addObject("returnTo", returnTo);
        return mv;
    }

    // TRAITER CREATE
    @PostMapping("/create")
    public String create(@ModelAttribute Ingredient ingredient,
                         @RequestParam(value = "returnTo", required = false) String returnTo) {
        repository.save(ingredient);
        if (returnTo != null && !returnTo.isEmpty()) {
            return "redirect:" + returnTo;
        }
        return "redirect:/mvcIngredients/findall";
    }

    // AFFICHER FORMULAIRE UPDATE
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") int id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient id: " + id));
        return new ModelAndView("ingredient/update", "ingredient", ingredient);
    }

    // TRAITER UPDATE
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute  Ingredient ingredient) {
        // s'assurer que l'id est correct
        ingredient.setId(id);
        repository.save(ingredient);
        return "redirect:/mvcIngredients/findall";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/mvcIngredients/findall";
    }
}
