package sda.lukaszs.myjdbcproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "first_name", nullable = false, columnDefinition="VARCHAR(50) DEFAULT ''")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition="VARCHAR(50) DEFAULT ''")
    private String lastName;

    @Column(name = "address", columnDefinition="VARCHAR(150) DEFAULT ''")
    private String address;

    @Column(name = "postal_code", columnDefinition="VARCHAR(10) DEFAULT ''")
    private String postalCode;

    @Column(name = "city", columnDefinition="VARCHAR(50) DEFAULT ''")
    private String city;

    @Column(name = "phone_number", columnDefinition="VARCHAR(20) DEFAULT ''")
    private String phoneNumber;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = "";
        this.postalCode = "";
        this.city = "";
        this.phoneNumber = "";
    }
}
