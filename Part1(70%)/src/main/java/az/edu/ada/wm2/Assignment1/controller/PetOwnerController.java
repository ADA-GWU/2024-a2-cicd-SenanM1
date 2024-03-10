package az.edu.ada.wm2.Assignment1.controller;

import az.edu.ada.wm2.Assignment1.model.PetOwner;
import az.edu.ada.wm2.Assignment1.model.Pets;
import az.edu.ada.wm2.Assignment1.service.PetOwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owner/")
public class PetOwnerController {
    static final Logger LOGGER = LoggerFactory.getLogger(PetOwnerController.class);

    PetOwnerService petOwnerService;
    public PetOwnerController(PetOwnerService petOwnerService){
        this.petOwnerService = petOwnerService;
    }

    @GetMapping({"/","","/list"})
    public String getPetOwners(Model model){
        return getPetOwnersByPageNo(model, 1);
    }

    @GetMapping("/page/{no}")
    public String getPetOwnersByPageNo(Model model, @PathVariable("no") Integer pageNo) {
        Page<PetOwner> petOwnersPage = petOwnerService.list(pageNo);

        model.addAttribute("petOwners", petOwnersPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", petOwnersPage.getTotalPages());
        model.addAttribute("nbElements", petOwnersPage.getNumberOfElements());
        model.addAttribute("totalElements", petOwnersPage.getTotalElements());

        return "PetOwner/home";
    }

    @GetMapping({"/{id}","/{id}/"})
    public String getPetOwner(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("petOwners", petOwnerService.getById(id));
        return "PetOwner/info";
    }

    @GetMapping("/{id}/availablePets")
    public String availablePetsPage(Model model, @PathVariable Long id) {

        PetOwner owners = petOwnerService.getById(id);
        model.addAttribute("petOwners", owners);

        List<Pets> allPets = petOwnerService.getPetsByPetOwnerIdNot(id);
        model.addAttribute("pets", allPets);
        return "PetOwner/availablePets";
    }

    @GetMapping("/new")
    public String createNewPetOwner(Model model){
        model.addAttribute("owner", new PetOwner());

        List<Pets> allPets = petOwnerService.getAllPets();
        model.addAttribute("pets", allPets);

        LOGGER.info("createNewPetOwner()");
        return "PetOwner/newPetOwner";
    }

    @PostMapping("/")
    public String save(@ModelAttribute("owner") PetOwner owner){
        petOwnerService.save(owner);
        return "redirect:/owner/page/1";
    }

    @GetMapping("/update/{id}")
    public String updateTheOwner(Model model, @PathVariable Long id){
        model.addAttribute("owner", petOwnerService.getById(id));

        List<Pets> allPets = petOwnerService.getPetsByPetOwnerId(id);
        model.addAttribute("pets", allPets);

        return "PetOwner/update";
    }

    @GetMapping("/delete/{id}")
    public String deleteTheOwner(@PathVariable Long id) {
        petOwnerService.deleteById(id);
        return "redirect:/owner/page/1";
    }


    @GetMapping("/and/{firstName}/{lastName}")
    public String getOwnerByNameAnd(Model model, @PathVariable String firstName, @PathVariable String lastName) {

        var owner = petOwnerService.getPetOwnerByNamesAnd(firstName, lastName);

        model.addAttribute("petOwners", owner);

        return "PetOwner/home";
    }

    @GetMapping("/or/{firstName}/{lastName}")
    public String getOwnerByNameOr(Model model, @PathVariable String firstName, @PathVariable String lastName) {

        var owner = petOwnerService.getPetOwnerByNamesOr(firstName, lastName);

        model.addAttribute("petOwners", owner);

        return "PetOwner/home";
    }
}
