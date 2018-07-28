package application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProductInventory {

    @Id
    @GeneratedValue
    private long id;
    private String productId;
    private int quantity;

    //this should definitely be in a different table but putting it here for simplicity sake
    private BigDecimal price;


    public ProductInventory() {
    }

    public ProductInventory(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
