package az.edu.ada.wm2.Assignment1.service.impl;

import az.edu.ada.wm2.Assignment1.model.PetOwner;
import az.edu.ada.wm2.Assignment1.model.Pets;
import az.edu.ada.wm2.Assignment1.repository.PetOwnerRepostiory;
import az.edu.ada.wm2.Assignment1.repository.PetRepository;
import az.edu.ada.wm2.Assignment1.service.PetOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetOwnerServiceImpl implements PetOwnerService {
    PetOwnerRepostiory petOwnerRepo;

    PetRepository petRepo;

    public PetOwnerServiceImpl(PetOwnerRepostiory petOwnerRepo, PetRepository petRepo){
        this.petOwnerRepo = petOwnerRepo;
        this.petRepo = petRepo;
    }


    @Override
    public Page<PetOwner> list(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return petOwnerRepo.findAll(pageable);
    }

    @Override
    public PetOwner save(PetOwner petOwner) {
        return petOwnerRepo.save(petOwner);
    }

    @Override
    public PetOwner getById(Long id) {
        return petOwnerRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        petOwnerRepo.deleteById(id);
    }


    @Override
    public List<PetOwner> getPetOwnerByNamesAnd(String firstName, String lastName) {
        return (List<PetOwner>) petOwnerRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<PetOwner> getPetOwnerByNamesOr(String firstName, String lastName) {
        return (List<PetOwner>) petOwnerRepo.findByFirstNameOrLastName(firstName, lastName);
    }

    @Override
    public List<Pets> getPetsByPetOwnerId(Long id) {
        return (List<Pets>) petRepo.findByPetOwnersId(id);
    }

    @Override
    public List<Pets> getPetsByPetOwnerIdNot(Long id) {
        return (List<Pets>) petRepo.findByPetOwnersIdNot(id);
    }

    @Override
    public List<Pets> getAllPets() {
        return (List<Pets>) petRepo.findAll();
    }

}
