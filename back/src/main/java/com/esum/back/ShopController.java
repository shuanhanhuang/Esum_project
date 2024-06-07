package com.esum.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.esum.back.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/addProduct")
	public Shop postDetails(@RequestBody Shop shop) {
		return shopService.saveDetail(shop);
	}
	
	@GetMapping("/getProduct")
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }
	
	@GetMapping("/getProduct/{id}")
    public Shop getProductById(@PathVariable int id) {
        return shopService.getProductById(id);
    }
	
	@GetMapping("/getProductpid/{pid}")
    public Shop getProductByPid(@PathVariable("pid") String pid) {
        return shopService.getProductByPid(pid);
    }
	
	@DeleteMapping("/deleteProduct/{id}")
    public void deleteShop(@PathVariable int id) {
        shopService.deleteShop(id);
    }
	
	 @PutMapping("updateProduct/{pid}")
    public Shop updateShop(@PathVariable("pid") String pid, @RequestParam int quantity) {
        return shopService.updateShop(pid,quantity);
    }
	
	
	
	
}
