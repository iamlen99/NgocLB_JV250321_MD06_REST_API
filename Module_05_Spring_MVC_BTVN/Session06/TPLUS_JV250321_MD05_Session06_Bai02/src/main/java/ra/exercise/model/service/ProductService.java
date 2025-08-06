package ra.exercise.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.model.entity.Product;
import ra.exercise.model.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> GetAllProducts() {
        return productRepository.findAll();
    }
}
