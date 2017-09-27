package at.fwuick.harryshofladen.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.view.converter.ShopProductConverter;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Controller
public class ShopController {
	
	OrderableProductDao orderableProductDao;
	ShopProductConverter shopProductConverter;
	
	@Autowired
	public ShopController(OrderableProductDao orderableProductDao, ShopProductConverter shopProductConverter){
		this.orderableProductDao = orderableProductDao;
		this.shopProductConverter = shopProductConverter;
	}
	
	@RequestMapping("/shop")
	public String shop(Model model){
		Collection<Product> products = orderableProductDao.all();
		Collection<ShopProduct> shopProducts = products.stream().map(shopProductConverter::convert).collect(Collectors.toList());
		model.addAttribute("products", shopProducts);
		return "shop";
	}
}
