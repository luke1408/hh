package at.fwuick.harryshofladen.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fwuick.harryshofladen.SecurityContextService;
import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.ProductDao;
import at.fwuick.harryshofladen.dao.UnitDao;
import at.fwuick.harryshofladen.repository.model.Product;
import at.fwuick.harryshofladen.service.ProductImageException;
import at.fwuick.harryshofladen.service.interfaces.IProductImageService;

@Controller
public class ProductManagmentController {

	@Autowired
	OrderableProductDao orderableProductDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	IProductImageService productImageService;
	
	@Autowired
	UnitDao unitDao;
	
	@RequestMapping("/product-managment")
	public String productList(Model model){
		SecurityContextService.validateAdmin();
		
		List<Product> products = orderableProductDao.all();
		model.addAttribute("products", products);
		
		return "productList";
	}
	
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute Product product, @RequestParam("img") MultipartFile imageFile){
		productDao.insert(product);
		try {
			productImageService.store(product.getId(), imageFile);
		} catch (ProductImageException e) {
			e.printStackTrace();
		}
		return "redirect:/product-managment";
	}
	
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String addProduct(Model model){
		model.addAttribute("units", unitDao.all());
		return "createProduct";
	}
	
	


	
	
}
