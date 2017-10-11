package at.fwuick.harryshofladen.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import at.fwuick.harryshofladen.service.interfaces.IProductImageService;

@Service
public class ProductImageService implements IProductImageService{
//THIS IS A MOCKUP
	@Override
	public void store(long productId, MultipartFile productImage) throws ProductImageException {
		//MOCKUP!!!
		
	}

	@Override
	public String getImageFile(long productId) {
		int amountImages = 3;
		int index = new Long(productId%amountImages).intValue() + 1;
		return String.format("/img/product/test%d.png", index);
	}


}
