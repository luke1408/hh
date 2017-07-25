package at.fwuick.harryshofladen.controller;

import at.fwuick.harryshofladen.model.Product;
import at.fwuick.harryshofladen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HofladenController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/shop")
    public String shopIndex(@RequestParam(value="page", required=false, defaultValue="0") Long page, Model model){
        long pageSize = 20;
        List<Product> products =  productRepository.list();
        model.addAttribute("products", products);
        model.addAttribute("page", page);
        return "shop";
    }


}
