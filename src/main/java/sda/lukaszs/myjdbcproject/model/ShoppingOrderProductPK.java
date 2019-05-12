package sda.lukaszs.myjdbcproject.model;

import lombok.Data;

import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
public class ShoppingOrderProductPK implements Serializable {

    @ManyToOne
    private ShoppingOrder shoppingOrder;
    @ManyToOne
    private Product product;
}
