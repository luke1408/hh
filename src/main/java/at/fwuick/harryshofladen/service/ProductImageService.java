package at.fwuick.harryshofladen.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import at.fwuick.harryshofladen.dao.ProductDao;
import at.fwuick.harryshofladen.service.interfaces.IProductImageService;

@Service
public class ProductImageService implements IProductImageService{
//THIS IS A MOCKUP
	ProductDao productDao;
	
	@Autowired
	public ProductImageService(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public void store(long productId, MultipartFile productImage) throws ProductImageException {
		
		try (InputStream is = productImage.getInputStream()){
			productDao.insertImage(productId, is );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String getImageFile(long productId) {
		return productDao.getImageBase64(productId);
	}


}
