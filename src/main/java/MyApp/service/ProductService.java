package MyApp.service;

import MyApp.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll(int page, int limit);
    Product getProductById(int id);
    Product save(Product product);
    boolean update(Product product, int id);
    boolean delete(int id);
}
