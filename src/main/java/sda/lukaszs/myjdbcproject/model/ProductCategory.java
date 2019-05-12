package sda.lukaszs.myjdbcproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="product_category")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition="VARCHAR(100) DEFAULT ''")
    private String name;

    @OneToMany(mappedBy="productCategory")
    private List<Product> products;

    public ProductCategory(String name){
        this.name = name;
        this.products = new ArrayList<>();
    }
}
