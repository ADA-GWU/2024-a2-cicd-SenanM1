package az.edu.ada.wm2.Assignment1.service.impl;

import az.edu.ada.wm2.Assignment1.model.Pets;
import az.edu.ada.wm2.Assignment1.repository.PetOwnerRepostiory;
import az.edu.ada.wm2.Assignment1.repository.PetRepository;
import az.edu.ada.wm2.Assignment1.service.PetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    PetOwnerRepostiory petOwnerRepo;

    PetRepository petRepo;

    public PetServiceImpl(PetOwnerRepostiory petOwnerRepo, PetRepository petRepo){
        this.petOwnerRepo = petOwnerRepo;
        this.petRepo = petRepo;
    }

    @Override
    public Page<Pets> list(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return petRepo.findAll(pageable);
    }

    @Override
    public Pets save(Pets pets) {
        return petRepo.save(pets);
    }

    @Override
    public Pets getById(Long id) {
        return petRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        petRepo.deleteById(id);
    }
}
