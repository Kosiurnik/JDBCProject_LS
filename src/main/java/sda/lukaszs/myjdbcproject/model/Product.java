package sda.lukaszs.myjdbcproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition="VARCHAR(100) DEFAULT ''")
    private String name;

    @Column(name = "price", nullable = false, columnDefinition="DECIMAL(11,2) DEFAULT 0")
    private BigDecimal price;

    public void setPrice(double price) {
        this.price = new BigDecimal(price);
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%d;", id, name, price);
    }
}
