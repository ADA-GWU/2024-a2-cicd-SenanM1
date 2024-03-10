package az.edu.ada.wm2.Assignment1.repository;


import az.edu.ada.wm2.Assignment1.model.PetOwner;
import az.edu.ada.wm2.Assignment1.model.Pets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pets, Long> {
    Page<Pets> findAll(Pageable pageable);
    @Query("select c from Pets c where c in " +
            "(select c from Pets c left join c.petOwners petOwner where petOwner.id = :id)")
    Iterable<Pets> findByPetOwnersId(Long id);

    @Query("select c from Pets c where c not in " +
            "(select c from Pets c left join c.petOwners petOwner where petOwner.id = :id)")
    Iterable<Pets> findByPetOwnersIdNot(Long id);

}
