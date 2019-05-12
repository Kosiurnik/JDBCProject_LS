package sda.lukaszs.myjdbcproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="customer_details")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @OneToOne(mappedBy = "details", fetch = FetchType.LAZY)
    Customer customer;

    @Column(name = "address", columnDefinition="VARCHAR(150) DEFAULT ''")
    private String address;

    @Column(name = "postal_code", columnDefinition="VARCHAR(10) DEFAULT ''")
    private String postalCode;

    @Column(name = "city", columnDefinition="VARCHAR(50) DEFAULT ''")
    private String city;

    @Column(name = "phone_number", columnDefinition="VARCHAR(20) DEFAULT ''")
    private String phoneNumber;

    public CustomerDetails(String address, String postalCode, String city, String phoneNumber) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
