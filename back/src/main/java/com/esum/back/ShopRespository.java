package com.esum.back;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRespository extends JpaRepository<Shop,Integer>{
	Shop findById(int id);
	Shop findByPid(String pid);
	
}
