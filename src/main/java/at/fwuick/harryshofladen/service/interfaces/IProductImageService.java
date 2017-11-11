package at.fwuick.harryshofladen.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import at.fwuick.harryshofladen.service.ProductImageException;

public interface IProductImageService {
	public void store(long productId, MultipartFile productImage) throws ProductImageException;
	public String getImageFile(long productId);
	
	
}
