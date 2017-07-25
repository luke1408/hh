package at.fwuick.harryshofladen.controller;

import at.fwuick.harryshofladen.model.admin.requests.CreateProductRequest;
import at.fwuick.harryshofladen.model.admin.requests.UpdateQuantityRequest;
import at.fwuick.harryshofladen.model.admin.responses.ProductListResponse;
import at.fwuick.harryshofladen.model.admin.responses.Response;
import at.fwuick.harryshofladen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HofladenAdminController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/create-product")
    public Response createProduct(@RequestBody @Valid CreateProductRequest request){
        productRepository.create(request.getName(), request.getPrice());
        return Response.SUCCESS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product-list")
    public ProductListResponse createProduct(){
        return new ProductListResponse(productRepository.list());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update-quantity")
    public Response updateQuantity(@RequestBody @Valid UpdateQuantityRequest request){
        productRepository.updateQuantity(request.getProduct(), request.getQuantity());
        return Response.SUCCESS;
    }



    @ExceptionHandler
    public Response catchException(Exception e){
        Response r = new Response(false);
        r.setError(e.getMessage());
        return r;
    }
}
