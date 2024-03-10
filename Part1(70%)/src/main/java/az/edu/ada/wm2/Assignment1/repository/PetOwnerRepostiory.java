package az.edu.ada.wm2.Assignment1.repository;

import az.edu.ada.wm2.Assignment1.model.PetOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetOwnerRepostiory extends JpaRepository<PetOwner, Long> {
    Page<PetOwner> findAll(Pageable pageable);
    @Query("select a from PetOwner a where a.firstName =?1 and a.lastName=?2")
    Iterable<PetOwner> findByFirstNameAndLastName(String firstName, String lastName);

    Iterable<PetOwner> findByFirstNameOrLastName(String firstName, String lastName);
}
