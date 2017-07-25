package at.fwuick.harryshofladen.model.admin.responses;

import at.fwuick.harryshofladen.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductListResponse extends Response{
    private List<Product> productList;

    public ProductListResponse(List<Product> productList){
        super(true);
        this.productList = productList;
    }
}
