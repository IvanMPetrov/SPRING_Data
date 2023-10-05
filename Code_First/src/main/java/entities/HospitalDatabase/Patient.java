package entities.HospitalDatabase;


import javax.persistence.*;
import java.awt.*;
import java.awt.font.ImageGraphicAttribute;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String picture;

    @Column(name = "active_medical_insurance",nullable = false)
    private boolean isActiveMedicalInsurance;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Doctor doctor;


    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;


}
