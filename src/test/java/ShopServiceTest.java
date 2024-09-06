import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")));
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    //Added Test for invalid ProductId
    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        //THEN
        assertThrows(ProductNotFoundException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersByStatusTest_whenListIsEmpty_expectEmptyList() {
        //GIVEN
        ShopService shopService = new ShopService();
        OrderStatus status = OrderStatus.PROCESSING;

        //WHEN
        List<Order> actual = shopService.getOrdersByStatus(status);

        //THEN
        assertEquals(0, actual.size());
    }

    @Test
    void updateOrderTest_() {
    }
}
