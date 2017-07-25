package at.fwuick.harryshofladen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Float price;
    private Long quantity;

}
