package com.esum.back;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class ShopService {
	
	@Autowired
	private ShopRespository shopRespository;
	
	public Shop saveDetail(Shop shop) {
		return shopRespository.save(shop);
	}
	
    public List<Shop> getAllShops() {
        return shopRespository.findAll();
    }
    
    public Shop getProductById(int id) {
        return shopRespository.findById(id);
    }
    
    public Shop getProductByPid(String pid) {
        return shopRespository.findByPid(pid);
    }
    
    public void deleteShop(int id) {
    	shopRespository.deleteById(id);
    }
    public Shop updateShop(String pid, int quantity) {
        Shop shop1 = shopRespository.findByPid(pid);
    	shop1.setQuantity(quantity);
        return shopRespository.save(shop1);
        
    }
	
}
