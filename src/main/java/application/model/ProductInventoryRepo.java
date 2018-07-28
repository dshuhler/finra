package application.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductInventoryRepo extends CrudRepository<ProductInventory, String> {

    Optional<ProductInventory> findByProductId(String productId);

}
