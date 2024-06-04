package service;


import entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefoaltProductService implements ProductService {

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(String title, String details) {
        return null;
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public void updateProduct(Integer id, String title, String details) {

    }

    @Override
    public void deleteProduct(Integer id) {

    }
}
