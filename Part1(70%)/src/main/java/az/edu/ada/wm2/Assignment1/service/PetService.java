package az.edu.ada.wm2.Assignment1.service;


import az.edu.ada.wm2.Assignment1.model.Pets;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PetService {
    Page<Pets> list(int pageNo);
    Pets save(Pets pets);
    Pets getById(Long id);
    void deleteById(Long id);
}
