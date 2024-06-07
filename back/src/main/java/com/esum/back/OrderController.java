package com.esum.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addOrder")
	public Order postDetails(@RequestBody Order order) {
		return orderService.saveDetail(order);
	}
	
	@GetMapping("/getOrder")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
	
	@DeleteMapping("/deleteorder/{id}")
    public void deleteShop(@PathVariable int id) {
		orderService.deleteOrder(id);
    }
}
