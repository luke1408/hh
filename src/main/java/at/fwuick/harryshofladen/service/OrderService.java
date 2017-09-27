package at.fwuick.harryshofladen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fwuick.harryshofladen.dao.OrderDao;
import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.dao.model.Order;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.exceptions.HofladenException;
import at.fwuick.harryshofladen.service.interfaces.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	public OrderService(OrderDao orderDao, OrderableProductDao productDao) {
		super();
		this.orderDao = orderDao;
		this.productDao = productDao;
	}


	OrderDao orderDao;
	OrderableProductDao productDao;
	
	

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



}
