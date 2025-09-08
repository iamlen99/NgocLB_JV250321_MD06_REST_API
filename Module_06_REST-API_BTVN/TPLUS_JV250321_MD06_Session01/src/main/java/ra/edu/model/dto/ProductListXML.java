package ra.edu.model.dto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ra.edu.model.entity.ProductEx6;

import java.util.List;

@XmlRootElement
public class ProductListXML {

    private List<ProductEx6> products;

    public ProductListXML() {}

    public ProductListXML(List<ProductEx6> products) {
        this.products = products;
    }

    @XmlElement
    public List<ProductEx6> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEx6> products) {
        this.products = products;
    }
}
