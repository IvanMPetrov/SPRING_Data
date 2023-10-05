package entities.HospitalDatabase;

import javax.persistence.*;

@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "diagnose_id",referencedColumnName = "id")
    private Diagnose diagnose;
}
