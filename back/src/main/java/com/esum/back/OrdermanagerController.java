package com.esum.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OrdermanagerController {
	
	@Autowired
	private OrdermanagerService ordermanagerService;
	
	@PostMapping("/addOrdermanager")
	public Ordermanager postDetails(@RequestBody Ordermanager ordermanager) {
		return ordermanagerService.saveDetail(ordermanager);
	}
	
	@GetMapping("/getOrdermanager")
    public List<Ordermanager> getAllOrders() {
        return ordermanagerService.getAllOrders();
    }
	

}
