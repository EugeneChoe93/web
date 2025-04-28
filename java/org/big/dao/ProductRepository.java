package org.big.dao;

import java.util.ArrayList;

import org.big.dto.Product;

public class ProductRepository {
	
	private ArrayList<Product> listOfProducts = new ArrayList<Product>();
	private static ProductRepository instance = new ProductRepository();
	
	public static ProductRepository getInstance() {
		return instance;
	}
	
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
	
	public ProductRepository() {
		// TODO Auto-generated constructor stub
		  
	      
	   }
	   public ArrayList<Product> getAllProducts() {
	      return listOfProducts;
	   }
	   
	   public Product getProductById(String productId) {
		   Product productById = null;
		   
		   for (int i = 0; i < listOfProducts.size(); i++) {
			   Product product = listOfProducts.get(i);
			   if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				   productById = product;
				   break;
			   }
		   }
		   return productById;
	   }
	   
}









