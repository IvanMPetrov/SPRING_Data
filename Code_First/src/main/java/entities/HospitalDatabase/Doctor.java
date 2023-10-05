package entities.HospitalDatabase;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;
}
