package ra.edu.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Product;
import ra.edu.model.repository.ProductRepository;
import ra.edu.model.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts(int page, int pageSize) {
        return productRepository.findAll(page, pageSize);
    }

    @Override
    public List<Product> getProductsByName(String name, int page, int pageSize) {
        return productRepository.findAll(name, page, pageSize);
    }

    @Override
    public int getProductsTotalPages(int pageSize) {
        return productRepository.getTotalPages(pageSize);
    }

    @Override
    public int getProductsTotalPages(String name, int pageSize) {
        return productRepository.getTotalPage(name , pageSize);
    }

    @Override
    public boolean addProduct(Product category) {
        return productRepository.save(category);
    }

    @Override
    public boolean updateProduct(Product category) {
        return productRepository.update(category);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productRepository.delete(id);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }
}
