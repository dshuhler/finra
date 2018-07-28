package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    @Autowired
    ProductInventoryRepo productInventoryRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateInMemoryDatabase() {
        productInventoryRepo.save(new ProductInventory("A-1", 200, BigDecimal.valueOf(100)));
        productInventoryRepo.save(new ProductInventory("A-2", 0, BigDecimal.valueOf(100)));
    }
}
