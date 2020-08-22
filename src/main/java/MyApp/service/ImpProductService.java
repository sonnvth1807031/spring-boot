package MyApp.service;

import MyApp.entity.Product;
import MyApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpProductService implements ProductService{
    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> getAll(int page, int limit) {
        return repository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public boolean update(Product product, int id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()) {
            Product existProduct = optionalProduct.get();
            existProduct.setName(product.getName());
            existProduct.setPrice(product.getPrice());
            existProduct.setThumbnail(product.getThumbnail());
            existProduct.setDescription(product.getDescription());
            repository.save(existProduct);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
