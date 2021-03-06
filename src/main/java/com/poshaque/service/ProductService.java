package com.poshaque.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poshaque.dao.CartRepository;
import com.poshaque.dao.ProductImageRepository;
import com.poshaque.dao.ProductsRepository;
import com.poshaque.dao.ReviewRepository;
import com.poshaque.exception.PoshaqueBussinessException;
import com.poshaque.model.Cart;
import com.poshaque.model.ProductImages;
import com.poshaque.model.ProductThumbnail;
import com.poshaque.model.Products;

@Service
public class ProductService {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private CartRepository cartRepository;

	public Page<Products> getAllProducts(Pageable page, String searchTerm){
		Page<Products> products = null;
		if(searchTerm.isEmpty())
			products = productsRepository.findAll(page);
		else
			products = productsRepository.findAll(page,searchTerm);
		
		return products;
	}

	public Page<Products> getProductsByCategoryId(Integer categoryId, Pageable page, String searchTerm) {
		Page<Products> products = null;
		if(searchTerm.isEmpty())
			products = productsRepository.findByCategoryId(categoryId,page);
		else
			products = productsRepository.findByCategoryId(categoryId,page,searchTerm);
		return products;
	}

	public Map<String,Object> getProductById(Integer id, UserPrincipal principal) {
		Map<String,Object> map = new HashMap<String,Object>();
		Products products = null;
		Optional<Products> optProducts = productsRepository.findById(id);
		
		if(optProducts.isPresent()){
			products = optProducts.get();
			List<ProductImages> list = productImageRepository.findByProductId(id);
			products.setProductImage(list);
		}else
			throw new PoshaqueBussinessException("Product not found.");
		
		List<Products> list = productsRepository.findRandomProducts(products.getCategoryId(),products.getId());
		map.put("productDetails", products);
		map.put("relatedProducts", list);
		map.put("reviewInfo", reviewRepository.findReviewCount(id));
		Integer userReviewCount = reviewRepository.findUserReviewCount(id, principal.getId());
		map.put("reviewDone", (userReviewCount > 0));
		return map;
	}

	public List<ProductThumbnail> getCartThumbnails(UserPrincipal principal) {
		Cart cart = cartRepository.findByUserId(principal.getId());
		Set<Integer> prodIds = new HashSet<Integer>();
		if(cart != null && cart.getCartData() != null){
			String jsonData = cart.getCartData();
			try {
				JSONObject cartObj = new JSONObject(jsonData);
				JSONObject prodObj = cartObj.getJSONObject("products");
				Iterator<String> keys = prodObj.keys();
				while(keys.hasNext()) {
				    String key = keys.next();
				    if (prodObj.get(key) instanceof JSONObject) {
				    	prodIds.add(prodObj.getJSONObject(key).getInt("id"));
				    }
				}
			} catch (JSONException e) {
				throw new PoshaqueBussinessException("Invalid cart error.");
			}
		}
		return productsRepository.findByIdIn(prodIds);
	}

}
