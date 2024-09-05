import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

//Modify the 'addOrder' method in the ShopService so that an exception is thrown if the product does not exist.
//In the 'addOrder' method, fill this field with the current timestamp.

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
            products.add(productToOrder);
        }

//    public Order addOrder(List<String> productIds) {
//        List<Product> products = new ArrayList<>();
//        for (String productId : productIds) {
//            Product productToOrder = productRepo.getProductById(productId);
//            if (productToOrder == null) {
//                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
//                return null;
//            }
//            products.add(productToOrder);
//        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }
    // Write a method in the ShopService that returns a list of all orders with a specific order status (parameter) using streams.

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status() == status)
                .collect(Collectors.toList());
    }

    //Add an 'updateOrder' method in the ShopService that updates the Order based on an orderId and a new order status. Use the Lombok @With annotation for this.

    public void updateOrder(String orderId, OrderStatus newStatus) { //"erzeuge eine public Methode ohne Rückgabewert namens updateOrder mit den Übergabeparametern orderId und newStatus"
        Order orderToUpdate = Optional.ofNullable(orderRepo.getOrderById(orderId)).orElseThrow(() -> new OrderNotFoundException(orderId)); //"erstelle eine neue Order (orderToUpdate) indem
        // du ein Optional erstellst (leer oder befüllt = .ofNullable) das sich die Info über die orderId aus dem orderRepo holt. Sollte die orderId nicht vorhanden sein, wird die Exception
        // OrderNotFoundException geworfen. Sonst...
        orderRepo.removeOrder(orderId);// ..."loesche die Order mit der Id orderId aus dem orderRepo und..."
        orderRepo.addOrder(orderToUpdate.withStatus(newStatus));//"...erstelle eine neue Order (orderToUpdate) mit der Id orderId und dem neuen Status newStatus"
    }
}

