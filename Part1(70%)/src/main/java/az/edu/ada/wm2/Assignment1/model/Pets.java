package az.edu.ada.wm2.Assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Pets")
public class Pets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String petType;
    private String petBreed;

    @ManyToMany(mappedBy = "pets")
    private Set<PetOwner> petOwners = new HashSet<>();

    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", petType='" + petType + '\'' +
                ", petBreed='" + petBreed + '\'' +
                ", petOwners=" + petOwners +
                '}';
    }
}
