package entities.HospitalDatabase;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnose")
public class Diagnose {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String comment;

    @OneToOne
    private Visitation visitation;

    @OneToMany(mappedBy = "diagnose")
    private Set<Medicament> medicaments;


}
