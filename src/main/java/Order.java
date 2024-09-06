import lombok.Builder;
import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;


public record Order(
        String id,
        List<Product> products,
        //Add an order status to the Order (PROCESSING, IN_DELIVERY, COMPLETED) to determine the status of the order
       @With
        OrderStatus status,
        //Extend the Order object with a field that stores the order timestamp.
        ZonedDateTime timestamp) {

}
