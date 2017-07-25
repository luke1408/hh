package at.fwuick.harryshofladen.model.admin.responses;

import lombok.Data;
import lombok.NonNull;

@Data
public class Response {
    public static final Response SUCCESS = new Response(true);

    private @NonNull  boolean success;
    private String error;



}
