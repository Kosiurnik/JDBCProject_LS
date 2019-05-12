package sda.lukaszs.myjdbcproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shoppingorder_product_alloc")
@AssociationOverrides({
        @AssociationOverride(name = "pk.shoppingOrder",
                joinColumns = @JoinColumn(name = "shoppingorder_id")),
        @AssociationOverride(name = "pk.product",
                joinColumns = @JoinColumn(name = "product_id")) })
@NoArgsConstructor
public class ShoppingOrderProductAlloc {

    @EmbeddedId
    private ShoppingOrderProductPK pk = new ShoppingOrderProductPK();

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ShoppingOrderProductAlloc(Product product, int quantity) {
        getPk().setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct(){
        return pk.getProduct();
    }
    public void setProduct(Product product){pk.setProduct(product);}

    @Transient
    public ShoppingOrder getShoppingOrder(){
        return pk.getShoppingOrder();
    }
    public void setShoppingOrder(ShoppingOrder order){pk.setShoppingOrder(order);}
}
