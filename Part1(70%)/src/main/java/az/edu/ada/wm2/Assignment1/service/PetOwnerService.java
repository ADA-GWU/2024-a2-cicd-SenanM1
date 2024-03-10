package az.edu.ada.wm2.Assignment1.service;

import az.edu.ada.wm2.Assignment1.model.PetOwner;
import az.edu.ada.wm2.Assignment1.model.Pets;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PetOwnerService {

    Page<PetOwner> list(int pageNo);

    PetOwner save(PetOwner petOwner);

    PetOwner getById(Long id);

    void deleteById(Long id);

    List<PetOwner> getPetOwnerByNamesAnd(String firstName, String lastName);

    List<PetOwner> getPetOwnerByNamesOr(String firstName, String lastName);

    List<Pets> getPetsByPetOwnerId(Long id);

    List<Pets>  getPetsByPetOwnerIdNot(Long id);

    List<Pets> getAllPets();
}
