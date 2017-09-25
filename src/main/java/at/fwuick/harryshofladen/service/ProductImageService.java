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
	
	private static final String RESOURCE_FOLDER = "src/main/resources";
	private static final String rootLocationString = RESOURCE_FOLDER+"/static/img";
	private static final String IMAGE_EXTENSION = ".jpg";
	private static final String[] ALLOWED_EXTENSIONS = new String[]{".jpg", ".png"};
	private final Path rootLocation;

	public ProductImageService() {
		this.rootLocation = Paths.get(rootLocationString);
		try {
			init();
		} catch (ProductImageException e) {
			throw new RuntimeException("ProductImage Folder failed to create", e);
		}
	}
	
	@Override
	public void store(long productId, MultipartFile productImage) throws ProductImageException{
        String filename = productId + IMAGE_EXTENSION;
        try {
            if (productImage.isEmpty()) {
                throw new ProductImageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new ProductImageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(productImage.getInputStream(), rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new ProductImageException("Failed to store file " + filename, e);
        }
		
	}
	
	public void init() throws ProductImageException{
		try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new ProductImageException("Could not initialize storage", e);
        }
	}


	@Override
	public Object getImageFile(long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
