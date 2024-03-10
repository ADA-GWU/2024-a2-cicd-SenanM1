package az.edu.ada.wm2.Assignment1.controller;

import az.edu.ada.wm2.Assignment1.model.Pets;
import az.edu.ada.wm2.Assignment1.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.element.ModuleElement;

@Controller
@RequestMapping("/pets/")
public class PetController {
    static final Logger LOGGER = LoggerFactory.getLogger(PetController.class);
    PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
    }

    @GetMapping({"", "/", "/list"})
    public String getPets(Model model) {
        return getPetsByPageNo(model, 1);
    }

    @GetMapping("/page/{no}")
    public String getPetsByPageNo(Model model, @PathVariable("no") Integer pageNo) {
        Page<Pets> petsPage = petService.list(pageNo);

        model.addAttribute("pets", petsPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", petsPage.getTotalPages());
        model.addAttribute("nbElements", petsPage.getNumberOfElements());
        model.addAttribute("totalElements", petsPage.getTotalElements());

        return "Pets/home";
    }

    @GetMapping("/new")
    public String getNewPet(Model model){
        model.addAttribute("pets", new Pets());
        LOGGER.info("getNewPet()");
        return "Pets/newPet";
    }

    @PostMapping("/")
    public String save(@ModelAttribute("pets") Pets pets) {
        petService.save(pets);
        return "redirect:/pets/";
    }

    @GetMapping("/{id}")
    public String getPet(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("pets", petService.getById(id));
        return "Pets/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        petService.deleteById(id);
        return "redirect:/pets/";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updatePet(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pets/update");

        mv.addObject("pets", petService.getById(id));
        return mv;
    }
}
