package client;

import entity.Product;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {
    List<Product> findAllProducts();
    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(int product, String title, String details);
    void deleteProduct(int productId);
}
