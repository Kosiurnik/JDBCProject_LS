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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_details_id", foreignKey=@ForeignKey(name = "FK_customer_customerdetails"))
    CustomerDetails details;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
