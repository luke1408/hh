package at.fwuick.harryshofladen.view.converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fwuick.harryshofladen.dao.ProductDao;
import at.fwuick.harryshofladen.dao.UserDao;
import at.fwuick.harryshofladen.dao.model.Order;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.view.converter.interfaces.IDaoViewConverter;

@Component
public class ListOrderConverter implements IDaoViewConverter<Order, at.fwuick.harryshofladen.view.model.Order>{

	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;
	
	
	@Override
	public at.fwuick.harryshofladen.view.model.Order convert(Order daoOrder) {
		at.fwuick.harryshofladen.view.model.Order viewOrder = new at.fwuick.harryshofladen.view.model.Order();
		viewOrder.setId((long) daoOrder.getId());
		viewOrder.setAmount(daoOrder.getAmount());
		viewOrder.setProductid(daoOrder.getProduct());
		viewOrder.setUserid(daoOrder.getUser());
		
		Product product = productDao.get(viewOrder.getProductid());
		productDao.persist(product);
		viewOrder.setProductname(product.getName());
		viewOrder.setUnit(product.getUnitObj().getName());
		
		viewOrder.setUsername(userDao.getName(viewOrder.getUserid()));
		viewOrder.setPrice(product.getPrice().multiply(new BigDecimal(daoOrder.getAmount())));
		return viewOrder;
		
		
	}

}
