package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Product;
import ra.edu.repository.ProductRepository;
import ra.edu.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    @Override
    public boolean addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.delete(id);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findProductByNameAndCategory(String name, Long categoryId) {
        return productRepository.findByNameAndCategory(name, categoryId);
    }
}
