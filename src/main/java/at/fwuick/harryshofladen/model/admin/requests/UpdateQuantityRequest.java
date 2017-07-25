package at.fwuick.harryshofladen.model.admin.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateQuantityRequest {
    private @NonNull Long product;
    private @NonNull Long quantity;
}
