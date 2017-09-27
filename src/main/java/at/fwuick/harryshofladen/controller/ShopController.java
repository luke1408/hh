package at.fwuick.harryshofladen.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.SecurityContextService;
import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.dao.model.User;
import at.fwuick.harryshofladen.exceptions.HofladenException;
import at.fwuick.harryshofladen.service.interfaces.IOrderService;
import at.fwuick.harryshofladen.view.converter.ShopProductConverter;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Controller
public class ShopController {
	
	OrderableProductDao orderableProductDao;
	ShopProductConverter shopProductConverter;
	IOrderService orderService;
	
	@Autowired
	public ShopController(OrderableProductDao orderableProductDao, ShopProductConverter shopProductConverter, IOrderService orderService){
		this.orderableProductDao = orderableProductDao;
		this.shopProductConverter = shopProductConverter;
		this.orderService = orderService;
	}
	
	@RequestMapping("/shop")
	public String shop(Model model){
		Collection<Product> products = orderableProductDao.all();
		Collection<ShopProduct> shopProducts = products.stream().map(shopProductConverter::convert).collect(Collectors.toList());
		model.addAttribute("products", shopProducts);
		return "shop";
	}
	
	@RequestMapping(value="/shop/order", method = RequestMethod.POST)
	public String order(@RequestParam("product") long productId, @RequestParam("amount") int amount){
		User user = SecurityContextService.getUser();
		try {
			orderService.order(user.getId(), productId, amount);
		} catch (HofladenException e) {
			e.printStackTrace();
		}
		return "redirect:/shop";
	}
}
