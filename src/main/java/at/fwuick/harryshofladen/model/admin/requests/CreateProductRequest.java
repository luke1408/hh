package at.fwuick.harryshofladen.model.admin.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class CreateProductRequest {
    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private Double price;
}
