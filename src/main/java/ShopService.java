import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
// Use the @RequiredArgsConstructor annotation in the ShopService to generate a corresponding constructor.
@RequiredArgsConstructor

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;


//Modify the 'addOrder' method in the ShopService so that an exception is thrown if the product does not exist.
//In the 'addOrder' method, fill this field with the current timestamp.

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

        for (String productId : productIds) {//"Führe eine Schleife aus durch die Liste productIds aus und suche nach einem String productID."
            Product productToOrder = productRepo.getProductById(productId)//"Das Product productToOrder bezieht sich auf die productID die ich über die .getProductByID Methode aus dem productRepo hole."
                    .orElseThrow(() -> new ProductNotFoundException(productId));//"Wenn die productId nicht vorhanden ist, wird die ProductNotFoundException geworfen."
            products.add(productToOrder); //"Sonst füge das Product productToOrder der Liste products hinzu."
        }
//    Old addOrder method:
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
        return orderRepo.addOrder(newOrder);
    }
    // Write a method in the ShopService that returns a list of all orders with a specific order status (parameter) using streams.

    public List<Order> getOrdersByStatus(OrderStatus status) { //"Erzeuge eine Liste über die Methode getOrdersByStatus mit dem Übergabeparameter status."
        return orderRepo.getOrders().stream() //"Gib mir aus dem orderRepo mit Hilfe eines Streams die Orders aus."
                .filter(order -> order.status() == status) //"Filter dir orders heraus die einen status haben.
                .collect(Collectors.toList()); //"Gib dir diese Orders in einer Liste zurück.
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

