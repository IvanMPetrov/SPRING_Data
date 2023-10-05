package entities.BillsPaymentSystem;


import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.util.Date;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;


    @Column(name = "card_type",nullable = false)
    private String cardType;

    @Column(name = "expiration_month",nullable = false)
    private Month expirationMonth;


    @Column(name = "expiration_year", nullable = false)
    private Year expirationYear;

    @OneToOne
    @JoinColumn(name = "billing_details_id")
    private BillingDetails billingDetails;

    public CreditCard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }
}
