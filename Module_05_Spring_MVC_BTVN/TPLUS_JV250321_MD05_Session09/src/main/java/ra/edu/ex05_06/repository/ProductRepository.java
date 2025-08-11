package ra.edu.ex05_06.repository;

import org.springframework.stereotype.Repository;
import ra.edu.ex05_06.entity.CartItems;
import ra.edu.ex05_06.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static List<Product> productList;
    private static List<CartItems>  cartItemsList;

    static {
        productList = new ArrayList<>();
        productList.add(new Product(1,"iPhone 16",5000.0,"This is iPhone16",
                "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-16-pro.png",
                1000));
        productList.add(new Product(2,"iPhone 15",4000.0,"This is iPhone15",
                "https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:90/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/dien-thoai-iphone-15-20.jpg",
                1000));
        productList.add(new Product(3,"iPhone 14",3000.0,"This is iPhone14",
                "https://cdn.tgdd.vn/Products/Images/42/240259/iPhone-14-plus-thumb-xanh-600x600.jpg",
                1000));
        productList.add(new Product(4,"iPhone 13",2000.0, "This is iPhone13",
                "https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-starlight-1-600x600.jpg",
                1000));
        productList.add(new Product(5,"iPhone 12",1000.0, "This is iPhone12",
                "https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-tim-1-600x600.jpg",
                1000));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<CartItems> getCartItemsList() {
        return cartItemsList;
    }

    public Product getProduct(String name) {
        return productList.stream()
                .filter(product -> product.getName().equals(name)).findFirst().get();
    }

    public boolean addCartItems(CartItems cartItems) {
        return cartItemsList.add(cartItems);
    }
}
