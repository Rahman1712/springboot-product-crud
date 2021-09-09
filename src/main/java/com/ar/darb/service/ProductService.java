package com.ar.darb.service;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.ar.darb.dao.Products;

@Service
public class ProductService {

	NavigableMap<Integer, Products> PRODUCT_NAV_MAP = new TreeMap<>();
	
	@PostConstruct
	private void initProds() {
		 List<Products> collect = Stream.of(
				 	new Products(1, "Realme X7 Max", "Realme", "India", 25690),
					new Products(2, "Iphone 12 Pro Max", "Apple", "USA", 115900),
					new Products(3, "Redmi Note 10s", "Xiaomi", "China", 14999),
					new Products(4, "Samsung Galaxy Flip 3", "Samsung", "South Korea", 84999),
					new Products(5, "Xperia 10 III Lite", "Sony", "Japan", 31690),
					new Products(6, "ROG Phone 5s Pro", "Asus", "Taiwan", 90590),
					new Products(7, "Vivo V20 Pro", "Vivo", "China", 29990),
					new Products(8, "Jio Phone 2", "Jio", "India", 2999),
					new Products(9, "BlackBerry KEY2 LE", "BlackBerry", "Canada",  22990),
					new Products(10, "Reno6 Pro", "Oppo", "China", 39990),
					new Products(11, "Andi Wink 4G", "Iball", "India", 5999),
					new Products(12, "Eluga Ray 810", "Panasonic", "Japan", 7499),
					new Products(13, "Magic 3 pro+", "Honor", "China", 91790),
					new Products(14, "Lava Z2s", "Lava", "India", 7299),
					new Products(15, "Toshiba Excite Go", "Toshiba", "Japan", 8990),
					new Products(16, "Karbonn X21", "Karbonn", "India", 5498),
					new Products(17, "OnePlus Nord 2 5G", "OnePlus", "China", 24999),
					new Products(18, "Liquid Z6 Plus", "Acer", "Taiwan", 17999),
					new Products(19, "mPhone 7s", "mPhone", "India", 19999),
					new Products(20, "Nova Y60", "Huawei", "China", 15390)
					).collect(Collectors.toList());
			 
			 collect.parallelStream().forEach(prod -> PRODUCT_NAV_MAP.put(prod.getId(), prod));
	}
	
	public List<Products> listOfProducts(){
		return  PRODUCT_NAV_MAP.entrySet().parallelStream()
				.map(es -> es.getValue()).collect(Collectors.toList());
	}

	public void save(Products product) {
		if(product.getId() <= 0) {
			if(PRODUCT_NAV_MAP.size() == 0) {
				product.setId(1);
				PRODUCT_NAV_MAP.put(product.getId(), product);
			}else{
				product.setId(PRODUCT_NAV_MAP.lastKey()+1);
				PRODUCT_NAV_MAP.put(product.getId(), product);
			}
		}else {
			PRODUCT_NAV_MAP.put(product.getId(), product);
		}
	}
	
	public void update(Products product) {
		PRODUCT_NAV_MAP.put(product.getId(), product);
	}

	public Products findById(int id) {
		return PRODUCT_NAV_MAP.entrySet().parallelStream()
				.map(entryset -> entryset.getValue())
				.filter(prod -> prod.getId() == id)
				.findAny().orElse(null);
	}
	

	public void delete(int id) {
		PRODUCT_NAV_MAP.remove(id);
	}
	

}
