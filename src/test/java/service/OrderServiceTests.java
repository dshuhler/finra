package service;

import application.model.ProductInventory;
import application.model.ProductInventoryRepo;
import application.service.CreditCardService;
import application.service.OrderService;
import application.web.dto.CreditCardPaymentDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTests {

    private ProductInventoryRepo mockProductInventoryRepo = mock(ProductInventoryRepo.class);
    private CreditCardService mockCreditCardService = mock(CreditCardService.class);

    @Test
    public void executesHappyPath() {
        ProductInventory testInventoryItem = new ProductInventory("A-1", 200, BigDecimal.valueOf(100));
        when(mockProductInventoryRepo.findByProductId("A-1")).thenReturn(Optional.of(testInventoryItem));

        when(mockCreditCardService.chargeCreditCard(new CreditCardPaymentDto("111-222-3333", BigDecimal.valueOf(100))))
                .thenReturn(Boolean.TRUE);

        OrderService orderService = new OrderService(mockProductInventoryRepo, mockCreditCardService);
        assertEquals("Order successful", orderService.executeOrder("A-1", "111-222-3333"));
    }

    @Test
    public void failsIfProductIdNotFound() {
        when(mockProductInventoryRepo.findByProductId(any())).thenReturn(Optional.empty());

        OrderService orderService = new OrderService(mockProductInventoryRepo, mockCreditCardService);
        assertEquals("No product found with product ID = A-1", orderService.executeOrder("A-1", "111-222-3333"));
    }

    @Test
    public void failsIfProductNotInStock() {
        ProductInventory testInventoryItem = new ProductInventory("A-1", 0, BigDecimal.valueOf(100));
        when(mockProductInventoryRepo.findByProductId("A-1")).thenReturn(Optional.of(testInventoryItem));

        OrderService orderService = new OrderService(mockProductInventoryRepo, mockCreditCardService);
        assertEquals("No product in stock", orderService.executeOrder("A-1", "111-222-3333"));
    }

    @Test
    public void failsIfCreditCardChargeFails() {
        ProductInventory testInventoryItem = new ProductInventory("A-1", 200, BigDecimal.valueOf(100));
        when(mockProductInventoryRepo.findByProductId("A-1")).thenReturn(Optional.of(testInventoryItem));

        when(mockCreditCardService.chargeCreditCard(new CreditCardPaymentDto("111-222-3333", BigDecimal.valueOf(100))))
                .thenReturn(Boolean.FALSE);

        OrderService orderService = new OrderService(mockProductInventoryRepo, mockCreditCardService);
        assertEquals("Credit card charge failed", orderService.executeOrder("A-1", "111-222-3333"));
    }

}
