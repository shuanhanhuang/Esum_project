package com.esum.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public Order saveDetail(Order order) {
		return orderRepository.save(order);
	}
	
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public void deleteOrder(int id) {
    	orderRepository.deleteById(id);
    }
    
    
}
