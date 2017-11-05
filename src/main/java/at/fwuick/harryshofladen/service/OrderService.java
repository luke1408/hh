package at.fwuick.harryshofladen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fwuick.harryshofladen.dao.OrderDao;
import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.model.Order;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.exceptions.HofladenException;
import at.fwuick.harryshofladen.service.interfaces.IOrderService;
import at.fwuick.harryshofladen.view.converter.ShopProductConverter;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Service
public class OrderService implements IOrderService {



	OrderDao orderDao;
	OrderableProductDao productDao;
	private ShopProductConverter shopProductConverter;
	
	
	
	@Autowired
	public OrderService(OrderDao orderDao, OrderableProductDao productDao, ShopProductConverter shopProductConverter) {
		super();
		this.orderDao = orderDao;
		this.productDao = productDao;
		this.shopProductConverter = shopProductConverter;
	}


	@Override
	public void order(int userId, long productId, int amount) throws HofladenException {
		validateAmountAvailable(productId, amount);
		Order order = new Order();
		order.setAmount(amount);
		order.setProduct(productId);
		order.setUser(userId);
		orderDao.insert(order);
		
	}


	private void validateAmountAvailable(long productId, int amount) throws HofladenException {
		Product product = productDao.get(productId);
		if(amount > product.getAmount()){
			throw new HofladenException("Amount not available");
		}
		
	}
	
	public boolean exists(long productId){
		return productDao.exists(productId);
	}
	
	public void validateExists(long productId) throws HofladenException{
		if(!exists(productId)){
			throw new HofladenException("No product found!");
		}
	}


	public Product get(long productId) {
		return productDao.get(productId);
	}
	
	
	public ShopProduct getShopProduct(Long productId) {
		Product product = this.get(productId);
		ShopProduct shopProduct = shopProductConverter.convert(product);
		return shopProduct;
	}



}
