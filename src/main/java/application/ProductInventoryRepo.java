package application;

import org.springframework.data.repository.CrudRepository;

public interface ProductInventoryRepo extends CrudRepository<ProductInventory, String> {}
