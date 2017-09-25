package at.fwuick.harryshofladen.service;

import java.io.IOException;

public class ProductImageException extends Exception {

	public ProductImageException(String message) {
		super(message);
	}

	public ProductImageException(String string, IOException e) {
		super(string, e);
	}

}
