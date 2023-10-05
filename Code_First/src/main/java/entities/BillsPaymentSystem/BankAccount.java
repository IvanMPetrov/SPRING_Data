package entities.BillsPaymentSystem;


import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;


    @Column(name = "bank_name", nullable = false)
    private String bankName;


    @Column(name = "swift_code", nullable = false)
    private String swiftCode;

    @OneToOne
    @JoinColumn(name = "billing_details_id")
    private BillingDetails billingDetails;

    public BankAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }
}
