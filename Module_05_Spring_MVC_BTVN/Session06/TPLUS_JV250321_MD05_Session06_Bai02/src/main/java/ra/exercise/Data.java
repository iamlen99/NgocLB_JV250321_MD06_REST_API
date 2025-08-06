package ra.exercise;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ra.exercise.model.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Data {
    public List<Product> products = Arrays.asList(
            new Product(1, "Áo thun nam", 199000.0, "Áo thun cotton thoáng mát", "https://example.com/images/ao-thun-nam.jpg"),
            new Product(2, "Quần jean nữ", 399000.0, "Quần jean co giãn thời trang", "https://example.com/images/quan-jean-nu.jpg"),
            new Product(3, "Áo sơ mi trắng", 299000.0, "Áo sơ mi công sở tay dài", "https://example.com/images/ao-so-mi.jpg"),
            new Product(4, "Giày sneaker nam", 799000.0, "Giày sneaker phong cách Hàn Quốc", "https://example.com/images/giay-sneaker.jpg"),
            new Product(5, "Túi xách nữ", 499000.0, "Túi xách da cao cấp", "https://example.com/images/tui-xach-nu.jpg"),
            new Product(6, "Đồng hồ thời trang", 999000.0, "Đồng hồ kim dây da sang trọng", "https://example.com/images/dong-ho.jpg"),
            new Product(7, "Nón lưỡi trai", 99000.0, "Nón lưỡi trai phong cách đường phố", "https://example.com/images/non.jpg"),
            new Product(8, "Balo học sinh", 299000.0, "Balo chống nước nhiều ngăn", "https://example.com/images/balo.jpg"),
            new Product(9, "Áo khoác gió", 399000.0, "Áo khoác nhẹ, chống nắng", "https://example.com/images/ao-khoac.jpg"),
            new Product(10, "Tai nghe bluetooth", 299000.0, "Tai nghe không dây, âm thanh rõ nét", "https://example.com/images/tai-nghe.jpg")
    );

}
