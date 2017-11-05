package at.fwuick.harryshofladen.controller.shop;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.model.Order;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.exceptions.HofladenException;
import at.fwuick.harryshofladen.service.OrderService;import at.fwuick.harryshofladen.view.converter.ShopProductConverter;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Controller
public class ShopDialogController {
	
	private OrderService orderService;

	@Autowired
	public ShopDialogController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@RequestMapping("/orderDialog")
	public String orderDialog(@RequestParam("product") Long productId, Model model) throws HofladenException{
		
		orderService.validateExists(productId);
		
		addProductToModel(productId, model);
		
		return "fragments/orderDialog";
	}

	private void addProductToModel(Long productId, Model model) {
		ShopProduct shopProduct = orderService.getShopProduct(productId);
		model.addAttribute("product", shopProduct);
	}



}
