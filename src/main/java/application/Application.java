package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application {

    @Autowired
    ProductInventoryRepo productInventoryRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        productInventoryRepo.save(new ProductInventory("A-1", 2));
        productInventoryRepo.save(new ProductInventory("A-2", 20));
        productInventoryRepo.save(new ProductInventory("A-3", 0));
        productInventoryRepo.save(new ProductInventory("B-1", 200));
        productInventoryRepo.save(new ProductInventory("B-2", 25));

        System.out.println("hello world, I have just started up");
    }
}
