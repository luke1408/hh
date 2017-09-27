package at.fwuick.harryshofladen.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.model.Product;

@Controller
public class ShopController {
	
	OrderableProductDao orderableProductDao;
	
	@Autowired
	public ShopController(OrderableProductDao orderableProductDao){
		this.orderableProductDao = orderableProductDao;
	}
	
	@RequestMapping("/shop")
	public String shop(Model model){
		Collection<Product> products = orderableProductDao.all();
		products.forEach(orderableProductDao::persist);
		model.addAttribute("products", products);
		return "shop";
	}
}
