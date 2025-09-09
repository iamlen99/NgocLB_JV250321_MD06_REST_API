package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Product;
import ra.edu.repository.ProductRepository;
import ra.edu.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long id) {
        return productRepository.findAllByCategoryId(id);
    }

    @Override
    public List<Product> getAllProductsByName(String productName) {
        return productRepository.findAllByNameContaining(productName);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tồn tại sản phẩm có id = " + id));
    }

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new NoSuchElementException("Không tồn tại sản phẩm có id = " + product.getId()));
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tồn tại sản phẩm có id = " + id));
        productRepository.deleteById(id);
    }
}
