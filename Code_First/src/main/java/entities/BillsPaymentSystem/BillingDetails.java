package entities.BillsPaymentSystem;

import javax.persistence.*;

@Entity
public  class  BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String owner;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "billingDetails")
    private CreditCard creditCard;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "billingDetails")
    private BankAccount bankAccount;

    public BillingDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
