package ra.exercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/servlet")
public class Servlet extends HttpServlet {
    private static final List<Product> products = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        int id = Integer.parseInt(req.getParameter("id"));
        product.setId(id);
        String productName = req.getParameter("name");
        product.setProductName(productName);
        double productPrice = Double.parseDouble(req.getParameter("price"));
        product.setPrice(productPrice);
        int stock = Integer.parseInt(req.getParameter("quantity"));
        product.setStock(stock);
        String description = req.getParameter("desc");
        product.setDescription(description);
        product.setStatus(true);
        products.add(product);

        req.setAttribute("products", products);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
