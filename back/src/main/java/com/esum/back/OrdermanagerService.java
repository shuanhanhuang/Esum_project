package com.esum.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdermanagerService {
	
	@Autowired
	private OrdermanagerRepository ordermanagerRepository;
	
	public Ordermanager saveDetail(Ordermanager ordermanager) {
		return ordermanagerRepository.save(ordermanager);
	}
	
    public List<Ordermanager> getAllOrders() {
        return ordermanagerRepository.findAll();
    }

}
