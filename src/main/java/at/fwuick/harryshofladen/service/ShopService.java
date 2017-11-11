package at.fwuick.harryshofladen.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fwuick.harryshofladen.dao.OrderableProductDao;
import at.fwuick.harryshofladen.view.converter.ShopProductConverter;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Service
public class ShopService {
	
	private OrderableProductDao productDao;
	private ShopProductConverter productConverter;
	
	
	@Autowired
	public ShopService(OrderableProductDao productDao, ShopProductConverter productConverter) {
		super();
		this.productDao = productDao;
		this.productConverter = productConverter;
	}

	public Collection<ShopProduct> searchProduct(String searchString){
		String[] searchTerms = searchString.split(" ");
		return productConverter.convert(productDao.filterByName(searchTerms));
		
	}
	
	public Collection<ShopProduct> all(){
		return productConverter.convert(productDao.all());
	}
	

}
