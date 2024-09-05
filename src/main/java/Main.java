import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Create a Main class with a main method. In this method, create an instance of the ShopService.



        //The concrete instances for OrderRepo and ShopRepo should also be created here in the main method.
        OrderRepo orderRepo = new OrderMapRepo();
        ProductRepo productRepo = new ProductRepo();

        //Create four concrete products and add them to the ProductRepo
        Product product1 = new Product("1", "Apfel");
        Product product2 = new Product("2", "Birne");
        Product product3 = new Product("3", "Kirsche");
        Product product4 = new Product("4", "Zitrone");
        productRepo.addProduct(product1);
        productRepo.addProduct(product2);
        productRepo.addProduct(product3);
        productRepo.addProduct(product4);

        // Pass them to the ShopService constructor.
        ShopService shopService = new ShopService(productRepo, orderRepo);

        //Define three concrete orders and add them all to the ShopService.
        Order order1 = new Order("1", List.of(product1, product2));
        Order order2 = new Order("2", List.of(product3, product4));
        Order order3 = new Order("3", List.of(product1, product2));
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);

        ShopService shopService2 = new ShopService(productRepo, orderRepo);
    }
}
