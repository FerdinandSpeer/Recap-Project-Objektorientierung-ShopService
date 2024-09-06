import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class ProductRepo {
    private final List<Product> products;

    public ProductRepo() {
        products = new ArrayList<>();
        products.add(new Product("1", "Apfel"));
    }

    //Modify the 'getProductById' method in your ProductRepo so that it returns an Optional if the product exists, otherwise an empty Optional.
    public Optional<Product> getProductById(String id) { //"erstelle eine Methode getProductById für ein Optional vom Typ Produkt mit dem Übergabeparametern id."
        for (Product product : products) { //"Schau dir alle Products in products an
            if (product.id().equals(id)) { // wenn die productID des Products product mit dem übergebenen id übereinstimmt
                return Optional.of(product); // gib ein Optional aus, das ein Product beinhaltet..."
            }
        }
        return Optional.empty(); // "...sonst gib ein leeres Optional aus"
    }

    public Product addProduct(Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    public void removeProduct(String id) {
        for (Product product : products) {
           if (product.id().equals(id)) {
               products.remove(product);
               return;
           }
        }
    }
}
