package ra.exercise.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.exercise.Data;
import ra.exercise.model.entity.Product;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private Data data;

    public List<Product> findAll() {
        return data.getProducts();
    }

    public boolean save(Product product) {
        product.setId(data.getCurrentId() + 1);
        return data.getProducts().add(product);
    }
}
