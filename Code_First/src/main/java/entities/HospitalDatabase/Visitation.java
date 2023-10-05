package entities.HospitalDatabase;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visitation")
public class Visitation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "date_of_visit",nullable = false)
    private Date dateOfVisit;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @OneToOne(mappedBy = "visitation")
    private Diagnose diagnose;


}
