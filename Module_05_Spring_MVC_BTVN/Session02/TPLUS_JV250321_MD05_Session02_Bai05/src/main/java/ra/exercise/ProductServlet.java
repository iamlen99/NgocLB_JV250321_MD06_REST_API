package ra.exercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/servlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Iphone15", 3000.0, "sản phẩm mới về"));
        products.add(new Product(2, "SamSung galaxy 21", 2900.0, "thế hệ đột phá"));
        products.add(new Product(3, "Đồng hồ thụy sỹ", 15000.0, "đẹp khỏi chê"));
        products.add(new Product(4, "Tai nghe airpods", 2000.0, "càng nghe càng thích"));
        products.add(new Product(5, "Laptop lenovo", 3000.0, "đổi trả hàng trong vòng 30 ngày miễn phí"));
        req.setAttribute("products", products);
        req.getRequestDispatcher("productList.jsp").forward(req, resp);
    }
}