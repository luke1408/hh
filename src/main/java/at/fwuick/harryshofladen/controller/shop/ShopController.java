package at.fwuick.harryshofladen.controller.shop;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.SecurityContextService;
import at.fwuick.harryshofladen.dao.OrderDao;
import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.model.Order;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.dao.model.User;
import at.fwuick.harryshofladen.exceptions.HofladenException;
import at.fwuick.harryshofladen.service.ShopService;
import at.fwuick.harryshofladen.service.interfaces.IOrderService;
import at.fwuick.harryshofladen.view.converter.ListOrderConverter;
import at.fwuick.harryshofladen.view.converter.ShopProductConverter;
import at.fwuick.harryshofladen.view.model.ViewOrder;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Controller
public class ShopController {
	
	OrderableProductDao orderableProductDao;
	IOrderService orderService;
	OrderDao orderDao;
	ListOrderConverter orderConverter;
	ShopService shopService;
	
	
	
	@Autowired
	public ShopController(OrderableProductDao orderableProductDao, IOrderService orderService, OrderDao orderDao,
			ListOrderConverter orderConverter, ShopService shopService) {
		super();
		this.orderableProductDao = orderableProductDao;
		this.orderService = orderService;
		this.orderDao = orderDao;
		this.orderConverter = orderConverter;
		this.shopService = shopService;
	}

	@ModelAttribute("username")
	public String getUsername(){
		return SecurityContextService.getUser().getName();
	}
	
	@RequestMapping("/shop")
	public String shop(Model model, @RequestParam(value = "query", required = false) String searchQuery){
		Collection<ShopProduct> products;
		if(searchQuery == null){
			products = shopService.all();
		}else{
			model.addAttribute("searchString", searchQuery);
			products = shopService.searchProduct(searchQuery);
		}
		model.addAttribute("products", products);
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
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	public String orders(Model model){
		User user = SecurityContextService.getUser();
		List<Order> allUserOrders = orderDao.selectByUser(user.getId());
		
		List<ViewOrder> activeOrders = allUserOrders.stream().filter(o -> o.isActive()).map(orderConverter::convert).collect(Collectors.toList());
		List<ViewOrder> inactiveOrders = allUserOrders.stream().filter(o -> !o.isActive()).map(orderConverter::convert).collect(Collectors.toList());
		
		model.addAttribute("activeOrders", activeOrders);
		model.addAttribute("inactiveOrders", inactiveOrders);
		return "orders";
	}
}
