package entities.SalesDatabase;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "location_name")
    private String locationName;


    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
