package sda.lukaszs.myjdbcproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="shoppingorder")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false, foreignKey=@ForeignKey(name = "FK_shoppingorder_customer"))
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.shoppingOrder", cascade=CascadeType.ALL)
    private List<ShoppingOrderProductAlloc> shoppingOrderProducts = new ArrayList<>();

    @Column(name = "order_date", nullable = false, columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    @PrePersist
    public void prePersist(){
        this.orderDate = LocalDateTime.now();
    }

    public void addProduct(Product product, int quantity) {
        ShoppingOrderProductAlloc alloc = new ShoppingOrderProductAlloc(product,quantity);
        alloc.getPk().setShoppingOrder(this);
        shoppingOrderProducts.add(alloc);
    }
}
