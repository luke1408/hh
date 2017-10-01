package at.fwuick.harryshofladen.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.SecurityContextService;
import at.fwuick.harryshofladen.dao.ActiveOrderDao;
import at.fwuick.harryshofladen.dao.OrderDao;
import at.fwuick.harryshofladen.dao.model.Order;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.view.converter.ListOrderConverter;

@Controller
public class OrderManagmentController {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ActiveOrderDao activeOrderDao;
	
	@Autowired
	ListOrderConverter orderConverter;
	
	@RequestMapping("/order-managment")
	public String orderList(Model model){
		SecurityContextService.validateAdmin();
		List<Order> orders = activeOrderDao.all();
		model.addAttribute("orders", orders.stream().map(orderConverter::convert).collect(Collectors.toList()));
		return "orderList";
	}
	
	@RequestMapping(value="/order-managment/close", method = RequestMethod.POST)
	public String closeOrder(@RequestParam("order") Long orderId){
		SecurityContextService.validateAdmin();
		orderDao.setActive(orderId, false);
		return "redirect:/order-managment";
	}
}
