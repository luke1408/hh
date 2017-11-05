package at.fwuick.harryshofladen.view.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fwuick.harryshofladen.dao.ProductDao;
import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.service.interfaces.IProductImageService;
import at.fwuick.harryshofladen.view.converter.interfaces.IDaoViewConverter;
import at.fwuick.harryshofladen.view.model.ShopProduct;

@Component
public class ShopProductConverter implements IDaoViewConverter<Product, ShopProduct>{
	
	@Autowired
	IProductImageService productImageService;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public ShopProduct convert(Product product) {
		productDao.persist(product);
		String image = productImageService.getImageFile(product.getId());
		String orderDialogUrl = "/orderDialog?product="+product.getId();
		int maxOrderCap = 40;
		int maxOrder = product.getAmount()>maxOrderCap?maxOrderCap:product.getAmount();

		return new ShopProduct(product, image, orderDialogUrl, maxOrder);
	}

}
